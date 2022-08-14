package mx.com.ismaeloe.webflux_customer_r2dbc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionRequestDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionResponseDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.entity.CustomerTransaction;
import mx.com.ismaeloe.webflux_customer_r2dbc.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer-api/v1/transaction")
public class CustomerTransactionController {

	@Autowired
	private TransactionService txService;
	/*
	 * Sin LOGs
	 */
	@PostMapping
	public Mono<TransactionResponseDto> createTx (@RequestBody Mono<TransactionRequestDto> txRequestDtoMono)
	{
		/*java.lang.IllegalStateException: block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-3
		 *
		 * System.err.println("TX =" + txRequestDtoMono.block() );
		 * Optional<TransactionRequestDto> op = txRequestDtoMono.blockOptional();
		 */
		
		//return txRequestDtoMono.flatMap( txRequest -> this.txService.createTx( txRequest ) );
		  return txRequestDtoMono.flatMap( this.txService::createTx );
	}
	
	/*
	 * LOG Before and After TX
	 */
	@PostMapping("/v2")
	public Mono< ResponseEntity<TransactionResponseDto> > createTxSubscribe (@RequestBody Mono<TransactionRequestDto> txRequestDtoMono)
	{
		//return txRequestDtoMono.flatMap( txRequest -> this.txService.createTx( txRequest ) );
		 return txRequestDtoMono.doOnNext( tx -> {
			 								System.err.println("LOG v2, TX.Request =" + tx);
		 									} )
				 				.flatMap( this.txService::createTx )
				 				.doOnNext( tx -> System.err.println("LOG v2, TX.Response =" + tx) )
				 				.map( ResponseEntity::ok)
				 				.defaultIfEmpty( ResponseEntity.notFound().build());
	}

	@GetMapping("/all")
	public Flux<CustomerTransaction> getAllTransactions() {
		return this.txService.findAll().doOnNext( tx -> System.err.println("GET /all TX " + tx ));
	}
	
	@GetMapping(value = "/{id}")
	public Mono< ResponseEntity<CustomerTransaction> > getTransactionsById(@PathVariable Integer id) {
		return this.txService.findTxById(id)
							.doOnNext( tx -> System.err.println("GET Path /" + id + ", TX =" + tx ) )
							.map( ResponseEntity::ok )
							.defaultIfEmpty( ResponseEntity.notFound().build());
	}

	@GetMapping
	public Flux<CustomerTransaction> getTransactionsByIdCustQuery(@RequestParam("idcust") Integer id) {
		return this.txService.findByTxIdCustomer(id)
							.doOnNext( tx -> System.err.println("GET Query /?idcust=" + id +  ", TX =" + tx ));
	}

	
}
