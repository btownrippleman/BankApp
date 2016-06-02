import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Database implements Serializable {

 	private ArrayList<Account> accounts = new ArrayList<Account>();
	
 	private ArrayList<String> usernames = new ArrayList<String>();
 	
 	private HashMap<String, Account> usernamesAndAccounts = new HashMap<String, Account>();

	private static final long serialVersionUID = 1L;

	IO io = new IO();
	
//	File file = new File("database.txt");
	
	String fname = "database.txt";
	
	Database(){
		this.accounts = (ArrayList<Account>) accounts;
		this.usernames = (ArrayList<String>)usernames;
		this.usernamesAndAccounts = (HashMap<String, Account>) usernamesAndAccounts;
		this.io = (IO) io;
	}	
			
 	public void addAccount(Account a){
		
		usernamesAndAccounts.put(a.getUsername(), a);
	}
	
	public Collection<Account> getAccounts(){
		
		return usernamesAndAccounts.values();
		
	}
	
	public String accountsToString(){
		return usernamesAndAccounts.toString();
	}

	public ArrayList<String> getUsernames(){
		return (ArrayList<String>) usernamesAndAccounts.keySet();
	}
	
	public boolean checkUserNames(String name){
		return usernamesAndAccounts.containsKey(name);			
		}
		
	public HashMap<String,Account> getHashMap(){
		return usernamesAndAccounts;
	}
 
	public void load () {

		 try
		    {
		      System.out.println("Now Deserializing");
		      FileInputStream inputFileStream = new FileInputStream(fname);
		      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
		      this.usernamesAndAccounts = (HashMap<String, Account>)objectInputStream.readObject();
		      objectInputStream.close();
		      inputFileStream.close();
		    }
		 
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }

	}

	/**
	 * Save user profiles to file.
	 * @param users User profiles
	 */
	public void save() {
	    try {
	        //create an output stream for serializing the employee object
	        FileOutputStream outputFileStream = new FileOutputStream(fname);
	        
	        ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);
	        //writeObject method of ObjectOutputStream will write/serialize the employeeobject to 
	        //the path provided by FileOutputStream
	        outputStream.writeObject(this.usernamesAndAccounts);
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
