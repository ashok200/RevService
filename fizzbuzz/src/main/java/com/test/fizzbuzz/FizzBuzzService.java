package com.test.fizzbuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service("BaseLogic")
public class FizzBuzzService implements FizzBuzzLogic {

    

	@Override
	public  List<String> getResponse() {
		IntStream intstream = IntStream.rangeClosed(1, 100);

		List<String> listResponse = new ArrayList<String>();
		intstream.forEach(n -> {
			StringBuilder sBuilder = new StringBuilder("");
			sBuilder.append(isFizz(n)?"Fizz":"");
			sBuilder.append(isBuzz(n)?"Buzz":"");
			if(!sBuilder.toString().isEmpty()) {
				listResponse.add(sBuilder.toString());
				//System.out.println(n+" = "+sBuilder.toString());
			}


		});
		return listResponse;

	}
	
	protected boolean isFizz(int n) {
		return n%3==0?true:false;
	}
	
	
	protected boolean isBuzz(int n) {
		return n%5==0?true:false;
	}

}
