package mx.com.ismaeloe.webflux_customer_r2dbc.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;


/*
 * org.springframework.r2dbc.BadSqlGrammarException: executeMany; bad SQL grammar [INSERT INTO customer_transaction (idCustomer, amount, transaction_date) VALUES ($1, $2, $3)]; nested exception is io.r2dbc.spi.R2dbcBadGrammarException: [42102] [42S02] Tabla "CUSTOMER_TRANSACTION" no encontrada
 * Table "CUSTOMER_TRANSACTION" not found; SQL statement:
 * INSERT INTO customer_transaction (idCustomer, amount, transaction_date) VALUES ($1, $2, $3) [42102-214]
 * SOLUTION: Add @Table
 */
@Table("CustomerTransaction")
@Data
@ToString
public class CustomerTransaction {

	@Id
	private Integer id;
	
	@Column("idCustomer")
	private Integer idCustomer;

	private Double  amount;
	private LocalDateTime transactionDate;
}
