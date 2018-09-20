var express = require("express");
var sqlite3 = require("sqlite3"),
TransactionDatabase = require("sqlite3-transactions").TransactionDatabase;
var bodyParser = require("body-parser");
var guid = require("guid");
var moment = require("moment");

var app = express();
//var db = new sqlite3.Database('./Jeopardy.db');

var db = new TransactionDatabase(
	new sqlite3.Database("./Jeopardy.db", sqlite3.OPEN_READWRITE | sqlite3.OPEN_CREATE)
);

app.use( bodyParser.urlencoded({ extended: true }));

app.use(bodyParser.json());


app.get("/", function(req,res) {
	db.get("select * from users",function(e,u){
		return res.json(u);
	})
	return res.send("Hello World");
})


app.post('/auth/signin', function(req, res) {
	var userID = req.body.userID;
	var password = req.body.password;
	
	if(userID == null || password == null) {
		return res.status(401).json({message: "invalid_credentials"});
	}
	
	var dbQuery = "select * from Users where UserID = ? and UserPassword = ?";
	var requestParams = [userID, password];
	
	db.get(dbQuery, requestParams, function(err, user) {
		if(err) {
			return res.status(500).json({message: "Internal server error"});
		}

		if(user == null) {
			return res.status(401).json({message: "invalid_credentials"});
		}
		var authToken = guid.raw();
		console.log(authToken);
		var authTokenIssued = moment().toJSON();
		console.log(authTokenIssued);

		dbQuery = "update Users set AuthToken = ?, AuthTokenIssued = ? where UserId = ? and UserPassword = ?";
		requestParams = [authToken, authTokenIssued, userID, password];
		db.run(dbQuery, requestParams);

		return res.status(200).json({message: "success", authToken: authToken});
	});

});

app.use(function(req,res,next) {
	//Check which endpoint the user is trying to access
	if(req.path === "/auth/signin") {
		//We don't want to protect this route, let the request continue
		return next();
	} else {
		var auth = req.query.auth;
		var dbQuery = "select * from Users where AuthToken = ?";
		var requestParams = [auth];
		db.get(dbQuery, requestParams, function (err, user){
			if(err){
				return res.status(500).json({message: "Internal server error"});
			}
			//if user doesnt exist or invalid return unauthorized access
			if(user == null){
				return res.status(400).json({message: "unauthorized excess"});
			}
			//if user exist or invalid, or expired, return auth token expired
			if(moment().diff(moment(user.AuthTokenIssued), 'seconds') > 3600){
				return res.status(400).json({message: "auth token expired"});
			}

			//to test whether it works, change hours to seconds
			/*
			if(moment().diff(moment(user.AuthTokenIssued), 'seconds') > 1){
				return res.status(400).json({message: "auth token expired"});
			}*/
			return next();
		});
	}
});

app.get('/questions', function(req, res) {
	var categoryTitle = req.query.categoryTitle;
	var dollarValue = req.query.dollarValue;
	var questionText = req.query.questionText;
	var answerText = req.query.answerText;
	var showNumber = req.query.showNumber;
	var airDate = req.query.airDate;

	var dbQuery = "select * from Questions join Categories on Questions.CategoryCode = Categories.CategoryCode where ";
	var paramCount = 0;
	var params = [];

	if (categoryTitle != null) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'CategoryTitle = ? ';
		params.push(categoryTitle.toUpperCase());
	}

	if (dollarValue != null) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'DollarValue = ? ';
		dollarValue = "$" + dollarValue;
		params.push(dollarValue);
	}

	if (questionText) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'QuestionText like ? ' ;
		questionText = '%' + questionText + '%';
		params.push(questionText);
	}

	if (answerText) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'AnswerText = ? ';
		params.push(answerText);
	}

	if (showNumber) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'ShowNumber = ? ';
		params.push(showNumber);
	}

	if (airDate) {

		if(paramCount > 0) {
			dbQuery = dbQuery + 'and ';
		}

		paramCount++;
		dbQuery = dbQuery + 'AirDate = ? ';
		params.push(airDate);
	}

	dbQuery = dbQuery + 'order by AirDate desc';

	if(paramCount == 0) {
		dbQuery = "select * from Questions order by AirDate desc";
	}

	db.all(dbQuery, params, (err, questions) => {

		if(questions.length > 5000) {
			return res.status(400).json({message: "too_many_results"});
		}

		if (err) {
			console.log(err);
			return res.status(500).json({message: "Internal server error"});
		}

		return res.status(200).json(questions);
	});
});


app.post('/questionadd', function(req, res) {
	var categoryCode = req.body.categoryCode;
    var categoryTitle = req.body.categoryTitle;
    var dollarValue = req.body.dollarValue;
    var questionText = req.body.questionText;
    var answerText = req.body.answerText;
    var showNumber = req.body.showNumber;
	var airDate = req.body.airDate;
	var dbQuery = "insert into Questions (CategoryCode, CategoryTitle, DollarValue, QuestionText, AnswerText, ShowNumber, AirDate) values ('?,?,?,?,?,?,?')"
	var paramCount = 0;
	var params = [];
	if(!showNumber){
		return res.status(400).json({message: "Show Number is empty or invalid."});
	}
	if(showNumber){
		if(parseInt(showNumber) <= 0){
			return res.status(400).json({message: "Show Number is not a valid integer."});
		}
	}
	if(!dollarValue){
		return res.status(400).json({message: "Dollar Value is empty or invalid."});
	}
	if(dollarValue){
		if(parseInt(dollarValue) % 100 !== 0 && parseInt(dollarValue) < 100 && parseInt(dollarValue) > 2000){
			return res.status(400).json({message: "Dollar Value is invalid, it must be an even multiple of 100 and in the range of 100 through 2000."});
		}
	}
	if(!airDate){
		return res.status(400).json({message: "Air Date is empty or invalid."});
	}
	if(!questionText){
		return res.status(400).json({message: "Question Text is empty or invalid."});
	}
	if(!answerText){
		return res.status(400).json({message: "Answer Text is empty or invalid."});
	}
	if(!categoryCode){
		return res.status(400).json({message: "Category Code is empty or invalid."});
	}
	if(!categoryTitle){
		return res.status(400).json({message: "Category Title is empty or invalid."});
	}

	
	var dbQuery = "select * from Categories where CategoryCode = ?";
	var requestParams = [categoryCode];

	db.get(dbQuery, requestParams,function(err, category){
		if(err){
			return res.status(500).json({message: "Internal server error"});
		}
		//if category code is not in the table or doesnt exist
		if(category == null){
			var dbQuery = "select * from Categories where CategoryTitle = ?";
            var requestParams = [categoryTitle.toUpperCase()];

            db.get(dbQuery, requestParams, function (err, category) {
                if (err) {
                    return res.status(500).json({ message: "Internal server error" });
                }
				//if categoryTitle exist
                if (category != null) {
					//if categoryCode is not the same
                    if (category.CategoryCode !== categoryCode)
                        return res.status(400).json({ message: "Invalid Category Code" });
                } else {
                    addToTable();
                }
            });
		}else{
			//if categoryCode exist
			//if the category title doesnt match
			if (category.CategoryTitle !== categoryTitle.toUpperCase()){
				return res.status(400).json({ message: "Invalid Category Title" });
			}	
			else {
				addToTable();
			}
		}

});	

//to add data into the table
function addToTable() {
	var dbQuery = "select * from Categories where CategoryTitle = ? and CategoryCode = ?";
	var requestParams = [categoryTitle.toUpperCase(), categoryCode];

	db.get(dbQuery, requestParams, function (err, category) {
		if (err) {
			return res.status(500).json({ message: "Internal server error" });
		}

		if (category == null) {
			requestParamsCategory = [parseInt(categoryCode), categoryTitle.toUpperCase()];
			var dbQueryCategory = "insert into Categories values (?, ?)";
			requestParamsQuestion = [parseInt(showNumber), airDate, parseInt(categoryCode), parseInt(dollarValue), questionText, answerText];
			var dbQueryQuestion = "insert into Questions (ShowNumber, AirDate, CategoryCode, DollarValue, QuestionText, AnswerText) values (?, ?, ?, ?, ?, ?)";

			db.beginTransaction(function (err, transaction) {
				transaction.run(dbQueryCategory, requestParamsCategory);
				transaction.run(dbQueryQuestion, requestParamsQuestion);

				transaction.commit(function (err) {
					if (err) {
						return console.log("Transaction failed.", err);	
					}
					return res.status(200).json({ message: "success" });
				});
			});
		} else {
			var requestParams = [parseInt(showNumber), airDate, parseInt(categoryCode), parseInt(dollarValue), questionText, answerText];
			var dbQuery = "insert into Questions (ShowNumber, AirDate, CategoryCode, DollarValue, QuestionText, AnswerText) values (?, ?, ?, ?, ?, ?)";
			db.run(dbQuery, requestParams);
			return res.status(200).json({ message: "success" });
		}
	});
}
});

var port = process.env.PORT || 8000;
app.listen(port, function() {
	console.log("Running server on port " + port);
});