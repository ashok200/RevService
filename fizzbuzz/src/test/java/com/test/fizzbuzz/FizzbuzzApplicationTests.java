package com.test.fizzbuzz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FizzbuzzApplicationTests {
	//This has been set to 'BaseLogic' in application.properties
	@Resource(name="${fizzbizz.logic.implementation}")
	FizzBuzzLogic logic;

	//This service is supposed to throw exception as this is 
	//@Resource(name="ExtendedLogic")
	//FizzBuzzLogic unImplementedLogic;



	
	@Test
	void testFizzBuzz() {

		List<String> expectedResult = Arrays.asList( "Fizz", "Buzz", "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "Fizz", "Fizz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "FizzBuzz", "Buzz", "FizzBuzz", "FizzBuzz", "Buzz", "Buzz", "FizzBuzz", "Buzz", "Buzz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Buzz");

		List<String> actualResult = logic.getResponse();

		//System.out.println(actualResult);
		assertThat(actualResult, is(expectedResult));

	}

	@Test
	void testFizzBuzzWithWrongOrder() {
		//swapping the first 2 refer testFizzBuzz
		List<String> expectedResult = Arrays.asList( "Buzz","Fizz",  "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "Fizz", "Fizz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "FizzBuzz", "Buzz", "FizzBuzz", "FizzBuzz", "Buzz", "Buzz", "FizzBuzz", "Buzz", "Buzz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Buzz", "Fizz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Fizz", "Buzz", "Fizz", "FizzBuzz", "Fizz", "Buzz", "Fizz", "Fizz", "Buzz");

		List<String> actualResult = logic.getResponse();


		assertThat(actualResult, not(expectedResult));

	}

	


}
