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

SERVER_NAME=$DEPLOY_DIR

if [ -z "$SERVER_NAME" ]; then
	SERVER_NAME=`hostname`
fi

PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$CONF_DIR" | grep "env=$ENV "|awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME does not started!"
    exit 1
fi

if [ "$1" != "skip" ]; then
$BIN_DIR/dump.sh
fi

echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
	kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
		PID_EXIST=`ps --no-heading -p $PID`
		if [ -n "$PID_EXIST" ]; then
			COUNT=0
			break
		fi
	done
done
echo "OK!"
echo "PID: $PIDS"
