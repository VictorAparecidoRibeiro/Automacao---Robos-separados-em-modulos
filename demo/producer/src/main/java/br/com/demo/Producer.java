package br.com.demo;

import br.com.demo.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Producer {

	public static void main(String[] args) {

		SpringApplication.run(Crawler.class, args);
	}

}
