#!/usr/bin/env bash
gradle build
gradle bootRun -p config &
gradle bootRun -p eureka-server &
gradle bootRun -p erp-server &
gradle bootRun -p auth-service &
gradle bootRun -p api-client-server &
