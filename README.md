#v0.1 webflux-customer
Spring WebFlux Reactor Customer Service with H2

#v0.2:
## 0.2.1 Fix idCustomer field
## 0.2.2 Add profiles
## 0.2.3 Rename service/DataSetupService.java to config/H2DevDataSetupService.java
## 0.2.4 Add @Profile("dev") H2DevDataSetupService.java

#0.2.1 command: HTTP POST "/customer-api/v1" [ExceptionHandlingWebHandler]

 private Integer idCustomer;
	
	we got error:
	org.springframework.r2dbc.BadSqlGrammarException: executeMany; bad SQL grammar [INSERT INTO customers (name, balance) VALUES ($1, $2)];
	Caused by: io.r2dbc.spi.R2dbcBadGrammarException: Columna "id_customer" no encontrada
	Column "id_customer" not found; SQL statement:

#Solution: Add @Column
  	@Column("idCustomer")
	private Integer	idCustomer;


#0.2.2 
Add spring.profiles.active=dev  in application.properties
Add application-dev.properties

##info:
if we use @Value("${spring.profiles.active}") and the property is not in application.properties

   we got the error:
	org.springframework.beans.factory.BeanCreationException:
	Error creating bean with name 'webfluxCustomerR2dbcApplication':Injection of autowired dependencies failed; nested
	java.lang.IllegalArgumentException: Could not resolve placeholder 'spring.profiles.active' in value "${spring.profiles.active}"

#v0.3:
##0.3.1 Add CustomerTransactionController POST 
##0.3.2 Add @Table("CustomerTransaction") public class CustomerTransaction

#0.3.2 
  we got the error:
	org.springframework.r2dbc.BadSqlGrammarException: executeMany; bad SQL grammar [INSERT INTO customer_transaction (idCustomer, amount, transaction_date) VALUES ($1, $2, $3)]; nested exception is io.r2dbc.spi.R2dbcBadGrammarException: [42102] [42S02] Tabla "CUSTOMER_TRANSACTION" no encontrada
  	Table "CUSTOMER_TRANSACTION" not found; SQL statement:
  	INSERT INTO customer_transaction (idCustomer, amount, transaction_date) VALUES ($1, $2, $3) [42102-214]
 
  SOLUTION: Add @Table
 
 	@Table("CustomerTransaction")
  	public class CustomerTransaction

#v0.4: GET Transactions
 ## Add GET /customer-api/v1/{id}/transactions
 	http://localhost:8092/customer-api/v1/1/transactions
 	
 ## Add GET /customer-api/v1/transaction/all
 	http://localhost:8092/customer-api/v1/transaction/all
 	
 ## Add GET /customer-api/v1/transaction/{id}
 	http://localhost:8092/customer-api/v1/transaction/5
 	
 ## Add GET /customer-api/v1/transaction/?idcust
 	http://localhost:8092/customer-api/v1/transaction?idcust=1

#v0.5 POSTGRES Docker
##Add docker-compose.yaml
  >docker-compose up
  http://localhost:9000
  Connection.host = postgres (the docker service name)
  
    
##TODO
Add h2-console
Add Validation
HandlerError
