package mx.com.ismaeloe.webflux_customer_r2dbc.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDto {

	private Integer idCustomer;
	private String  name;
	private Double balance;
}
