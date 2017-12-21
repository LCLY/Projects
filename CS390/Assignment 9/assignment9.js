function body_onload(){
   
    var app = new Vue({
        el: "#hw9App",

        data:{
            questions:[],
            signInStatus:false,
            addNewStatus:false,
            signInMessage:"",
            searchMessage:"",  
            questionid:"", 
            deleteConfirmStatus:false,     
            questiontext:"", 
            addNewStatus:false,
            addNewQuestionMessage:"", 
            error:false, 
        },

        methods:{
            checkSignIn: function(){
                var url = "http://localhost:8000/";
                try {
                    var httpRequest = new XMLHttpRequest();
                    httpRequest.onreadystatechange = httpStateChange;
                    httpRequest.onerror = httpError;
                    httpRequest.open("POST", url + "auth/signin", true);
                    httpRequest.setRequestHeader("Content-type", "application/json");
                    httpRequest.send(JSON.stringify({ userID: txtUserID.value, password: txtPassword.value }));
                } catch (error) {
                    alert("Error!");
                }

                function httpError() {
                    alert("network error");
                }

                function httpStateChange() {
                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);
                            sessionStorageSet("userID", txtUserID.value);
                            sessionStorageSet("AuthToken", serverResponse.authToken);

                            app.signInStatus = true;                       
                        } else {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);
                            app.signInMessage = serverResponse.message;
                        }
                    }
                }
            },

            getQuestions: function () {
                var url = "http://localhost:8000/";
                var authToken = sessionStorageGet("AuthToken", null);
                try {
                    var httpRequest = new XMLHttpRequest();
                    httpRequest.onreadystatechange = httpStateChange;
                    httpRequest.onerror = httpError;
                    httpRequest.open("GET", url + "questions?auth=" + authToken + "&questionText=" + txtSearchBar.value, true);
                    httpRequest.setRequestHeader("Content-type", "application/json");
                    httpRequest.send(null);
                } catch (error) {
                    alert("error");
                }

                function httpError() {
                    alert("network error");
                }

                function httpStateChange() {
                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {
                            app.error = false;
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                          
                            app.questions = serverResponse;                          
                        } else {
                            app.error = true;
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                          
                            app.searchMessage = serverResponse.message;
                        }
                    }
                }
            },

            openAddNew: function(){
                app.addNewStatus= true;
                app.getCategories();
            },

            deleteConfirm: function (questionid) {
                app.deleteConfirmStatus = true;
                app.questionid = questionid;
            },

            questionDelete: function () {
                var url = "http://localhost:8000/";
                var authToken = sessionStorageGet("AuthToken", null);
                app.deleteConfirmStatus = false;

                try {
                    var httpRequest = new XMLHttpRequest();
                    httpRequest.onreadystatechange = httpStateChange;
                    httpRequest.onerror = httpError;
                    httpRequest.open("GET", url + "questiondelete?auth=" + authToken + "&questionID=" + app.questionid, true);
                    httpRequest.setRequestHeader("Content-type", "application/json");
                    httpRequest.send(null);
                } catch (error) {
                    alert("error");
                }

                function httpError() {
                    alert("network error");
                }

                function httpStateChange() {
                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                    
                            app.questions = serverResponse;                         
                            app.getQuestions();
                        } else {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                         
                            alert(serverResponse.message);
                        }
                    }
                }
            },

            getCategories: function () {
                var url = "http://localhost:8000/";

                authToken = sessionStorageGet("AuthToken", null);

                try {
                    var httpRequest = new XMLHttpRequest();
                    httpRequest.onreadystatechange = httpStateChange;
                    httpRequest.onerror = httpError;
                    httpRequest.open("GET", url + "getcategories?auth=" + authToken, true);
                    httpRequest.setRequestHeader("Content-type", "application/json");
                    httpRequest.send(null);
                } catch (error) {
                    alert("error");
                }

                function httpError() {
                    alert("network error");
                }

                function httpStateChange() {
                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);

                            for (var i = 0; i < serverResponse.length; i++) {
                                var category = document.createElement("option");
                                category.text = serverResponse[i].CategoryTitle;
                                txtAddNewCategory.appendChild(category);
                            }
                        } else {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                          
                            alert(serverResponse.message);
                        }
                    }
                }
            },

            addQuestion: function () {
                var url = "http://localhost:8000/";

                authToken = sessionStorageGet("AuthToken", null);

                try {
                    var httpRequest = new XMLHttpRequest();
                    httpRequest.onreadystatechange = httpStateChange;
                    httpRequest.onerror = httpError;
                    httpRequest.open("POST", url + "questionadd?auth=" + authToken, true);
                    httpRequest.setRequestHeader("Content-type", "application/json");
                    httpRequest.send(JSON.stringify({
                        categoryTitle: txtAddNewCategory.options[txtAddNewCategory.selectedIndex].text,
                        airDate: txtAddNewAirDate.value,
                        showNumber: txtAddNewShowNumber.value,
                        dollarValue: txtAddNewDollarValue.value,
                        questionText: txtAddNewQuestion.value,
                        answerText:txtAddNewAnswer.value
                    }));
                } catch (error) {
                    alert("error");
                }

                function httpError() {
                    alert("network error");
                }

                function httpStateChange() {
                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);                          
                            app.addNewStatus = false;
                            app.error = false;
                        } else {
                            app.error = true;
                            var json = httpRequest.responseText;
                            serverResponse = JSON.parse(json);
                            app.addNewQuestionMessage = serverResponse.message;
                        }
                    }
                }
            },


        }
    })

    Vue.component("result-box", {
        template: "#result-box-template",
        props: ["categorytitle", "dollarvalue", "airdate", "questiontext", "answertext","questionid"],
        data: function () {
            return {
                showAnswer: false,
            }
        },

        methods: {
            deleteConfirm: function () {
                app.deleteConfirm(this.questionid);
            }
        }
    })


}

function localStorageGet(token, defaultValue) {
    var value = localStorage.getItem(token);

    if (value === null) {
        return defaultValue;
    }
    return value;
}

function localStorageSet(token, value) {
    localStorage.setItem(token, value);
}

function sessionStorageGet(token, defaultValue) {
    var value = sessionStorage.getItem(token);

    if (value === null) {
        return defaultValue;
    }
    return value;
}

function sessionStorageSet(token, value) {
    sessionStorage.setItem(token, value);
}