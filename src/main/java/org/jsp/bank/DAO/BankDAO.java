package org.jsp.bank.DAO;

import org.jsp.bank.model.Bank;

public interface BankDAO {

	void userRegistration(Bank bank);
	void credit(String accountnumber);
	void debit(String accountnumber, String password);
	void changingThePassword(String accountnumber, String mobilenumber);
	void mobileToMobileTransaction(String mobileNo);
	void checkBalance(String accountnumber, String password);
	
}
