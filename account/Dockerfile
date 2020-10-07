FROM		openjdk:11-jre-slim
ENV			APP=/home/root/account-service/
WORKDIR		$APP
COPY		./build/libs/*.jar $APP/service.jar
ENTRYPOINT	["java", "-jar", "service.jar"]