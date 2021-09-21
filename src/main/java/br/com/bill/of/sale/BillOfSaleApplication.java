package br.com.bill.of.sale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class BillOfSaleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BillOfSaleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// if you want to enter the bank as soon as the application starts
	}
}
