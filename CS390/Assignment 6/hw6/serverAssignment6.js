var express = require('express');
var bodyParser = require('body-parser');
var sqlite3 = require('sqlite3').verbose();

var db = new sqlite3.Database('Jeopardy.db');
var cors = require('cors');

var app = express();

app.use(cors());//getting access to everything in the web app
app.use(bodyParser.urlencoded({
	extended:true
}));
app.use(bodyParser.json());

app.post('/auth/signin', function(req, res){
	var{userID, password} = req.body;	
	var currentQuery = "SELECT UserPassword FROM Users WHERE UserID='" + userID.toLowerCase() + "'";
	db.all(currentQuery, function(err, rows){
		if(err){
			return res.status(500).json(
				{message: "Internal server error"});			
		}else{
			//if the username or password is missing return invalid data with 400 status code
			if(rows.length <= 0){
				return res.status(400).json(
					{message: "invalid_data"});
			}

			//if username is valid, return success with 200 status code
			if(rows[0].UserPassword === password){
				return res.status(200).json(
					{message: "success"});
			}else{
				//if username password present but incorrect, return invalid credentials with 401 status code
				return res.status(401).json(
					{message: "invalid_credentials"});
			}
		}
	});

});

app.get('/questions', function(req, res){
	var {categoryTitle, airDate, questionText, dollarValue, answerText, showNumber} = req.query;
	var statement = "";
	var categoryCode;
	
	function getCategoryTitle(successCallBack){
		console.log("callback 1");
		if(categoryTitle !== undefined){
			if(statement === ""){
				statement += " WHERE CategoryTitle='" + categoryTitle.toUpperCase() + "'";
			}else{
				statement += " AND CategoryTitle='" + categoryTitle.toUpperCase() + "'";
			}
		}
		successCallBack(getQuestionText);
		//successCallBack(getQuestionText) = getAirDate(getQuestionText);
	}

	function getAirDate (successCallBack){
		console.log("callback 2");

		if(airDate !== undefined){

			var day;
			var month;
			var year;

			year = airDate[2] + airDate[3];//YYYY getting the last 2 Y
			
			if(airDate[5] !== "0"){ //MM
				month = airDate[5] + airDate[6];
			}else{
				month = airDate[6];
			}

			if(airDate[8] !== "0"){//DD
				date = airDate[8] + airDate[9];
			}else{
				date = airDate[9];
			}

			var date = month + "/" + day + "/" + year;

			if(statement === ""){
				statement += " WHERE AirDate='" + airDate +"'";
			}else{
				statement += " AND AirDate='" + airDate + "'";
			}
		}
		successCallBack(getDollarValue);
	}

	function getDollarValue(successCallBack){
		console.log("callback 4");
		if(dollarValue !== undefined){
			if(dollarValue[0] !== '$'){
				dollarValue = '$' + dollarValue;
			}
			if(statement === ""){
				statement += " WHERE DollarValue='" + dollarValue +"'";
			}else{
				statement += " AND DollarValue ='" + dollarValue + "'";
			}			
		}
		successCallBack(getShowNumber);
	}



	function getQuestionText(successCallBack){
		console.log("callback 3");
		if(questionText !== undefined){
			if(statement === ""){
				statement += " WHERE QuestionText LIKE '%" + questionText + "%'";
			}else{
				statement += " AND QuestionText LIKE '%" + questionText + "%'"
			}
		}
		successCallBack(getAnswerText);
	}



	function getAnswerText(successCallBack){
		console.log("callback 5");
		if(answerText !== undefined){
			if(statement === ""){
				statement += " WHERE AnswerText LIKE '%" + answerText + "%'";
			}else{
				statement += " AND AnswerText LIKE '%" + answerText + "%'";
			}
		}
		successCallBack(getQuery);
	}

	function getShowNumber(successCallBack){
		if(showNumber !== undefined){
			if(statement === ""){
				statement += " WHERE ShowNumber=" + showNumber;
			}else{
				statement += " AND ShowNumber=" + showNumber;
			}
		}
		successCallBack();
	}
	
	function getQuery(){
		//returned sorted by Air Date in descending order 
		//var currentQuery = "SELECT * FROM Questions" + statement + " ORDER BY AirDate DESC";
		var currentQuery = "SELECT Categories.CategoryTitle, AirDate, QuestionText, DollarValue, AnswerText, ShowNumber FROM Questions JOIN Categories ON Questions.CategoryCode = Categories.CategoryCode" + statement + " ORDER BY AirDate DESC";
		//in order to arrange the format like the pdf, ON is to match the categoryCode from both tables
		console.log(currentQuery);
		db.all(currentQuery, function(err,rows){
			if(err){
				return res.status(500).json(
					{message: "Internal server error"});
			}else{
				if(rows.length <= 0){
					return res.status(400).json(
						{message: "invalid_data"});
				}else{
					if(rows.length > 5000){
						return res.status(400).json(
							{message: "too_many_results"});
					}else{
						return res.status(200).json(rows);
					}
				}
			}
		});
	
	}
	
	getCategoryTitle(getAirDate);

});

var port = process.env.PORT || 8000;
var server = app.listen(port, function() {
	console.log(`App listening on port ${port}`);
});

