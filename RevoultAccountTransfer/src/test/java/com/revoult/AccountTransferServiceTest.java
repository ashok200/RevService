package com.revoult;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assertions

import java.math.BigDecimal;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Rule;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.revoult.exception.InSufficientBalanceException;
import com.revoult.exception.InvalidAccountException;
import com.revoult.status.Status;

public class AccountTransferServiceTest {
	
	ServiceHelper helper;
	
	@Before
	public void setup() {
		helper = new ServiceHelper();
		
	}

	

	@Test	
	public void testWithDraw()  {
		String acc1="1" ;
		String acc2 ="2";
		Status status = helper.transfer(acc1, acc2, Double.valueOf(10));
		assertTrue(Status.STATUS_SUCCESS.equals(status.getStatus()));
		assertTrue(BigDecimal.valueOf(90.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc1).getMessage()))));
		assertTrue(BigDecimal.valueOf(110.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc2).getMessage()))));
		
	}
	
	@Test
	public void testWithDeposit()  {	
		String acc1="3" ;
		String acc2 ="4";
		
		Status status  =  helper.transfer(acc2, acc1, Double.valueOf(10));		
		assertTrue(Status.STATUS_SUCCESS.equals(status.getStatus()));
		assertTrue(BigDecimal.valueOf(110.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc1).getMessage()))));
		assertTrue(BigDecimal.valueOf(90.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc2).getMessage()))));
		
	}
	
	@Test
	public void concurrentWithdraw() throws InterruptedException, InvalidAccountException {
		String acc1="5" ;
		String acc2 ="6";
	    final int numThreads = 10;	   // 
	   
	    final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
	    try {
	        final CyclicBarrier allExecutorThreadsReady = new CyclicBarrier(numThreads);
	        final CountDownLatch allDone = new CountDownLatch(numThreads);
	        for (int i=0;i<numThreads;i++) {
	            threadPool.submit(new Runnable() {
	                public void run() {
	                	 
	                    try {
	                    	//System.out.println("Number of parties required to trip the barrier = "+ 
	                    	//		allExecutorThreadsReady.getParties());
	                    	//System.out.println(Thread.currentThread().getName() +" waiting at barrier ");
							allExecutorThreadsReady.await();
						} catch (InterruptedException | BrokenBarrierException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    try {
	                       
	                        helper.transfer(acc1, acc2, Double.valueOf(5));
	                       
	                        
	                    }   finally {
	                    	allDone.countDown();
	                    	
	                    	
	                    }
	                    	
	                    }
	                    
	                }
	            );
	           
	        }
	       
	        // wait until all threads are ready
	    
	        // start all test runners
	        try {
				allDone.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       System.out.println("going to end ");
	        
	    } finally {
	        threadPool.shutdownNow();
	    }
	    assertTrue(BigDecimal.valueOf(50.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc1).getMessage()))));
		assertTrue(BigDecimal.valueOf(150.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc2).getMessage()))));
	}
	
	
	@Test
	public void concurrentTransfer() throws InterruptedException, InvalidAccountException {
	    final int numThreads = 10;
	    String acc1="7" ;
		String acc2 ="8";
	  
	    final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
	    try {
	        final CyclicBarrier allExecutorThreadsReady = new CyclicBarrier(numThreads);	       //
	        final CountDownLatch allDone = new CountDownLatch(numThreads);
	        for (int i=0;i<numThreads;i++) {
	            threadPool.submit(new Runnable() {
	                public void run() {
	                	 
	                    try {
	                    	//System.out.println("Number of parties required to trip the barrier = "+ 
	                    	//		allExecutorThreadsReady.getParties());
	                    	//System.out.println(Thread.currentThread().getName() +" waiting at barrier ");
							allExecutorThreadsReady.await();
						} catch (InterruptedException | BrokenBarrierException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    try {
	                       
	                        helper.transfer(acc2, acc1, Double.valueOf(5));
	                       
	                        
	                    } finally {
	                    	allDone.countDown();
	                    	
	                    	
	                    }
	                    	
	                    }
	                    
	                }
	            );
	           
	        }
	       
	        // wait until all threads are ready
	    
	        // start all test runners
	        try {
				allDone.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       System.out.println("going to end ");
	        
	    } finally {
	        threadPool.shutdownNow();
	    }
	    assertTrue(BigDecimal.valueOf(150.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc1).getMessage()))));
		assertTrue(BigDecimal.valueOf(50.0).equals(BigDecimal.valueOf(Double.valueOf(helper.getBalance(acc2).getMessage()))));
		
	}
	
	@Test
	public void testInsufficientBalanceTransfer()  {
		String acc1="9" ;
		String acc2 ="10";		
		Status status = helper.transfer(acc1, acc2, Double.valueOf(101));
		assertTrue(Status.STATUS_FAIL.equals(status.getStatus()));
		assertTrue(Status.MESSAGE_INSUFFICIENT_FUND.equals(status.getMessage()));
		
	   
		
		
		
	}
	
	@Test
	public void testInValidFromAccount()  {
		String acc1="11" ;
		String acc2 ="10";		
		Status status = helper.transfer(acc1, acc2, Double.valueOf(10));
		assertTrue(Status.STATUS_FAIL.equals(status.getStatus()));
		assertTrue(Status.FROM_ACCOUNT_INVALID.equals(status.getMessage()));
		
		
		
	}
	
	@Test
	public void testInValidToAccount()  {
		String acc1="1" ;
		String acc2 ="11";				
		Status status = helper.transfer(acc1, acc2, Double.valueOf(10));
		assertTrue(Status.STATUS_FAIL.equals(status.getStatus()));
		assertTrue(Status.TO_ACCOUNT_INVALID.equals(status.getMessage()));
		
		
		
		
	}


}

