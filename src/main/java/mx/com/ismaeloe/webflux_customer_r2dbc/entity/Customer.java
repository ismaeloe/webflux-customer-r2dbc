package mx.com.ismaeloe.webflux_customer_r2dbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table("customers")
public class Customer {

	@Id
	private Integer	idCustomer;
	private String 	name;
	private Double	balance;
}
