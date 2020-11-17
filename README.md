### Resume
Simple Micro-Service Spring Boot project.

### Database:
* Redis Database (for the cache)
* Mongo Database

### Services:
* **Gateway Service** for authentication and routing
* **Account Service** for registered users (**MongoDB**)

### Build
* Run Gradle Build:
```
$ gradle clean build -x test
```
* Run Docker Compose
```
$ docker-compose up
```
* Do a GET Request for User List:
```
$ curl --request GET --url http://localhost:8080/api/accounts/ --header 'Authorization: Basic YWRtaW5pc3RyYXRvcjpyb290'
```
* Default User and Password:
```
Username: administrator
Password: root
```