FROM openjdk:8-jre-alpine

MAINTAINER YiMing jian "35@gmail.com"

ENV DIST_NAME=jeecf-manager \
    JAVA_OPTS=" -Xms512M -Xmx512M"

WORKDIR app

COPY docker-entrypoint.sh .
COPY $DIST_NAME.jar .

ENTRYPOINT ["sh", "docker-entrypoint.sh"]

EXPOSE 8801
