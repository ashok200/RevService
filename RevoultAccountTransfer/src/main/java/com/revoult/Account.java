package com.revoult;

import java.math.BigDecimal;

public interface Account {
	public Long getAccountId();
	public void setAccountId(Long accountId);
	public String getAccountName();
	public void setAccountName(String accountName);
	public BigDecimal getBalance();		
	public void setBalance(BigDecimal balance);
	
	public static  Account createAccount(Long id,String name,BigDecimal initialBalance) {
		return new AccountImp(id,name,initialBalance);
	}
	

}
