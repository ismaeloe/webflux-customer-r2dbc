package mx.com.ismaeloe.webflux_customer_r2dbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.CustomerDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer-api/v1")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	public Flux<CustomerDto> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping(value= "/{id}")
	public Mono<ResponseEntity<CustomerDto>> getCustomerById(@PathVariable("id") Integer idCust) {
		/*
		return this.service.getCustomerById(idCust)		//1. get Mono<CustomerDto)
							.map( cust -> ResponseEntity.ok(cust))	//2. trasform to ResponseEntity.ok(CustomerDto)
							.defaultIfEmpty( ResponseEntity.notFound().build() );	//3. if Empty return NOTFOUND
		*/
		return this.service.getCustomerById(idCust)		//1. 
							.map( ResponseEntity::ok )	//2. 
							.defaultIfEmpty( ResponseEntity.notFound().build() );	//3. 
	}
	
	@PostMapping
	public Mono<CustomerDto> createCustomer(@RequestBody Mono<CustomerDto> custDtoMono) {
		return this.service.createUser(custDtoMono);
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<CustomerDto>> updateCustomer(
												@PathVariable Integer id ,
												@RequestBody Mono<CustomerDto> custDtoMono)
	{
		return this.service.updateCustomer(id, custDtoMono)
							.map( ResponseEntity::ok )
							.defaultIfEmpty( ResponseEntity.notFound().build() );
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> deleteCustomer(@PathVariable int id) {	//int to Avoid Integer.NULL
		return this.service.deleteCustById(id);
	}
	
}
