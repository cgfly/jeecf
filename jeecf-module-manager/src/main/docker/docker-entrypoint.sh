#!/bin/bash
LOG_HOME=$JEECF_HOME/log 
CURRENT_TIME=$(date +%Y%m%d%H%M%S)
LOG_NAME=log.$CURRENT_TIME

FILE_PLUGIN_HOME="$JEECF_HOME/plugin" 
FILE_PLUGIN_UPLOAD_PATH="$FILE_PLUGIN_HOME/real" 
FILE_PLUGIN_UPLOAD_TMP_PATH="$FILE_PLUGIN_HOME/tmp" 
FILE_TMPL_HOME="$JEECF_HOME/tmpl" 
FILE_TMPL_DOWNLOAD_PATH="$FILE_TMPL_HOME/download" 
FILE_TMPL_UPLOAD_PATH="$FILE_TMPL_HOME/real" 
FILE_TMPL_UPLOAD_TMP_PATH="$FILE_TMPL_HOME/tmp"  

DB_URL="jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull" 

SERVER="--server.port=$SERVER_PORT"      
DB="--spring.datasource.primary.url=$DB_URL --spring.datasource.primary.username=$DB_USERNAME --spring.datasource.primary.password=$DB_PASSWORD" 
REDIS="--redis.host=$REDIS_HOST --redis.port=$REDIS_PORT" 
FILE="--file.plugin.upload.path=$FILE_PLUGIN_UPLOAD_PATH --file.plugin.upload.tmp.path=$FILE_PLUGIN_UPLOAD_TMP_PATH --file.tmpl.download.prefix.path=$FILE_TMPL_DOWNLOAD_PATH --file.tmpl.upload.path=$FILE_TMPL_UPLOAD_PATH --file.tmpl.upload.tmp.path=$FILE_TMPL_UPLOAD_TMP_PATH"

if [ ! -d $JEECF_HOME  ]; then
  mkdir $JEECF_HOME
fi

if [ ! -d $LOG_HOME  ]; then
  mkdir $LOG_HOME
fi

while true
do
  EXITS_DB=$(mysql -h${DB_HOST} -P${DB_PORT} -u${DB_USERNAME} -p${DB_PASSWORD}  -e "show databases like '$DB_NAME' ")
  if [ ! -n "$EXITS_DB" ]; then
    sleep 2
  else
    break
  fi
done

mysql -h${DB_HOST} -P${DB_PORT} -u${DB_USERNAME} -p${DB_PASSWORD} $DB_NAME < ./exec.sql
nohup java -jar $DIST_NAME.jar ${JAVA_OPTS} $SERVER $DB $REDIS $FILE > $lOG_HOME/$LOG_NAME
