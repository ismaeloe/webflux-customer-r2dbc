package mx.com.ismaeloe.webflux_customer_r2dbc.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
//@Table("CustomerTransaction")
public class CustomerTransaction {

	@Id
	private Integer id;
	private Integer idCustomer;
	private Double  amount;
	private LocalDateTime transactionDate;
}
