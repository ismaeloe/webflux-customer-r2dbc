package mx.com.ismaeloe.webflux_customer_r2dbc.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.ismaeloe.webflux_customer_r2dbc.entity.CustomerTransaction;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerTransactionRepository 
		extends ReactiveCrudRepository<CustomerTransaction, Integer>{

	/*
	 * Get All Transaction By IdCustomer
	 */
	public Flux<CustomerTransaction> findByIdCustomer(Integer id);
	
}
