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
  
##TODO
HandlerError