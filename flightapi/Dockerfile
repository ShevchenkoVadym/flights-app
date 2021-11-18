FROM openjdk:11.0.13-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar

COPY entrypoint.sh /app/entrypoint.sh
# Give execute permission to below file, so that the script can be executed by docker.
RUN chmod +x /app/entrypoint.sh

EXPOSE 9666

ENTRYPOINT ["/app/entrypoint.sh"]
