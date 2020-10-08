# build, test and run

-need maven
-JDK 11

### build
```
./mvnw clean install
```

### run
It will start with port 8080

```
./mvnw spring-boot:run
```

### test the API 

It is available behind http://localhost:8080/api/v1/product

#### Create
```
curl -d '{"name":"product1", "price": 2.0}' -H 'Content-Type: application/json' http://localhost:8080/api/v1/product
```
#### Read all

```
curl -v http://localhost:8080/api/v1/product
```

### Read one
http://localhost:8080/api/v1/product/{id}
id must be the id of the product
please replace {id} throw the real id

```
curl -v http://localhost:8080/api/v1/product/{id}
```
#### Update
http://localhost:8080/api/v1/product/{id}
id must be the id of the product
please replace {id} throw the real id

```
curl -d '{"name":"product5", "price": 3.0}' -H 'Content-Type: application/json' -X PUT http://localhost:8080/api/v1/product/{id}
```

#### Delete
http://localhost:8080/api/v1/product/{id}
id must be the id of the product
please replace {id} throw the real id
```
curl -X DELETE http://localhost:8080/api/v1/product/{id}
```



