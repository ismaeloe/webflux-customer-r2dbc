-- Database: customerdb

-- DROP DATABASE IF EXISTS customerdb;

CREATE DATABASE customerdb
    WITH
    OWNER = ismaeloe
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False
 ;

CREATE TABLE customers (
 idCustomer SERIAL PRIMARY KEY,
 name		varchar(50),
 balance 	numeric(10,2)
) ;

CREATE TABLE CustomerTransaction (
 id 		SERIAL primary key,
 idCustomer bigint,
 amount		numeric(10,2),
 transaction_date timestamp,
 CONSTRAINT FK_IdCustomer FOREIGN KEY (idCustomer) REFERENCES customers(idCustomer)
);