package com.revoult.status;

public class TransferStatus implements Status {
	private String status ;
	private String message ;
	

	public TransferStatus() {
	
	}
	

	public void setStatus(String status) {
		this.status = status;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
