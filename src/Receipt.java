import java.io.Serializable;
import java.util.Date;



public class Receipt implements Serializable {
	
	private Date date= new Date();
	private String transactionType;
	private double amt;
	private double balance;

	
	@Override
	public String toString() {
		return "Receipt [date=" + date + ", transactionType=" + transactionType
				+ ", amt=" + amt + ", balance=" + balance + "]";
	}

	public Receipt(double transactionAmt, String transactionType, double balance) {
		super();
		this.amt = transactionAmt;
		this.transactionType = transactionType;
		if (transactionType=="withdrawal"){ this.balance = balance-transactionAmt;}
		else { this.balance = balance+transactionAmt;}
		
	}

	public String toSave() {
		return  this.date.toString() + ":" + this.transactionType + ":" + balance;
	}

 

 
	
	
	

}
