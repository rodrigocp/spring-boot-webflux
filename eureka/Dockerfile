FROM		openjdk:11-jre-slim
ENV			APP=/home/root/eureka-service/
WORKDIR		$APP
COPY		./build/libs/*.jar $APP/service.jar
ENTRYPOINT	["java", "-jar", "service.jar"]