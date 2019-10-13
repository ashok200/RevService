package com.revoult.status;

import java.io.Serializable;

public interface Status extends Serializable {
	public static String STATUS_SUCCESS="SUCCESS";
	public static String STATUS_FAIL="FAIL";
	public static String MESSAGE_INSUFFICIENT_FUND="Insufficient Fund in Source Account";
	public static String FROM_ACCOUNT_INVALID="From Account does not exists";
	public static String TO_ACCOUNT_INVALID="To Account does not exists";
	public static String TRANSFER_SUCCESSFUL="Transfer is successful";
	public String getStatus();
	public String getMessage();
		
	}


