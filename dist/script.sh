#!/bin/bash

#export JAVA_HOME=
#export PATH = $JAVA_HOME/bin:$PATH
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


#当前时间
current_time=$(date +%Y%m%d%H%M%S)
#jar包名称
jar_name=jeecf-manager.jar
#jeecf 文件存储目录
jeecf_home=/Users/jianyiming/jeecf
#日志输出目录
log_home=$jeecf_home/log
#日志名称
log_name=log.$current_time
#堆内存设置
JAVA_OPTS=" -Xms512M -Xmx512M "
#数据库连接url
db_url="jdbc:mysql://127.0.0.1:3306/jeecf?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
#数据库用户名
db_username="root"
#数据库密码
db_password="12345678"
#redis 地址
redis_host="127.0.0.1"
#redis 端口号
redis_port="6379"

file_plugin_home="$jeecf_home/plugin"
file_plugin_upload_path="$file_plugin_home/real"
file_plugin_upload_tmp_path="$file_plugin_home/tmp"

file_tmpl_home="$jeecf_home/tmpl"
file_tmpl_download_path="$file_tmpl_home/download"
file_tmpl_upload_path="$file_tmpl_home/real"
file_tmpl_upload_tmp_path="$file_tmpl_home/tmp"      

db="--spring.datasource.primary.url=$db_url --spring.datasource.primary.username=$db_username --spring.datasource.primary.password=$db_password"
redis="--redis.host=$redis_host --redis.port=$redis_port"
file="--file.plugin.upload.path=$file_plugin_upload_path --file.plugin.upload.tmp.path=$file_plugin_upload_tmp_path --file.tmpl.download.prefix.path=$file_tmpl_download_path --file.tmpl.upload.path=$file_tmpl_upload_path --file.tmpl.upload.tmp.path=$file_tmpl_upload_tmp_path"



delay_time=20

if [ ! -d $jeecf_home  ]; then
  mkdir $jeecf_home
fi

if [ ! -d $log_home  ]; then
  mkdir $log_home
fi


start(){
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -n "$pid" ]; then
    echo '进程已存在'
  else
    nohup java -jar $jar_name $JAVA_OPTS $db $redis $file > $log_home/$log_name &
    echo "进程启动中，请等待......"
    sleep $delay_time
    pid0=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
    if [ -n "$pid0" ]; then
      echo '进程启动成功'
    else
      echo '进程启动失败,请查看日志:' $log_home/$log_name
    fi
  fi
}

stop(){
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -n "$pid" ]; then
    kill -9 $pid
    echo '进程已停止'
  else
    echo '进程没有启动'
  fi
}

state(){
  pid=$(ps -ef | grep $jar_name | grep -v grep | awk '{print $2}')
  if [ -n "$pid" ]; then
    echo '进程运行中'
  else
    echo '进程没有启动'
  fi
}



case $1 in
  start)
		start;
    ;;
  stop)
		stop;
    ;;
  restart)
      stop;
      start;
    ;;
  state)
    state;
    ;;
  *)
  
    echo "Usage: script.sh {start|stop|restart|state}" 
    ;;
esac

exit 0
