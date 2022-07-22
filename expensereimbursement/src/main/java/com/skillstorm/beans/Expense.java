package com.skillstorm.beans;

public class Expense {
	
	// These class fields represent attributes from the database table expenses: expense_id(PK), emplopyee_name, reason, notes, and reimbursement_id(FK) 
	private int expenseId; // holds a unique id that represents the expense reimbursement request. The id is auto-generated. This field is related to expense_id in the database
	private String employeeName; // holds the name of the employee that made the request. This field is related to employee_name in the database
	private String expenseReason; // holds the reason for the request. This field is related to reason in the database
	private String expenseNotes;  // holds any notes that the employee might have in regards to the request. This field is related to notes in the database
	private ReimbursementStatus status; // this object represents the foreign key that relates to the reimbursement_statuses table of the database
	
	
    //======================================================== Constructors
	public Expense() {
		super();
	}

	
	public Expense(String employeeName, String expenseReason, String expenseNotes) {
		super();
		this.employeeName = employeeName;
		this.expenseReason = expenseReason;
		this.expenseNotes = expenseNotes;
		this.status = new ReimbursementStatus(1);
		
	}
	public Expense(String employeeName, String expenseReason, String expenseNotes, int id) {
		super();
		this.employeeName = employeeName;
		this.expenseReason = expenseReason;
		this.expenseNotes = expenseNotes;
		this.status = new ReimbursementStatus(id);
	}
	//======================================================== Getters and Setters
	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getExpenseReason() {
		return expenseReason;
	}


	public void setExpenseReason(String expenseReason) {
		this.expenseReason = expenseReason;
	}


	public String getExpenseNotes() {
		return expenseNotes;
	}


	public void setExpenseNotes(String expenseNotes) {
		this.expenseNotes = expenseNotes;
	}


	public ReimbursementStatus getStatus() {
		return status;
	}


	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	//======================================================== toString	
	@Override
	public String toString() {
		return "Expense [expenseId= " + expenseId + ", employeeName= " + employeeName + ", expenseReason= " + expenseReason
				+ ", expenseNotes= " + expenseNotes + ", status= " + status.getReimburStatus() + "]";
	}
	
	
	
}
