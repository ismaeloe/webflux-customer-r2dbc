package mx.com.ismaeloe.webflux_customer_r2dbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxCustomerR2dbcApplication implements CommandLineRunner{

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	public static void main(String[] args) {
		
		SpringApplication.run(WebfluxCustomerR2dbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		System.err.println("Active Profile =" + activeProfile );	
	}

}
