package mx.com.ismaeloe.webflux_customer_r2dbc.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.ismaeloe.webflux_customer_r2dbc.entity.Customer;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

	@Modifying
	@Query("UPDATE customers SET balance = balance - :amount" +
			" WHERE idCustomer = :idCust AND balance >= :amount")
	public Mono<Boolean> updateCustomerBalance(int idCust ,double amount);
}
