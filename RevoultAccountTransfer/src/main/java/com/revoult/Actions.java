package com.revoult;

import java.math.BigDecimal;

import com.revoult.exception.InSufficientBalanceException;
import com.revoult.exception.InvalidAccountException;

public interface Actions  {			
	
	public Boolean transfer(String fromAccount,String toAccount,double amount) throws InvalidAccountException,InSufficientBalanceException;
	public BigDecimal getBalance(String fromAccount) throws InvalidAccountException;
	public Account getAccount(String fromAccount) ;
	public static Actions getTransferActions() {
		return TransferAction.getInstance();
	}
	

}
