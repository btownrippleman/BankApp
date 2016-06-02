import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Account implements Serializable {
	
	public Account(String username, String password, int number,
			double balance, String firstName, String lastName, String address,
			String city, String state, int zipCode, ArrayList<Receipt> receipts) {
		super();
		this.username = username;
		this.password = password;
		this.number = number;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.receipts = receipts;
	}
	
	
	private String username;
	private String password;
	private int number;
	private double balance;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	
	private ArrayList<Receipt> receipts =  new ArrayList<>();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
	}

	public Account() {
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String toString() {
		return  "Account [user=" + username + ", number=" + number + ", balance="
				+ balance + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + "]";
	}
	
	public String toSave() {
		 
		return ".."+username+"."+password+"." + number + "."
				+ balance + "." + firstName + "."
				+ lastName + "." + address + "." + city
				+ "." + state + "." + zipCode +"."+receiptsToString();
	}
	
	public void addReceipt(double amt, String transactionType, double bal){
		Receipt r = new Receipt(amt,transactionType,bal);
		receipts.add(r);
		}
	
	public String receiptsToString(){
		String s = "";
		for (Receipt r:receipts){
			s = s+"\n"+r.toString();
		}
		return s;
	}
		
	public String receiptsToSave(){
			String s = "";
			for (Receipt r:receipts){
				s = s+":::"+r.toString();
			}
			return s;
		}

	public String toStringText() {
	        // TODO Auto-generated method stub
	        String value = "name : " + firstName + "\nUserName : " + username
	                + "\nPassword : " + password;
	        return value;
	    }	
	
	public void save(){
    try {
        //create an output stream for serializing the employee object
        FileOutputStream outputFileStream = new FileOutputStream("database.txt");
        
        ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);
        //writeObject method of ObjectOutputStream will write/serialize the employeeobject to 
        //the path provided by FileOutputStream
        outputStream.writeObject(this);
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
	
	public Account load() {
		 Account genericAcct = new Account();
		 File file = new File("database.txt");
		 System.out.println("the lenthg of database.txt is "+file.length());
		 if (file.length() > 0){		 
			 try
		    {
		      System.out.println("Now Deserializing");
		      FileInputStream inputFileStream = new FileInputStream("database.txt");
		      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
		      genericAcct = (Account)objectInputStream.readObject();
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
	
	
}

