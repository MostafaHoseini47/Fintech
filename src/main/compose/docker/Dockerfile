
FROM openjdk:17
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    APP_SLEEP=10 \
    JAVA_OPTS="-Dspring.profiles.active=dev"
COPY ./entrypoint.sh /entrypoint.sh
RUN chmod +x entrypoint.sh
COPY *.jar /app.jar
CMD bash -c /entrypoint.sh
