package mx.com.ismaeloe.webflux_customer_r2dbc.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {
	
	private Integer idCustomer;
	private Double 	amount;
	private TransactionStatusEnum status;

}
