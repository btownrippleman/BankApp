import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class BankMain /* extends Thread*/  implements Serializable {
	
	
	
	public static Account acct = new Account();
	public static Scanner sc = new Scanner(System.in);
	public static Database db = new Database();
	public static Display dp = new Display();
	public static HashMap<String,Account> usernamesAndAccounts = db.getHashMap();
	
	public static void main(String[] args) /*throws Exception*/ {
		Account acct2 = load();
		System.out.println(acct2.getClass().toString()+" = "+acct2.toString());
		menuScreen();
			
	}
	
	public static void menuScreen(){
		arrayListPrint(dp.menuOptions());
 		menuOptionsExecute(sc.nextInt()); 
 	}
	
	public static void menuOptionsExecute(int i){
		
		switch(i){
		case 1:
			register();
			break;		
		case 2:
			login();
			break;			
		default:
 			for (int j = 0; j<100;j++)
 			{i++;System.out.println("thanks for banking at Brady's Bank!");}
 			System.exit(0);
			break;
	 
		}
		
	}
	
	public static void arrayListPrint(ArrayList<String> s){
		for (String i: s){ System.out.println(i); }
	}
	
	public static void login(){
		ArrayList<String> form = dp.loginScriptForm();
		System.out.println(form.get(0));
		String name = sc.next();
		System.out.println(form.get(1));
		String password = sc.next();
		
		boolean userFound = usernamesAndAccounts.containsKey(name);
		if (userFound){
			if (usernamesAndAccounts.get(name).getPassword().equals(password)){
				acct = usernamesAndAccounts.get(name);
				accountOptions(acct); //i.e. send this account off to account options
			}
			
			else if (usernamesAndAccounts.get(name) != null)// the case that the account exists but the password is incorrect
			{
				password = sc.next();
				while(!usernamesAndAccounts.get(name).getPassword().equals(password))  //incorrect password loop
				{
					System.out.println("please enter the correct password");
					password = sc.next();
				}
				
			}}
		else 
			{
			System.out.println("Your username nor password is not found in our database, would you like to register for an account?");
			System.out.println("type yes or no");
			if (sc.next().startsWith("y")){register(); }
			else {System.out.println("have a nice day Sytem.exit(0)"); System.exit(0);}
			
			}		
	}
		
	public static void register(){
		Account acct = new Account();
		System.out.println("now that we've established that you're a new user, enter a unique username:");
		String enteredUsername = sc.next();
		// sends through while loop if username already exists
		while(db.checkUserNames(enteredUsername)){	
			System.out.println("please enter a new user name, the one you entered is not unique;"); 
			enteredUsername = sc.next();}
		System.out.println("great "+enteredUsername+" is a unique username!");
		acct.setUsername(enteredUsername);
		
 		String firstPassword = "", secondPassword = "";
		
		while(acct.getPassword() == null){
			System.out.println("enter in your password making sure that it is at least five characters long:");
			firstPassword = sc.next();
			System.out.println("now enter in your password again:");
			secondPassword = sc.next();
			if(firstPassword.length() > 4 && firstPassword.equals(secondPassword))
			{acct.setPassword(firstPassword);}
			}
		String[] regQs = new String[dp.registrationForm().size()];

		System.out.println("Enter your First Name");
		acct.setFirstName(sc.next());
		System.out.println("Enter your Last Name");
		acct.setLastName(sc.next());
		System.out.println("Enter your Street Address");
		acct.setAddress(sc.next());
		System.out.println("Enter your City");
		acct.setCity(sc.next());
		System.out.println("Enter your State");
		acct.setState(sc.next());
		System.out.println("Enter your Zip Code");
		acct.setZipCode(sc.nextInt());
		System.out.println("Enter the amount you wish to deposit.");
		acct.setBalance(sc.nextInt());
		System.out.println("Your account has now been created!");//
		
		
		db.addAccount(acct);
		System.out.println();
		accountOptions(acct);
	}
	public static void withdrawal(Account a, double amt){
		double bal = a.getBalance();
		a.setBalance(bal - amt);
		a.addReceipt(amt, "withdrawal", bal);
	}
	
	public static void deposit(Account a, double amt){
		double bal = a.getBalance();
		a.setBalance(bal +amt);
		a.addReceipt(amt, "deposit", bal);	}
	public static void accountOptions(Account a){
		boolean sysExit = false;
		while(!sysExit){
			System.out.println("press 1 to withdraw, 2 to deposit, 3 to view account, balance 4 to view receipts, 5 for acct info or any other integer to log out");
			String response = sc.next();
			int num = Integer.parseInt(response);
			
			if (num > 0 && num < 7){accountOptionsExecute(num, a);} 
		
			else {System.out.println("would you like more time? press n to exit or l to logout user");
				response = sc.next();
				  if (response.toLowerCase().startsWith("n")){ int i = 0;
					while (i<100){i++;System.out.println("thanks for banking at Brady's Bank!");System.exit(0);}}
				  	else if(response.toLowerCase().startsWith("l")) { logout();}
			}
		}
	} 
	
	public static void logout(){
		acct.save();
		System.out.println("you are now logged out");
		menuScreen();
	}
	
	public static void accountOptionsExecute(int response, Account a){
		if (response == 1){ 
			System.out.println("how much would you like to withdraw?");
			double withdrawAmt = sc.nextDouble();
			withdrawal(a, withdrawAmt);
		}
		else if (response == 2){ 
			System.out.println("how much would you like to deposit?");
			double depositAmt = sc.nextDouble();
			deposit(a, depositAmt);
		}
		else if (response == 3){ 
			System.out.println("your current balance is");
			System.out.println(a.getBalance());
		}
		else if (response == 4){ 
			System.out.println("your array of receipts is;");
			System.out.println(a.receiptsToString());
		}
		else if (response == 5){ 
			System.out.println("your account profile is as follows:");
			System.out.println(a.toString());
		}
		else if (response == 6){ 
			System.out.println("your database looks as follows:");
			System.out.println(db.getHashMap().toString());}
				
	
	}

	public static Account load() {
		 Account genericAcct = new Account();
		 File file = new File("database.txt");
		 System.out.println("the lenthg of database.txt is "+file.length());
		 if (file.length() > 0){		 
			 try
		    {
		      System.out.println("Now Deserializing");
		      FileInputStream inputFileStream = new FileInputStream("database.txt");
		      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
		      genericAcct = (Account) objectInputStream.readObject();
		      objectInputStream.close();
		      inputFileStream.close();
		      return genericAcct;
		    }
		 
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }}
		 return genericAcct;
	}

	public void save(){
	    try {
	        //create an output stream for serializing the employee object
	        FileOutputStream outputFileStream = new FileOutputStream("database.txt");
	        
	        ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);
	        //writeObject method of ObjectOutputStream will write/serialize the employeeobject to 
	        //the path provided by FileOutputStream
	        outputStream.writeObject(acct);
	        outputStream.close();
	        outputFileStream.close();
	        
	        System.out.println("seralization a success");
	        
	      }
	      catch(IOException e)
	      {
	        //Print any exception
	        e.printStackTrace();
	      }
	}
	
}
