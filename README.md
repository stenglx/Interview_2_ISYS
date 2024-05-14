# TODO
- download api doc of current code
- add to backup branch
- add features + error handling (discussed)
- test features
- take notes of features and tests
- download second api 
- diff
- dicuss api breaking changes

- make code for 3rd
- save flatmap thingy as backup
- write a test 
- handle 
- analyze the impact of this change within and across potentially affected 
services

add this to new branch for blank code
create branch for features
add features, add new error handling 
add tests
download second openapi
do diff
argument

do third example
...

# Simple Spring Boot Microservice Demo
```docker run --name mariadb -d --rm -e MYSQL_ROOT_PASSWORD=mypass -e MYSQL_DATABASE=db_example -p 3306:3306 mariadb```
    run the App.class of each component 
    
We have 2 services and 1 Frontend.
To communicate with each other our microservices and components use HTTP calls fia Restful API. 

Validator classes to ensure input is alright 
e.g. number for lecture 
vaidation that a number consisting of two numbers seperated by a dot is present
    
## Features and Tests
For the features I needed to change frontend and backend accordingly. 
Frontend -> new button, action to method 
Method then gives HTTP call to backend
Backend -> safe delete from DB

TODO
If return of lecture delete fails
-> delete no employee 
-> return some other stuff 

### Delete lectures

### Delete employees and corresponding lectures

### Add testcases 

## OpenAPI Doc
-> only available for RestEndpoints, Main Controller = No Rest but just Controller
Document (Rest) Endpoints (HTTP) -> clients can use to interact with API
+ request & response structures
-> methods like updateFromDto not considered as only internally used althouh field is documented to provide overview on object (objects used in requests)

- run all as always
- http://127.0.0.1:8081/swagger-ui/index.html
- http://127.0.0.1:8082/swagger-ui/index.html

- http://127.0.0.1:8081/v3/api-docs
- http://127.0.0.1:8082/v3/api-docs

port: server:
  port: 8081
  
e.g. from application.yaml

- @RequestBody = method parameter bound to Body 
- @PathVariable = bind path varibale from uri to variable id 

Other possible: (catch all possible return codes)
no return but @ResponseCode for ResourceNotFoundException class
400 response code is mapped to this exception
-> exception thrown -> this is returned 

@ApiResponse(responseCode = "201", description = "Employee added successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "text/plain"))

### Diff
### Download api doc for differ
 #### curl -o /Users/Rina/Desktop/Interview_2_ISYS/openapi-docs/lectureService_doc_initial.json http://127.0.0.1:8082/v3/api-docs
 #### curl -o /Users/Rina/Desktop/Interview_2_ISYS/openapi-docs/employeeService_doc_initial.json http://127.0.0.1:8081/v3/api-docs
 
 same with other naming after features 

#### openapi-diff /Users/Rina/Desktop/test.json /Users/Rina/Desktop/test2.json
No changes found between the two specifications

openapi-diff /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/lectureService_initial.json /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/lectureService_new_version.json > /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/lecture_Service_diff.txt

openapi-diff /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/employeeService_initial.json /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/employeeService_new_version.json > /Users/Rina/Desktop/Monday_Interview_FinalTry/Interview_2_ISYS/openapi-docs/employee_Service_diff.txt

https://docs.github.com/en/rest/about-the-rest-api/breaking-changes?apiVersion=2022-11-28

-> describes api breaking and non breaking changes
#### analyze whether the implementations of the two feature requests are "API 
breaking"?


## Feature and else
Do in the lecture service (Lecture Controller) as LectureService is part of frontend and not service per se. 
For fronend I'd use flatMap
Code as backup auf git

-> Tests also for backend -> write test
