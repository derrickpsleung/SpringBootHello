package com.hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LocalTransfer {

	@Id
	private String id;
	private String logonUsername;
	private String bankName;
	private String fromAccNo;
	private String toAccNo;
	
	public String getLogonUsername() {
		return logonUsername;
	}
	public void setLogonUsername(String logonUsername) {
		this.logonUsername = logonUsername;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}
	
	
}
