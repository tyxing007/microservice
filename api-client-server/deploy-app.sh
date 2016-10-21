#!/usr/bin/env bash
# COMMAND LINE VARIABLES
#command to execute
# Ex: start | stop
COMMAND=$1

function stopProduction(){
 echo " "
    echo "Stoping a process on port: 8080"
    fuser -n tcp -k 8080
    echo " "
}

function startProduction(){
  echo " "
  echo "Starting an application on port: 8080  in production mode"
  nohup java -jar ./build/libs/dengo-erp-0.0.1-SNAPSHOT.jar --server.port=8080 --spring.profiles.active=production | tee log.txt &
  tail -f nohup.out
}

case ${COMMAND} in
start)
startProduction
;;
stop)
stopProduction
;;
esac
