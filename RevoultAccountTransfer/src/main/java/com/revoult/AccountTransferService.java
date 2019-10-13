package com.revoult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revoult.status.TransferStatus;
 
@Path("account")
public class AccountTransferService {
	
	  ServiceHelper helper = new ServiceHelper();
	  
    @GET
    @Path("transfer/{fromAccount}/{toAccount}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)  
    public TransferStatus transfer(@PathParam("fromAccount") String fromAccount,@PathParam("toAccount") String toAccount,@PathParam("amount") double amount){   
    	TransferStatus status = (TransferStatus)helper.transfer(fromAccount,toAccount, amount);		 
        return status;
    }
    
    @GET
    @Path("balance/{fromAccount}")
    @Produces(MediaType.APPLICATION_JSON)
    public TransferStatus balance(@PathParam("fromAccount") String fromAccount) {  
    	TransferStatus status =(TransferStatus)helper.getBalance(fromAccount);		
        return status;
    }
    
   
 
	}
