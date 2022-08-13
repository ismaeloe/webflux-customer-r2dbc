package mx.com.ismaeloe.webflux_customer_r2dbc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionRequestDto;
import mx.com.ismaeloe.webflux_customer_r2dbc.dto.TransactionResponseDto;

import mx.com.ismaeloe.webflux_customer_r2dbc.service.TransactionService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer-api/v1/transaction")
public class CustomerTransactionController {

	@Autowired
	private TransactionService txService;
	
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
	
	@PostMapping("/v2")
	public Mono< ResponseEntity<TransactionResponseDto> > createTxSubscribe (@RequestBody Mono<TransactionRequestDto> txRequestDtoMono)
	{
		//return txRequestDtoMono.flatMap( txRequest -> this.txService.createTx( txRequest ) );
		 return txRequestDtoMono.doOnNext( tx -> {
			 								System.err.println("LOG 2, TX =" + tx);
		 									} )
				 				.flatMap( this.txService::createTx )
				 				.map( ResponseEntity::ok)
				 				.defaultIfEmpty( ResponseEntity.notFound().build());
	}

}
