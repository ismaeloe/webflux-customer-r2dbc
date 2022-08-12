package mx.com.ismaeloe.webflux_customer_r2dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.CustomerDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.repository.CustomerRepository;
import mx.com.ismaeloe.webflux_customer_r2dbc.util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository dao;

	public Flux<CustomerDto> getAll() {
		//return this.dao.findAll().map(cust -> EntityDtoUtil.toDto(cust));
		  return this.dao.findAll().map(EntityDtoUtil::toDto);
	}
	
	public Mono<CustomerDto> getCustomerById(final Integer id) {
		return this.dao.findById(id).map( EntityDtoUtil::toDto );
	}
	
	public Mono<CustomerDto> createUser(Mono<CustomerDto> monoCustDto) {
		
		/*ok
		return monoCustDto.map( monoCust -> EntityDtoUtil.toEntity(monoCust) )
							.flatMap( customer -> this.dao.save(customer) )
							.map(EntityDtoUtil::toDto);
		*/
		return monoCustDto.map( EntityDtoUtil::toEntity )
							.flatMap( this.dao::save )
							.map(EntityDtoUtil::toDto);
	}
	
	public Mono<CustomerDto> updateCustomer(Integer idCust ,Mono<CustomerDto> custDtoMono) {
		
		return this.dao.findById(idCust)	//1. Find Entity to Save
						.flatMap( cust ->	//2. Operation and Return Transformed 
									custDtoMono.map(EntityDtoUtil::toEntity)	//3. Transform Dto to Entity
													.doOnNext( e -> e.setIdCustomer(idCust)))	//4. Set idCust to Entity
								.flatMap( this.dao::save)	//5. Save Entity and Return Entity
								.map(EntityDtoUtil::toDto); //6. Transform Entity to Dto 
	}
	
	public Mono<Void> deleteCustById(Integer idCust) {
		return this.dao.deleteById(idCust);
	}
	
}
