package mx.com.ismaeloe.webflux_customer_r2dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionRequestDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionResponseDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionStatusEnum;
import mx.com.ismaeloe.webflux_customer_r2dbc.entity.CustomerTransaction;
import mx.com.ismaeloe.webflux_customer_r2dbc.repository.CustomerRepository;
import mx.com.ismaeloe.webflux_customer_r2dbc.repository.CustomerTransactionRepository;
import mx.com.ismaeloe.webflux_customer_r2dbc.util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private CustomerTransactionRepository custTxDao;
		
	public Mono<TransactionResponseDto> createTx(final TransactionRequestDto txRequestDto) {

		/*
		return this.customerDao.updateCustomerBalance( 	txRequestDto.getIdCustomer() , //1. return Mono<Boolean>
														txRequestDto.getAmount() )
									.filter( bool -> bool.booleanValue())	//2. Filter if Boolean is TRUE
									.map( bool -> EntityDtoUtil.toEntity(txRequestDto) ) //3. trasform to Entity
									.flatMap( custTx -> this.custTxDao.save( custTx ) )	 //4. save Entity
									.map( custTx -> EntityDtoUtil.toDto( txRequestDto, TransactionStatusEnum.APPROVED) ) //5. trasform TransactionResponseDto
									.defaultIfEmpty( EntityDtoUtil.toDto( txRequestDto, TransactionStatusEnum.DECLINE) );//6. trasform TransactionResponseDto
		*/
		
		return this.customerDao.updateCustomerBalance( 	txRequestDto.getIdCustomer() , //1. 
														txRequestDto.getAmount() )
									.filter( Boolean::booleanValue )		//2. 
									.map( bool -> EntityDtoUtil.toEntity(txRequestDto) ) //3
									.flatMap( this.custTxDao::save )  		//4
									.map( custTx -> EntityDtoUtil.toDto( txRequestDto, TransactionStatusEnum.APPROVED) )
									.defaultIfEmpty( EntityDtoUtil.toDto( txRequestDto, TransactionStatusEnum.DECLINE) );
	}

	public Mono<CustomerTransaction> findTxById(Integer id) {
		return this.custTxDao.findById(id);
	}

	public Flux<CustomerTransaction> findByTxIdCustomer(Integer id) {
		return this.custTxDao.findByIdCustomer(id);
	}

	public Flux<CustomerTransaction> findAll() {
		return this.custTxDao.findAll();
	}

}
