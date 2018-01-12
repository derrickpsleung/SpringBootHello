package com.hello.form;

import java.util.ArrayList;
import java.util.List;

import com.hello.model.LocalTransfer;

public class LocalTransferForm {

	private List<LocalTransfer> transferInfoList;

	public List<LocalTransfer> getTransferInfoList() {
		if(transferInfoList==null) {
			return new ArrayList<LocalTransfer>();
		}
		return transferInfoList;
	}

	public void setTransferInfoList(List<LocalTransfer> transferInfoList) {
		this.transferInfoList = transferInfoList;
	}
	
	
}
