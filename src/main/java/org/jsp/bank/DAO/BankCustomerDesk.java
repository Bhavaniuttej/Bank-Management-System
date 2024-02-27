package org.jsp.bank.DAO;

public class BankCustomerDesk {
	
	public static BankDAO customerHelpDesk() 
	{
		BankDAO bankDAO = new BankDAOImplementation();
		return bankDAO;
		
	}
}
