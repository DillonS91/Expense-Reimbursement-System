package com.skillstorm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.skillstorm.beans.Expense;

import com.skillstorm.beans.ReimbursementStatus;

public class ExpenseDAO {
	
	private Connection connection;
	
	// initiates the connection object
	public ExpenseDAO() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reimbursementsystem", "root", "root");
	}
	
	// adds new entries to the database
	public void create(Expense expense) throws SQLException{
		ReimbursementStatus status = new ReimbursementStatus();
		String sql = "insert into expenses(employee_name, reason, notes, reimbursement_id) values (?, ?, ?, 1)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, expense.getEmployeeName());
		statement.setString(2, expense.getExpenseReason());
		statement.setString(3, expense.getExpenseNotes());
		statement.executeUpdate(); 
	}
	
	// returns all the entries from the database
	public List<Expense> findAll() throws SQLException{
		List<Expense> list = new CopyOnWriteArrayList<>();		
		String sql = "select * from expenses";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		if(rs != null) {
			while (rs.next()) {
				Expense expense = new Expense(rs.getString("employee_name"), rs.getString("reason"), rs.getString("notes"), rs.getInt("reimbursement_id"));
				expense.setExpenseId(rs.getInt("expense_id"));
				ReimbursementStatus status = new ReimbursementStatus(rs.getInt("reimbursement_id"));
				expense.setStatus(status);
				list.add(expense);
			}			
			return list;
		} else
			return new CopyOnWriteArrayList<>();
	}
	
	// updates the reimbursement status of a given request 
	public void updateById(int eID, int rID) throws SQLException{
		String sql = "update expenses set reimbursement_id = ? where expense_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, rID);
		statement.setInt(2, eID);
		statement.executeUpdate();
	}
	
	// deletes the entry of a given request
	public void deleteById(int id) throws SQLException {
		String sql = "delete from expenses where expense_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}
	
}



//select expenses.employee_name, expenses.reason, expenses.notes, expenses.reimbursement_id, reimbursement_statuses.reimbursement_status
//from expenses 
//right join reimbursement_statuses on expenses.reimbursement_id = reimbursement_statuses.reimbursement_id;
