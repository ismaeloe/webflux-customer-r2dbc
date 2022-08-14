package mx.com.ismaeloe.webflux_customer_r2dbc.config;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Profile("dev")
@Service
public class H2DevDataSetupService  implements CommandLineRunner {

	@Value("classpath:h2/init.sql")
	private Resource initSql;
	
	@Autowired
	private R2dbcEntityTemplate entityTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.err.println("resource =" + initSql.getFilename() );
		
		if ( initSql.exists() && initSql.isFile() && initSql.isReadable() )
		{	
			String query = StreamUtils.copyToString( initSql.getInputStream(), StandardCharsets.UTF_8);

			System.out.println("SQL:\n" + query );
			
			this.entityTemplate.getDatabaseClient()	// 1. Get Reactive Data Connection
								.sql(query)		// 2. Run Query
								.then()			// 3. return Mono<Void>
								.subscribe();	// 4. Publisher
		}else {
			System.err.println("Not Valid Resource =" + initSql.getFilename() );
			System.exit(1);
		}

		
	}

	
}
