# Insticator Project
## Problem Overview

Create RESTful API endpoints for an embed to show four different types of questions:
- trivia: a question have two to four options with one of them is the correct answer. 
- poll: an objective question have two to four options without a correct answer.  
- checkbox: an objective question with up to ten options and people can choose anything between one and ten options.
- matric: an objective question that shows options in a matrix. A visitor can only pick one of the available options, there is no correct and wrong.

## Frameworks and technologies
- Spring Boot (MVC framework)
- JPA/Hibernate for CRUD services
- MySQL database for data storage

## Database Structure
![alt text](https://github.com/Fancylynn/Insticator/blob/master/database%20schema.jpg)

## RESTful API endpoints
- Host-address=api.fancylynn.com
- App-name=insticator
- URL prefix should be http://api.fancylynn.com/insticator
### Question
| Method | Endpoint | Purpose |
|--|--|--|
| GET | /question/all | Get all the existing questions in the pool |
| POST | /question/response | Save user's answer to the question |
| POST| /question | Create a new question |
| DELETE | /question/{questionId} | Delete an existing question |

### User
| Method | Endpoint | Purpose |
|--|--|--| 
| GET | /user/getQuestions | Return a list of questions for a specific user |
| POST | /user | Create a new user if the ip address doesn't exist in the user list |

### How it works
- Starting from the front-end, it should provide an required param of end user's ip address; By detecting if the ip address exists in the user list, backend either gives back a list of questions based on rollingPeriod(number of questions gives back to end user everytime) and startPoint (for avoid giving back the questions that end user has answered before), or returns error message saying the user doesn't exist

- If the user doesn't exist, front-end should send create new user post request, backend will generate record based on end-user ip address, the default starting point of the questions is 1.

- Questions and options are sepreated in different tables and they are joined based on pk and fk; options for trivia, poll and checkbox questions are stored in option table; options for matric questions are stored in matrix and matrix_option table.

- End user's answers to questions are stored in question_response table; the answers should be a json string;
  Please use JSON.stringify() for data storage and JSON.parse() for data analysis.
  https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify
  
  For matric questions:
  ```
  JSON.stringify({ matrix: {age: < 20, gender: f} })
  // expected output: "{"matrix":{"age":"< 20", "gender": "f"}"
  ```
  For other questions:
  ```
  JSON.stringify({ #question_type: [blue, red, yellow] })
  // expected output: "{"#question_type":["blue", "red", "yellow"]}"
  ```
### Examples
- Create a new question
http://api.fancylynn.com/insticator/question/
```
{
	"questionContent": "what's your favorite car brand",
	"questionType": "poll",
	"options": ["Nissan", "Honda", "Audi", "BMW"]
}
```
```
{
	"questionContent": "Which team won the 2017 super bowl?",
	"questionType": "trivia",
	"options": ["Falcons", "Patriots"],
	"correctOption": "Patriots"
}
```
```
{
	"questionContent": "Please tell us a bit about yourself",
	"questionType": "matrix",
	"matrixOptions": {"age": ["<18", "18-35", "35-55", ">55"], "gender": ["Male", "Female"]}
}
```
- Save answer to question
http://api.fancylynn.com/insticator/question/response
```
{
	"userId": 1,
	"questionId": 1,
	"response": "Nissan"
}
```

 ## Assumption
 - In this project, I assume that the relationship between questions and options is one to many instead of many to many, which means options won't appear in different questions (as the dataset I used for testing is pretty small and won't influence the performance even if duplication exists)
 
 ## Improvement and Scalability
 - Instead of one to many relationship between questions and answers, additional tables should be provided for improving performance as options can appear in multiple questions. 
 
 - Load balance should be used to improve the distribution of workloads as the scale of questions and end users reach millions. Multiple servers and databases may use as the distribution system for both acrossing multiple resources and data backup. Users may assigned to different servers but the databases should be update simultaneously. 
