#!/bin/sh

echo "The application will start in ${APP_SLEEP}s..." && sleep ${APP_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED -jar app.jar ${JAR_OPTS}
