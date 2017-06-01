#!/bin/bash
ENV=$1
if [ -z "$ENV" ]; then
    echo "ERROR:PLEASE SPEC ENV ARGS,SUCH AS test,develop,product..  "
    echo "app exit"
    exit 1
fi
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
DUBBO_FILE=conf/$ENV/dubbo.properties
SERVER_NAME=`sed '/dubbo.application.name/!d;s/.*=//' conf/$ENV/*.properties | tr -d '\r'`
SERVER_PORT=`sed '/dubbo.protocol.port/!d;s/.*=//' conf/$ENV/*.properties | tr -d '\r'`
JAVA_OPTS=`sed '/dubbo.jvm.opts/!d;s/[^=]*=//' conf/$ENV/*.properties | tr -d '\r'`
SPRING_CONFIG=`sed '/dubbo.spring.config/!d;s/.*=//' conf/$ENV/*.properties | tr -d '\r'`
LOGS_FILE=`sed '/dubbo.log4j.file/!d;s/.*=//' conf/$ENV/*.properties | tr -d '\r'`
DOUBBO_CONTAINER=`sed '/dubbo.container/!d;s/.*=//' conf/$ENV/*.properties | tr -d '\r'`
if [ "$DOUBBO_CONTAINER" = "springWeb" ]; then
SPRING_CONFIG="spring/springWeb-start.xml"
fi
echo "start with spring config $SPRING_CONFIG"
if [ -z "$SERVER_NAME" ]; then
	SERVER_NAME=`hostname`
fi

PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$CONF_DIR" | grep "env=$ENV "|awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
	SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
	if [ $SERVER_PORT_COUNT -gt 0 ]; then
		echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
		exit 1
	fi
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
	LOGS_DIR=`dirname $LOGS_FILE`
else
	LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
	mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS="-Xbootclasspath/a:. $JAVA_OPTS -Denv=$ENV -Ddubbo.properties.file=$DUBBO_FILE -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_MEM_OPTS=""
BITS=`file $JAVA_HOME/bin/java | grep 64-bit`
if [ -n "$BITS" ]; then
    let memTotal=`cat /proc/meminfo |grep MemTotal|awk '{printf "%d", $2/1024 }'`
    if [ $memTotal -gt 2500 ];then
        JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
    else 
        JAVA_MEM_OPTS=" -server -Xmx1g -Xms1g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
    fi
else
	JAVA_MEM_OPTS=" -server -Xms1024m -Xmx1024m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the $SERVER_NAME ...\c"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS -classpath $CONF_DIR:$LIB_JARS com.laughing2b.common.shell.SpringMain $SPRING_CONFIG> $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    	COUNT=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | grep "env=$ENV "| awk '{print $2}' | wc -l`
	if [ $COUNT -gt 0 ]; then
		break
    else
        echo ''
        echo 'start app fail !'
        exit 1
	fi
done
echo "OK!"
PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | grep "env=$ENV "| awk '{print $2}'`
                           echo "PID: $PIDS"
                           echo "STDOUT: $STDOUT_FILE"
