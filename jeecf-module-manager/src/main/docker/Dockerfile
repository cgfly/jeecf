FROM openjdk:8-jre-alpine

MAINTAINER YiMing jian "3502168163@qq.com"

ENV DIST_NAME=jeecf-manager \
    JAVA_OPTS=" -Xms512M -Xmx512M" \
    JEECF_HOME=/Users/jeecf \
    SERVER_PORT=8801 \
    DB_NAME=jeecf \
    DB_HOST="127.0.0.1" \
    DB_PORT="3306" \
    DB_USERNAME="root" \
    DB_PASSWORD="12345678" \
    REDIS_HOST="127.0.0.1" \
    REDIS_PORT="6379" 
    
WORKDIR app

COPY exec.sql .
COPY docker-entrypoint.sh .
COPY $DIST_NAME.jar .

RUN apk update
RUN apk add mysql-client

ENTRYPOINT ["sh", "docker-entrypoint.sh"]

EXPOSE $SERVER_PORT
