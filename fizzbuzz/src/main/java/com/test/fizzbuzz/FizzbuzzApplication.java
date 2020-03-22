package com.test.fizzbuzz;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FizzbuzzApplication implements CommandLineRunner  {

   
	@Resource(name="${fizzbizz.logic.implementation}")
	FizzBuzzLogic logic;

	public static void main(String[] args) {
		SpringApplication.run(FizzbuzzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		logic.getResponse().forEach(a -> System.out.println(a));;		

	}

}
