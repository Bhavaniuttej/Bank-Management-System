package org.jsp.bank.model;

public class Bank {
private int userId;
private String userFirstName;
private String userLastName;
private String mobileNumber;
private String emailId;
private String password;
private double amount;
private String address;
private String accountNumber;

public Bank() {
}

public Bank(int userId, String userFirstName, String userLastName, String mobileNumber, String emailId,
		String password, String address, double amount, String accountNumber) {
	this.userId = userId;
	this.userFirstName = userFirstName;
	this.userLastName = userLastName;
	this.mobileNumber = mobileNumber;
	this.emailId = emailId;
	this.password = password;
	this.amount = amount;
	this.address = address;
	this.accountNumber = accountNumber;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getUserFirstName() {
	return userFirstName;
}

public void setUserFirstName(String userFirstName) {
	this.userFirstName = userFirstName;
}

public String getUserLastName() {
	return userLastName;
}

public void setUserLastName(String userLastName) {
	this.userLastName = userLastName;
}

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

@Override
public String toString() {
	return "Bank_Management [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
			+ ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", password=" + password + ", amount="
			+ amount + ", address=" + address + ", accountNumber=" + accountNumber + "]";
}

}