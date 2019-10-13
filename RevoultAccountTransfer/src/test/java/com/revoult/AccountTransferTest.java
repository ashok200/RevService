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

public class AccountTransferTest {
	
	@Rule
    public ExpectedException exception= ExpectedException.none();

	

	@Test	
	public void testWithDraw() throws InvalidAccountException, InSufficientBalanceException {
		String acc1="1" ;
		String acc2 ="2";
		Actions actions  = Actions.getTransferActions();
		Boolean status = actions.transfer(acc1, acc2, Double.valueOf(10));
		assertTrue(status.booleanValue());
		assertTrue(BigDecimal.valueOf(90.0).equals(actions.getAccount(acc1).getBalance()));
		assertTrue(BigDecimal.valueOf(110.0).equals(actions.getAccount(acc2).getBalance()));
		
	}
	
	@Test
	public void testWithDeposit() throws InvalidAccountException, InSufficientBalanceException {	
		String acc1="3" ;
		String acc2 ="4";
		Actions actions  = Actions.getTransferActions();
		Boolean status =  actions.transfer(acc2, acc1, Double.valueOf(10));		
		assertTrue(status.booleanValue());
		assertTrue(BigDecimal.valueOf(110.0).equals(actions.getAccount(acc1).getBalance()));
		assertTrue(BigDecimal.valueOf(90.0).equals(actions.getAccount(acc2).getBalance()));
		
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
	                       
	                        Actions.getTransferActions().transfer(acc1, acc2, Double.valueOf(5));
	                       
	                        
	                    } catch (InvalidAccountException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InSufficientBalanceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  finally {
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
	    assertTrue(BigDecimal.valueOf(50.0).equals(Actions.getTransferActions().getBalance(acc1)));
		assertTrue(BigDecimal.valueOf(150.0).equals(Actions.getTransferActions().getBalance(acc2)));
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
	                       
	                        Actions.getTransferActions().transfer(acc2, acc1, Double.valueOf(5));
	                       
	                        
	                    } catch (InvalidAccountException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InSufficientBalanceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  finally {
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
	    assertTrue(BigDecimal.valueOf(150.0).equals(Actions.getTransferActions().getBalance(acc1)));
		assertTrue(BigDecimal.valueOf(50.0).equals(Actions.getTransferActions().getBalance(acc2)));
		
	}
	
	@Test
	public void testInsufficientBalanceTransfer() throws InvalidAccountException, InSufficientBalanceException {
		String acc1="9" ;
		String acc2 ="10";
		Actions actions  = Actions.getTransferActions();	
		exception.expect(InSufficientBalanceException.class);
		actions.transfer(acc1, acc2, Double.valueOf(101));
		
	   
		
		
		
	}
	
	@Test
	public void testInValidFromAccount() throws InvalidAccountException, InSufficientBalanceException {
		String acc1="11" ;
		String acc2 ="10";
		Actions actions  = Actions.getTransferActions();	
		exception.expect(InvalidAccountException.class);
		 actions.transfer(acc1, acc2, Double.valueOf(10));
		
		
		
	}
	
	@Test
	public void testInValidToAccount() throws InvalidAccountException, InSufficientBalanceException {
		String acc1="1" ;
		String acc2 ="11";
		Actions actions  = Actions.getTransferActions();	
		exception.expect(InvalidAccountException.class);
		actions.transfer(acc1, acc2, Double.valueOf(10));
		
		
		
		
	}


}
