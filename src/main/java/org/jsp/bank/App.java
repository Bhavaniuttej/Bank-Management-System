package org.jsp.bank;

import java.util.Scanner;

import org.jsp.bank.DAO.BankCustomerDesk;
import org.jsp.bank.DAO.BankDAO;
import org.jsp.bank.model.Bank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BankDAO bankDAO = BankCustomerDesk.customerHelpDesk();
    	
    	System.out.println("Enter \n 1.For Registration \n 2.For Credit \n 3.For Debit \n 4.For Changing the Password \n 5.For Mobile To Mobile Transaction \n 6.For Check Balance");
    	//It is used to take input from the user for operation
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	int response = sc.nextInt();
    	
    	switch (response) {
    	//Firstly we have stored the values in the bank class object.
    	//So,we have choose the constructor with argument.
    	//Because it is mandatory to give all the details of the user.
		case 1: 
			System.out.println("Enter Your First Name : ");
			String fname = sc.next();
			System.out.println("Enter Your Last Name : ");
			String lname = sc.next();
			System.out.println("Enter Your Mobile Number : ");
			String mb = sc.next();
			System.out.println("Enter Your Email Id : ");
			String email = sc.next();
			System.out.println("Enter Your Password : ");
			String password = sc.next();
			System.out.println("Enter Your Address : ");
			String address = sc.next();
			System.out.println("Enter Your Amount : ");
			double amount = sc.nextDouble();
			System.out.println("Enter Your Account Number : ");
			String accountNumber = sc.next();
			Bank bank = new Bank(
			/*User_Id = */					0,
			/*User_First_Name = */			fname,
			/*User_Last_Name = */			lname,
			/*User_Mobile number = */		mb,
			/*User_Email_Id = */			email,
			/*User_Password = */			password,
			/*User_Address = */				address,
			/*User_Amount = */				amount,
			/*User_Account number = */		accountNumber);
				bankDAO.userRegistration(bank);
				
			
			break;
		case 2:
			System.out.println("Enter Your Account Number : ");
			boolean accountStatus = true;
			while (accountStatus) {
				String ac_Number = sc.next();
				if (ac_Number.length() == 11) {
					accountStatus = false;
					// call the method which is having implementation for credit operation.
					bankDAO.credit(ac_Number);
				} else {
					System.out.println("Invalid Account Number");
					System.out.println("Enter Your 11digits account number");
				}
			}

			break;
		case 3:
			System.out.println("Enter Your Account Number : ");
			boolean accountstatus = true;
			while(accountstatus) {
				String accountnumber = sc.next();
				if (accountnumber.length() == 11) {
					accountstatus = false;
					System.out.println("Enter  Your Password");
					boolean passwordstatus = true;
					while (passwordstatus) {
						String userpassword = sc.next();
						if (userpassword.length() == 4) {
							passwordstatus = false;
							//call the method which is having implementation for debit operation.
							bankDAO.debit(accountnumber, userpassword);
						} else {
							System.out.println("Invalid password");
							System.out.println("Enter 4digit password");
							passwordstatus = true;
						}
					}
				} else {
					System.out.println("Invalid account number");
					System.out.println("Enter Your 11 digit account number");
				}
			}
			
		
			break;
		case 4:
			System.out.println("Enter Your Account Number : ");
			boolean acStatus = true;
			while (acStatus) {
				String ac = sc.next();
				if (ac.length() == 11) {
					acStatus = false;
					System.out.println("Enter Your Mobile Number : ");
					boolean mn_status = true;
					while (mn_status) {
						String mobileNumber = sc.next();
						if (mobileNumber.length() == 10) {
							mn_status = false;
							// call the method which is having implementation for changing the password operation.
							bankDAO.changingThePassword(ac, mobileNumber);
						} else {
							System.out.println("Invalid mobile number");
							System.out.println("Enter Your 10 digit mobile number");
						}
					}
				} else {
					System.out.println("Invalid account number");
					System.out.println("Enter Your 11 digit account number");
				}
			}
			break;
		case 5:
        	System.out.print("Enter your Mobile No : ");
        	while(true) {
        	try {
        		String userMobileNumber = sc.next();
            	if(userMobileNumber.length() == 10 ) {
            		bankDAO.mobileToMobileTransaction(userMobileNumber);
            		break;
            	}else {
            		throw new IllegalAccessException();
            	}
			} catch (Exception e) {
				//to handle the exception thrown by the mobile no
				System.out.print("enter valid mobile number \n");
				System.out.print("\nenter the mobile number contains 10 digits : ");
			}
        	}
        	
			break;
		case 6:
			System.out.println("Enter Your account number : ");
			boolean account_status = true;
			while (account_status) {
				String acNumber = sc.next();
				if (acNumber.length() == 11) {
					account_status = false;
					System.out.println("Enter Your password : ");
					boolean password_status = true;
					while (password_status) {
						String userPassword = sc.next();
						if (userPassword.length() == 4) {
							password_status = false;
							bankDAO.checkBalance(acNumber, userPassword);
						} else {
							System.out.println("Invalid mobile number");
							System.out.println("Enter Your 10 digit mobile number");
						}
					}
				} else {
					System.out.println("Invalid account number");
					System.out.println("Enter Your 11 digit account number");
				}
			}
			break;
		default:
			System.out.println("Enter correct response");
			break;
		}
    }
}
