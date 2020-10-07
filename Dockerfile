#FROM		gradle:jdk11 AS builder
#ENV			OUTPUT=/home/root/build/
#WORKDIR		$OUTPUT
#COPY		. .
#RUN			gradle -x test build --no-daemon
#RUN			ls $OUTPUT
#
#FROM		openjdk:11-jre-slim
#ENV			APP=/home/root/eureka-service/
#WORKDIR		$APP
#COPY		--from=builder /home/root/build/eureka/build/libs/*.jar $APP/service.jar
#ENTRYPOINT	["java", "-jar", "service.jar"]

#FROM		openjdk:11-jre-slim
#ENV			APP=/home/root/account-service/
#WORKDIR		$APP
#COPY		--from=builder /home/root/build/account/build/libs/*.jar $APP/service.jar
#ENTRYPOINT	["java", "-jar", "service.jar"]
#
#FROM		openjdk:11-jre-slim
#ENV			APP=/home/root/session-service/
#WORKDIR		$APP
#COPY		--from=builder /home/root/build/session/build/libs/*.jar $APP/service.jar
#ENTRYPOINT	["java", "-jar", "service.jar"]
#
#FROM		openjdk:11-jre-slim
#ENV			APP=/home/root/gateway-service/
#WORKDIR		$APP
#COPY		--from=builder /home/root/build/gateway/build/libs/*.jar $APP/service.jar
#ENTRYPOINT	["java", "-jar", "service.jar"]