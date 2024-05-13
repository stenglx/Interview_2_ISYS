# Simple Spring Boot Microservice Demo
```docker run --name mariadb -d --rm -e MYSQL_ROOT_PASSWORD=mypass -e MYSQL_DATABASE=db_example -p 3306:3306 mariadb```
    run the App.class of each component 
    
We have 2 services and 1 Frontend.
To communicate with each other our microservices and components use HTTP calls fia Restful API. 
    
## Features and Tests
For the features I needed to change frontend and backend accordingly. 
Frontend -> new button, action to method 
Method then gives HTTP call to backend
Backend -> safe delete from DB

TODO
If return of lecture delete fails
-> delete no employee 
-> return some other stuff 


## OpenAPI Doc
run all as previous
view localhost 8081/82 ...

-> only available for RestEndpoints, Main Controller = No Rest but just Controller
### Diff
For diff I used 


## Feature and else
Do in the lecture service (Lecture Controller) as LectureService is part of frontend and not service per se. 
For fronend I'd use flatMap
Code as backup auf git

-> Tests also for backend -> write test
