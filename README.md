# order
 Simple spring boot system with stock, order, and customer APIs.
 
 To run the application, it is necessary to create the database, follow:
 
 https://github.com/rubaosaad/order/blob/main/src/main/resources/database/database.txt
 
 After that, check it out if the credentials of database, on config.properties are ok. 
 
 Run application.
 
 To call apis, follow some curls:
 
 User:
	ADD:
		curl --location --request POST 'http://localhost:8080/order/user' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		"name": "Rubens",
		"email":"rubens.saad@gmail.com"
		}'
	 
	UPDATE 
	 	curl --location --request PATCH 'http://localhost:8080/order/user' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		"id": 1,
		"name": "Rubens",
		"email":"rubens.saad@gmail.com"
		}'
	
	DELETE	
	 	curl --location --request DELETE 'http://localhost:8080/order/user' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		"id": 1
		}'
	
	GET	
	 	curl --location --request GET 'http://localhost:8080/order/user/1' \
		--header 'Content-Type: application/json' \
		--data-raw ''
	
	GET ALL	
	 	curl --location --request GET 'http://localhost:8080/order/user' \
		--header 'Content-Type: application/json' \
		--data-raw ''		
	 
Item:
	ADD:
		curl --location --request POST 'http://localhost:8080/order/item' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"name": "Cerveja"
		}'
	
	UPDATE:
 		curl --location --request PATCH 'http://localhost:8080/order/item' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2,
			"name": "Cerveja"
		}'
	
	DELETE:
	 	curl --location --request DELETE 'http://localhost:8080/order/item' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2
		}'
		
	GET	
	 	curl --location --request GET 'http://localhost:8080/order/item/1' \
		--header 'Content-Type: application/json' \
		--data-raw ''
	
	GET ALL	
	 	curl --location --request GET 'http://localhost:8080/order/item' \
		--header 'Content-Type: application/json' \
		--data-raw ''
 
Stock:
	ADD:
		curl --location --request POST 'http://localhost:8080/order/stock' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"creationDate": "2022-05-12 18:52:29.261",
			"item": 2,
			"quantity": 44
		}'
	UPDATE:
		curl --location --request PATCH 'http://localhost:8080/order/stock' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2,
			"creationDate": "2022-05-12 18:52:29.261",
			"item": 2,
			"quantity": 44
		}'	
	DELETE:
		curl --location --request DELETE 'http://localhost:8080/order/stock' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2
		}'	
		
	GET	
	 	curl --location --request GET 'http://localhost:8080/order/stock/2' \
		--header 'Content-Type: application/json' \
		--data-raw ''
	
	GET ALL	
	 	curl --location --request GET 'http://localhost:8080/order/stock' \
		--header 'Content-Type: application/json' \
		--data-raw ''
		
Order:
	ADD:
		curl --location --request POST 'http://localhost:8080/order/order' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"item": 2,
			"quantity": 2,
			"creationDate": "2022-05-12 18:52:29.261",
			"user": 1
		}'
	UPDATE:
		curl --location --request PATCH 'http://localhost:8080/order/order' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2,
			"item": 2,
			"quantity": 2,
			"creationDate": "2022-05-12 18:52:29.261",
			"user": 1
		}'
	DELETE:
		curl --location --request DELETE 'http://localhost:8080/order/order' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"id": 2
		}'
		
	GET	
	 	curl --location --request GET 'http://localhost:8080/order/order/2' \
		--header 'Content-Type: application/json' \
		--data-raw ''
	
	GET ALL	
	 	curl --location --request GET 'http://localhost:8080/order/order' \
		--header 'Content-Type: application/json' \
		--data-raw ''