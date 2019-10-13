package com.revoult;

import java.math.BigDecimal;

import com.revoult.exception.InSufficientBalanceException;
import com.revoult.exception.InvalidAccountException;
import com.revoult.status.Status;
import com.revoult.status.TransferStatus;

public class ServiceHelper {

	
	public Status transfer(String fromAccount, String toAccount, double amount) {
		TransferStatus status = new TransferStatus();
		try {
			Actions.getTransferActions().transfer(fromAccount, toAccount, amount);
		} catch (InvalidAccountException  ia) {			// 
			
			  status.setStatus(Status.STATUS_FAIL);
			  status.setMessage(ia.getMessage());
			  return status;
		} catch(InSufficientBalanceException ib) {			
			  status.setStatus(Status.STATUS_FAIL);
			  status.setMessage(ib.getMessage());
			  return status;
			
		} catch(Throwable t) {
			status.setStatus(Status.STATUS_FAIL);
			  status.setMessage(t.getMessage());
			  return status;
			
		}
		status.setStatus(Status.STATUS_SUCCESS);
		  status.setMessage(Status.TRANSFER_SUCCESSFUL);
		  return status;
	}

	
	public Status getBalance(String fromAccount) {
		TransferStatus status = new TransferStatus();
		BigDecimal balance=null;
		try {
			balance = Actions.getTransferActions().getBalance(fromAccount);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			 status.setStatus(Status.STATUS_FAIL);
			  status.setMessage(e.getMessage());
			  return status;
		}  catch(Throwable t) {
			status.setStatus(Status.STATUS_FAIL);
			  status.setMessage(t.getMessage());
			  return status;
			
		}
		status.setStatus(Status.STATUS_SUCCESS);
		  status.setMessage(balance.toString());
		  return status;
	}

	
	

}
