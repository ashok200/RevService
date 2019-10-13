package com.revoult;

import java.math.BigDecimal;

import com.revoult.Account;


public interface AccountDao  {	
	public Account getUserAccount(String accountId);
	public Account getUserAccount(Long accountId);
	public void updateUserAccount(Account account,BigDecimal balance);
	

}
