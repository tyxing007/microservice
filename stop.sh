#!/usr/bin/env bash
kill $(cat auauth-service.pid)
kill $(cat eureka-server.pid)
kill $(cat api-client-server.pid)
kill $(cat erp-service.pid)
kill $(cat config.pid)