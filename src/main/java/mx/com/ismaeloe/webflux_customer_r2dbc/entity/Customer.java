package mx.com.ismaeloe.webflux_customer_r2dbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table("customers")
public class Customer {

	@Id
	@Column("idCustomer")
	private Integer	idCustomer;
	
	private String 	name;
	
	private Double	balance;
}
