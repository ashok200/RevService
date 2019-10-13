package com.revoult;

import java.math.BigDecimal;
import java.util.Arrays;

import com.revoult.exception.InSufficientBalanceException;
import com.revoult.exception.InvalidAccountException;
import com.revoult.status.Status;

public class TransferAction implements Actions {	
	
	private static Actions instance = new TransferAction();
	AccountDao dao = InMemoryAccountDao.getInstance();
	public static Actions getInstance() {
		return instance;
	}
	private TransferAction() {
		System.out.println("Private Action initialised");
	}
	private void init() {
		
	}

	
	@Override
	public  Boolean transfer(String fromAcc, String toAcc,double amt) throws InvalidAccountException,InSufficientBalanceException{
		System.out.println(Thread.currentThread().getName() +
                " start transfer ");
		Account fromAccount = dao.getUserAccount(fromAcc);
		if(fromAccount==null) {
			throw new InvalidAccountException(Status.FROM_ACCOUNT_INVALID);			
		}
		Account toAccount = dao.getUserAccount(toAcc);
		if(toAccount==null) {
			throw new InvalidAccountException(Status.TO_ACCOUNT_INVALID);
			
		}
		if(fromAcc.equals(toAcc))
			throw new InvalidAccountException(Status.TO_ACCOUNT_INVALID);
		BigDecimal amount = BigDecimal.valueOf(amt);
		
		Account[]  accounts = {fromAccount,toAccount};

		Arrays.sort(accounts, (Account o1, Account o2)-> (int)(o1.getAccountId() - o2.getAccountId()));
		synchronized (accounts[0]) {
			synchronized (accounts[1]) {
		 BigDecimal fromValue = fromAccount.getBalance();
		    if (amount.compareTo(fromValue) > 0) {
		    	throw new InSufficientBalanceException(Status.MESSAGE_INSUFFICIENT_FUND);
		    }
		         
		    BigDecimal toValue = toAccount.getBalance();
		    fromAccount.setBalance(fromValue.add(amount.negate()));
		    toAccount.setBalance(toValue.add(amount));
		    System.out.println(Thread.currentThread().getName() +
	                " end transfer ");
		     return Boolean.TRUE;
			}
		}
	}
	@Override
	public BigDecimal getBalance(String fromAcc) throws InvalidAccountException {
		Account fromAccount = dao.getUserAccount(fromAcc);
		if(fromAccount==null) {
			throw new InvalidAccountException(Status.FROM_ACCOUNT_INVALID);
			
		}
		return fromAccount.getBalance();
		
	}
	@Override
	public Account getAccount(String fromAcc)  {
		Account fromAccount = dao.getUserAccount(fromAcc);		
		return fromAccount;
		
	}

}
