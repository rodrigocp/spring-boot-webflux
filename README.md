### Resume
Simple Micro-Service Spring Boot project.
### Services:
* One Redis Database
* One Mongo Database
* **Gateway Service** for authentication and routing
* **Session Service** for authorization requests (**RedisDB**)
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
* Do a POST Request:
```
$ curl --request POST --url http://localhost:8080/api/login --header 'content-type: application/json' --data '{ "username": "administrator", "password": "root" }'
```
* Do a GET Request for Session Details:
```
$ curl --request GET --url 'http://localhost:8080/api/sessions?token=aTG3kzYhXNuO7GimIPCQ' --header 'authorization: Bearer aTG3kzYhXNuO7GimIPCQ'
```
* Do a GET Request for User List:
```
$ curl --request GET --url 'http://localhost:8080/api/users/ --header 'authorization: Bearer aTG3kzYhXNuO7GimIPCQ'
```
