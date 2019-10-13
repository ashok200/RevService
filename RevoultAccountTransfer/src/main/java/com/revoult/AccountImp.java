package com.revoult;

import java.math.BigDecimal;

public class AccountImp implements Account {

	private Long accountId;
	private String accountName;
	private BigDecimal balance;

	
	
	public AccountImp(Long accountId, String accountName, BigDecimal balance) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;

	}

	
	@Override
	public Long getAccountId() {
		// TODO Auto-generated method stub
		return accountId;
	}
	@Override
	public void setAccountId(Long accountId) {
		this.accountId=accountId;
		
	}
	@Override
	public String getAccountName() {
		// TODO Auto-generated method stub
		return accountName;
	}
	@Override
	public void setAccountName(String accountName) {
		// TODO Auto-generated method stub
		this.accountName=accountName;
	}
	@Override
	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	@Override
	public void setBalance(BigDecimal balance) {
		this.balance=balance;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountImp other = (AccountImp) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AccountImp [accountId=" + accountId + ", accountName=" + accountName + ", balance=" + balance + "]";
	}
	
	
	

	

}
