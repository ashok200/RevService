package com.test.fizzbuzz;

import org.springframework.stereotype.Service;

@Service("ExtendedLogic")
public class ExtendedFizzBuzzLogic extends FizzBuzzService{

	protected boolean isFizz(int n) {
		return super.isFizz(n)||hasDigit(n, 3);
		
	}
	
	
	protected boolean isBuzz(int n) {
		return super.isBuzz(n)||hasDigit(n, 5);
	}
	
	private  boolean hasDigit(int num,int digit) {
		int rem;
		while (num > 0) {
			rem = num % 10;
			if (rem == digit)
				return true;
			num = num / 10;
		}
		return false;
	}
}




