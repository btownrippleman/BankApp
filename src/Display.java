import java.util.ArrayList;


public class Display {
	
	public Display(){};
	
	public ArrayList<String> menuOptions(){
		ArrayList<String>  menuScripts = new ArrayList<String>();
		menuScripts.add("Welcome to Brady's Bank");
		menuScripts.add("enter 1 if you are a new user and want to register with Brady's Bank");
		menuScripts.add("enter 2 if you are a returning user and want to access your account");
		return menuScripts;
	}
	
	public ArrayList<String> registrationForm(){
		ArrayList<String> registrationScripts = new ArrayList<String>();
		registrationScripts.add("Enter your First Name");
		registrationScripts.add("Enter your Last Name");
		registrationScripts.add("Enter your Street Address");
		registrationScripts.add("Enter your City");
		registrationScripts.add("Enter your State");
		registrationScripts.add("Enter your Zip Code");
		registrationScripts.add("Enter the amount you wish to deposit.");
		registrationScripts.add("Your account has now been created!");
		return registrationScripts;	
	}
	
	public ArrayList<String> loginScriptForm(){
		ArrayList<String> loginScripts = new ArrayList<String>();
		loginScripts.add("Enter your username: ");
		loginScripts.add("Enter your password: ");
		return loginScripts;
	}
	
	public ArrayList<String> accountOptionsForm(){
		ArrayList<String> accountOptionsScript = new ArrayList<String>();
		accountOptionsScript.add("press 1 to deposit to your account:");
		accountOptionsScript.add("press 2 to withdraw from your account:");
		accountOptionsScript.add("press 3 to view your account balance:");
		accountOptionsScript.add("press 4 to logout");
		return accountOptionsScript;
	}
	
	

}
