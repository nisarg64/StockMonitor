# StockMonitor
## Pre-requisites:
1. Maven 3
2. Spring 4.0.3

## Running Instructions:
1. Go to the project directory.
2. Run "mvn clean install tomcat7:run"

## CRUD Operations:
1. Add a company:
  "curl --data "symbol=INTC" http://localhost:8080/add"
2. Get the current Stock price:
  "curl -i http://localhost:8080/get?symbol=INTC"
3. List all companies:
  "curl -i http://localhost:8080/list"
4. Delete a company:
  "curl -X "DELETE" http://localhost:8080/delete?symbol=INTC"
