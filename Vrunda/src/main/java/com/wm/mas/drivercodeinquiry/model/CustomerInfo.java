package com.wm.mas.drivercodeinquiry.model;

public class CustomerInfo {

	String ServiceName;
	String BillingPhoneNo;
	String ErrorMsg;
	
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	
	public String getBillingPhoneNo() {
		return BillingPhoneNo;
	}
	public void setBillingPhoneNo(String phonenumber) {
		BillingPhoneNo = phonenumber;
	}
	
}
