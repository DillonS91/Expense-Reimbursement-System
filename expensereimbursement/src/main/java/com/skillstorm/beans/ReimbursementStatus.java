package com.skillstorm.beans;

import java.util.Objects;

public class ReimbursementStatus {
	
	/*  These class fields represent attributes from the database table reimbursement_statuses: reimbursement_status and reimbursement_id(PK)
		There are three values that this class/table can have:
			1 => Pending
			2 => Approved
			3 => Denied
	*/
	private String reimburStatus; // This field will either be pending, approved, or denied. This field is related to reimbursement_status in the database
	private int statusId; // This field will either be 1, 2, 3 and map respectively to reimburStatus. This field is related to reimbursement_id in the database
	
	//======================================================== Constructors
	public ReimbursementStatus() {
		super();
	}
	
	
	public ReimbursementStatus(int statusId) {
		super();
		if (statusId == 2) {
			this.reimburStatus = "Approved";
		} else if (statusId == 3) {
			this.reimburStatus = "Denied";
		} else {
			this.reimburStatus = "Pending";
		}
		this.statusId = statusId;
	}
	//======================================================== Getters and Setters
	public String getReimburStatus() {
		return reimburStatus;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
		if (statusId == 2) {
			this.reimburStatus = "Approved";
		} else if (statusId == 3) {
			this.reimburStatus = "Denied";
		} else {
			this.reimburStatus = "Pending";
		}
	}
	
	public void setReimburStatus(String reimburStatus) {
		this.reimburStatus = reimburStatus;
	}
	
	//======================================================== toString
	@Override
	public String toString() {
		return "ReimbursementStatus [reimburStatus=" + reimburStatus + ", statusId=" + statusId + "]";
	}

}
