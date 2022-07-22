package com.skillstorm.expensereimbursement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.DAO.ExpenseDAO;
import com.skillstorm.beans.Expense;

@WebServlet(urlPatterns = "/request")
public class ExpenseController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// communicates data from the MySQL database and passes it to front end as json
	// find all the entries from the expense table and returns it to the front end
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			ExpenseDAO dao = new ExpenseDAO();
			resp.getWriter().print(new ObjectMapper().writeValueAsString(dao.findAll()));
		} catch (IOException | SQLException | ClassNotFoundException e) {
			System.out.println("issue with doGet");
			e.printStackTrace();
		}
	}
	
	// communicates data from the front end and passes it to the database
	// If a user enters a new request on the front end this method will pass that info to the database
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			ExpenseDAO dao = new ExpenseDAO();
			dao.create(new ObjectMapper().readValue(req.getInputStream(), Expense.class));
		} catch (SQLException | IOException | ClassNotFoundException e) {
			System.out.println("issue with doPost");
			e.printStackTrace();
		}	
	}
	
	// communicates data from the front end and passes it to the database
	// If a user on the front end approves or denies a request this method will update the database 
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ExpenseDAO dao = new ExpenseDAO();
			Numberholder num = new ObjectMapper().readValue(req.getInputStream(), Numberholder.class);			
			dao.updateById(num.geteID(), num.getrID());
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("issue with doPut");
			e.printStackTrace();
		}
		
	}
	
	// communicates data from the front end and passes it to the database
	// If a user on the front end deletes a request this method will communicate which request needs to be delete to database
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ExpenseDAO dao = new ExpenseDAO();
			Numberholder num = new ObjectMapper().readValue(req.getInputStream(), Numberholder.class);			
			dao.deleteById(num.geteID());
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("issue with doDelete");
			e.printStackTrace();
		}
	}
}

// helper class: takes json object from front end for the PUT and DELETE calls
// handles two ints:
// eID -> expenseId
// rID -> reimbursementId
class Numberholder{
	private int eID;
	private int rID;
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}	
}

