## stock-management

REST APIs to handle stock availability. 

### start the service

```bash
git clone https://vinayanayak@bitbucket.org/vinayanayak/stock-management.git
cd stock-management-api
mvn clean install
mvn spring-boot:run
```

### run the tests
```bash
mvn test
```

### APIs
- update stock of a particular product

```bash
curl -X POST \
  http://localhost:8080/updateStock \
  -H 'Content-Type: application/json' \
  -d '{
	"id": "0003",
	"timeStamp": "2018-01-11T17:52:15.015Z",
	"productId" : "productId",
	"quantity" : "100"
}'
```
response
status-code 201 if everything went well and the new entry for stock was accepted
status-code 204 if outdated stock, because a newer stock was processed first

+ get current stock available of a particular
product

```bash
curl -X GET \
  http://localhost:8080/stock/{productId}
```

- get statistics about the stocks

```bash
curl -X GET \
  'http://localhost:8080/statistics?time=today'
```

### TODOs and improvements 
+ more test coverage
- rewrite in scala
