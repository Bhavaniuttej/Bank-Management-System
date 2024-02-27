package org.jsp.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.model.Bank;

public class BankDAOImplementation implements BankDAO{
	
	public String url = "jdbc:mysql://localhost:3306/teca52?user=root&password=12345";
	Scanner sc = new Scanner(System.in);
	public void userRegistration(Bank bank) {
		String insert = "insert into bank(User_FirstName, User_LastName, mobilenumber, emailid, password, address, amount, Account_Number) values(?,?,?,?,?,?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, bank.getUserFirstName());
			preparedStatement.setString(2, bank.getUserLastName());
			preparedStatement.setString(3, bank.getMobileNumber());
			String tempname = bank.getUserFirstName().toLowerCase();
			Random random = new Random();
			int tempnum = random.nextInt(1000);
			String bankemailid = tempname+tempnum+"@teca52.com";
			preparedStatement.setString(4, bankemailid);
			preparedStatement.setString(5, bank.getPassword());
			preparedStatement.setString(6, bank.getAddress());
			preparedStatement.setDouble(7, bank.getAmount());
			long ac = random.nextLong(100000000000l);
			if (ac < 10000000000l) {
				ac+=10000000000l;
			}
			preparedStatement.setString(8, ""+ac);
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				System.out.println("Account Created Successfully....!!!");
				try {
					Thread.sleep(2000);
					System.out.println("Your Bank Email Id is : "+bankemailid);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Invalid data");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void credit(String accountnumber) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from bank where Account_Number = ?");
			preparedStatement.setString(1, accountnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Enter Your Amount");
				boolean amountStatus = true;
				while (amountStatus) {
					double useramount = sc.nextDouble();
					if (useramount >= 0) {
						amountStatus = false;
						double databaseamount = resultSet.getDouble("amount");
						double add = databaseamount + useramount;
						String update = "update bank set amount = ? where Account_Number = ?";
						PreparedStatement preparedStatement2 = connection.prepareStatement(update);
						preparedStatement2.setDouble(1, add);
						preparedStatement2.setString(2, accountnumber);
						int result = preparedStatement2.executeUpdate();
						if (result != 0) {
							
							for(int i = 0; i < 5 ; i++) {
								try {
									Thread.sleep(3000);
									System.out.print(".");
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println(" \n Account Credited Successfully....!!");
						}
						else {
							System.out.println("Server Issue");
						}
					} else {
						System.out.println("Invalid Amount");
						System.out.println("Enter amount greater than zero");
					}
				}
			} else {
				System.out.println("NOT OK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void debit(String accountnumber, String password) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from bank where Account_Number = ? and password = ?");
			preparedStatement.setString(1, accountnumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				
				System.out.println("Enter Your Amount");
				//To continue 
				boolean amountstatus = true;
				while (amountstatus) 
				{
					double useramount = sc.nextDouble();
					if (useramount >= 0) {
						amountstatus = false;
						double databaseAmount = resultSet.getDouble("amount");
						if (databaseAmount >= useramount) {
							double sub = databaseAmount-useramount;
							String update = "update bank set amount = ? where Account_Number = ? and password = ?";
							PreparedStatement preparedStatement2 = connection.prepareStatement(update);
							preparedStatement2.setDouble(1, sub);
							preparedStatement2.setString(2, accountnumber);
							preparedStatement2.setString(3, password);
							int result = preparedStatement2.executeUpdate();
							if (result != 0) {
								
								for(int i = 0; i < 5 ; i++) {
									try {
										Thread.sleep(3000);
										System.out.print(".");
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								System.out.println(" \n Account Debited Successfully....!!");
							}
							else {
								System.out.println("Server Issue");
							}
						} else {
							System.out.println("Insufficient Balance");
							amountstatus = true;
						}
						
					} else {
						System.out.println("Invalid amount");
						System.out.println("Enter amount greater than ZERO");
						amountstatus = true;
					}
				}
			} else {
				System.out.println("NOT OK");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changingThePassword(String accountnumber, String mobilenumber) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from bank where Account_Number = ? and mobilenumber = ?");
			preparedStatement.setString(1, accountnumber);
			preparedStatement.setString(2, mobilenumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Enter Your New Password : ");
				boolean passwordstatus = true;
				while (passwordstatus) {
					String newpassword = sc.next();
					System.out.println("Confirm Your new password");
					String confirmnewpassword = sc.next();
					if (newpassword.equals(confirmnewpassword)) {
						String oldpassword = resultSet.getString("password");
						if (!oldpassword.equals(newpassword)) {
							if (newpassword.length() == 4) {
								passwordstatus = false;
								String update = "update bank set password = ? where Account_Number = ? and mobilenumber = ?";
								PreparedStatement preparedStatement2 = connection.prepareStatement(update);
								preparedStatement2.setString(1, confirmnewpassword);
								preparedStatement2.setString(2, accountnumber);
								preparedStatement2.setString(3, mobilenumber);
								int result = preparedStatement2.executeUpdate();
								if (result != 0) {
									Random random = new Random();
									int otp = random.nextInt(1000000);
									if (otp < 100000) {
										otp += 100000;
									}
									System.out.println("Your generated OTP");
									System.out.println(otp);
									try {
										Thread.sleep(10000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									System.out.println("Enter generated OTP");
									int user_otp = sc.nextInt();
									if (otp == user_otp) {
										for(int i = 0; i < 5 ; i++) {
											try {
												Thread.sleep(3000);
												System.out.print(".");
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
										}
										System.out.println(" \n Your Password Updated Successfully...!!");
									} else {
										System.out.println("Inavlid OTP");
										System.out.println("Enter valid OTP");
									}
								} else {
									System.out.println("Invalid email-Id or Mobile Number ");
									System.out.println("Enter email-Id and Mobile Number correctly");
								}
							} else {
								System.out.println("Invalid password");
								System.out.println("Enter a 4-digit password");
							}
						} else {
							System.out.println("New password is same as Old password");
							System.out.println("Enter a new password");
						}
					} else {
						System.out.println("Password mismatch");
						System.out.println("Enter same password again..");
					}
				}
			} else {
				System.out.println("NOT OK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void mobileToMobileTransaction(String mobileNo) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from bank where mobilenumber = ? ");
			preparedStatement.setString(1, mobileNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.print("enter the receiver's mobile number : ");
				boolean receiversMobileNumberStatus = true;
				while(receiversMobileNumberStatus) {
				String receiversMobileNumber = sc.next();
				if(receiversMobileNumber.length() == 10 ) {
					receiversMobileNumberStatus = false ; 
					PreparedStatement preparedStatementRec = connection.prepareStatement("select * from bank where mobilenumber = ?");
					preparedStatementRec.setString(1, receiversMobileNumber);
					ResultSet resultSetRec = preparedStatementRec.executeQuery();
					if(resultSetRec.next()) {
							System.out.print("enter amount : ");
							boolean receiverAmountStatus = true;
							while(receiverAmountStatus) {
								double receiversAmount = sc.nextDouble();
								if(receiversAmount > 0 ) {
									receiverAmountStatus = false;
									double senderDBAmount = resultSet.getDouble("Amount");
									double recDBAmount = resultSetRec.getDouble("Amount");
									if(senderDBAmount >= receiversAmount ) {
										double totalRecAmount =  recDBAmount + receiversAmount;
										double totalSenderAmount = senderDBAmount-receiversAmount ;
									 	PreparedStatement preparedStatementUpdaterec = connection.prepareStatement("update bank set amount =? where mobilenumber = ?");
									 	preparedStatementUpdaterec.setDouble(1, totalRecAmount);
									 	preparedStatementUpdaterec.setString(2, receiversMobileNumber);
									 	preparedStatementUpdaterec.executeUpdate();
									 	PreparedStatement preparedStatementUpdateuser = connection.prepareStatement("update bank set amount =? where mobilenumber = ?");
									 	preparedStatementUpdateuser.setDouble(1, totalSenderAmount);
									 	preparedStatementUpdateuser.setString(2, mobileNo);
									 	int userResult = preparedStatementUpdateuser.executeUpdate();
									 	if(userResult !=0){
									 		try {
									 			for (int i = 0; i < 3; i++) {
									 				Thread.sleep(1000);
									 				System.out.print('.');
												}
												
											} catch (Exception e) {
										
											}
									 		System.out.println("sent successfully");
									 	}
									 	else {
									 		System.out.println("server issue");
									 	}
									}
									else {
										System.out.println("Insufficent Balance");
										
										System.out.println("enter password to display of the balance : ");
										String senderPassword = sc.next();
										if(senderPassword.equals(resultSet.getString("password"))) {
										System.out.println("your account balance is "+ senderDBAmount +"/-");
										}
										receiversMobileNumberStatus = true ;
										System.out.print("enter reciver's mobile number : ");
									}
								}else {
									System.out.println("enter valid amount : ");
								}
							}
					}else {
						System.out.println("enter valid receiver mobile number : ");
						System.out.println("refer your receiver to the teca52 Bank ");
					}
				}
				else {
					System.out.print("enter valid 10 digit mobile number : ");
					receiversMobileNumberStatus = true ;
				}
				}
			}
			else {
				System.out.println("Invalid Mobile Number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkBalance(String accountnumber, String password) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select amount from bank where Account_Number = ? and password = ?");
			preparedStatement.setString(1, accountnumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				double balance = resultSet.getDouble("amount");
				for(int i = 0; i < 5 ; i++) {
					try {
						Thread.sleep(3000);
						System.out.print(".");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("\nYour balance is : "+balance);
				
			} else {
				System.out.println("NOT OK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
