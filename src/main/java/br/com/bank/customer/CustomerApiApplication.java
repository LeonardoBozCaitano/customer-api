package br.com.bank.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CustomerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

}
