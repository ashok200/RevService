package com.revoult;

import java.math.BigDecimal;
import java.util.HashMap;

public class InMemoryAccountDao implements AccountDao {
	private static HashMap<Long,Account> accountSet= new HashMap<Long,Account>();
	static {
		loadData();
	}

	
	private static  void loadData() {
		Account acc1 = new AccountImp(1L,"ashok",BigDecimal.valueOf(100));
		Account acc2 = new AccountImp(2L,"revoult",BigDecimal.valueOf(100));	
		Account acc3 = new AccountImp(3L,"nomura",BigDecimal.valueOf(100));	
		Account acc4 = new AccountImp(4L,"db",BigDecimal.valueOf(100));	
		Account acc5 = new AccountImp(5L,"db1",BigDecimal.valueOf(100));	
		Account acc6 = new AccountImp(6L,"db2",BigDecimal.valueOf(100));	
		Account acc7 = new AccountImp(7L,"db3",BigDecimal.valueOf(100));	
		Account acc8 = new AccountImp(8L,"db4",BigDecimal.valueOf(100));	
		Account acc9 = new AccountImp(9L,"db5",BigDecimal.valueOf(100));	
		Account acc10 = new AccountImp(10L,"db6",BigDecimal.valueOf(100));	
		accountSet.put(acc1.getAccountId(), acc1);
		accountSet.put(acc2.getAccountId(), acc2);
		accountSet.put(acc3.getAccountId(), acc3);
		accountSet.put(acc4.getAccountId(), acc4);
		accountSet.put(acc5.getAccountId(), acc5);
		accountSet.put(acc6.getAccountId(), acc6);
		accountSet.put(acc7.getAccountId(), acc7);
		accountSet.put(acc8.getAccountId(), acc8);
		accountSet.put(acc9.getAccountId(), acc9);
		accountSet.put(acc10.getAccountId(), acc10);
			

		
		
	}
	
	private static InMemoryAccountDao instance = new InMemoryAccountDao();
	public static InMemoryAccountDao getInstance() {
		return instance;
	}
	private InMemoryAccountDao() {
		loadData();
	}

	@Override
	public Account getUserAccount(String accountId) {
		Account account = accountSet.get(Long.valueOf(accountId));
		return account;
	}
	
	@Override
	public Account getUserAccount(Long accountId) {
		Account account = accountSet.get(accountId);
		return account;
	}

	@Override
	public void updateUserAccount(Account account, BigDecimal balance) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(InMemoryAccountDao.getInstance().getUserAccount("1"));
		
	}

}
