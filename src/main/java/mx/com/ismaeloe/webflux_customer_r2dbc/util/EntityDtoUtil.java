package mx.com.ismaeloe.webflux_customer_r2dbc.util;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.CustomerDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionRequestDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionResponseDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionStatusEnum;
import mx.com.ismaeloe.webflux_customer_r2dbc.entity.Customer;
import mx.com.ismaeloe.webflux_customer_r2dbc.entity.CustomerTransaction;

public class EntityDtoUtil {

	public static CustomerDto toDto (Customer cust) {
		
		CustomerDto dto = new CustomerDto();
		BeanUtils.copyProperties(cust, dto);
		return dto;
	}
	
	public static Customer toEntity (CustomerDto dto) {

		Customer cust = new Customer();
		BeanUtils.copyProperties( dto ,cust );
		return cust;
	}

	public static CustomerTransaction toEntity( TransactionRequestDto txRequestDto) {
		
		CustomerTransaction custTx = new CustomerTransaction();
		
		custTx.setIdCustomer( txRequestDto.getIdCustomer() );
		custTx.setAmount( txRequestDto.getAmount() );
		custTx.setTransactionDate( LocalDateTime.now() );

		return custTx;
	}
	
	public static TransactionResponseDto toDto( TransactionRequestDto txRequest ,TransactionStatusEnum txStatus ) {
		
		TransactionResponseDto responseDto = new TransactionResponseDto();
		
		responseDto.setAmount( txRequest.getAmount() );
		responseDto.setIdCustomer(txRequest.getIdCustomer() );
		responseDto.setStatus(txStatus);

		return responseDto;
	}
	
}
