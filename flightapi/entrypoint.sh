#!/bin/sh

# Below shells script is required because the flask container need to wait for postgres db server to startup before
# accessing it below.

#RETRIES=10
#USER=postgres
#DATABASE=flights
#HOST=postgres

#until psql -h $HOST -U $USER -d $DATABASE -c "select 1" > /dev/null 2>&1 || [ $RETRIES -eq 0 ]; do
#  echo "Waiting for postgres server to start, $((RETRIES)) remaining attempts..."
#  RETRIES=$((RETRIES-=1))
#
#done

sleep 10 # wait until Postgresql container has been started

echo "Hope PostgreSQL started!"

java -jar /app/app.jar