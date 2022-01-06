#!/bin/bash

counter=0
until [ $counter -gt 120 ]
do
  OUTPUT=$(curl -s http://localhost:8080/test)
  echo "${counter} th calling... ${OUTPUT}"
  ((counter++))
  sleep 1
done
