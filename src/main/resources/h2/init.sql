CREATE TABLE customers (
 idCustomer bigint auto_increment,
 name		varchar(50),
 balance 	double,
 primary key (idCustomer)
) ;

CREATE TABLE CustomerTransaction (
 id 		bigint auto_increment,
 idCustomer bigint,
 amound		double,
 transaction_date timestamp,
 foreign key (idCustomer) references customers(idCustomer)
);