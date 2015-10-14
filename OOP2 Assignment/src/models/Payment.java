package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("serial")
public class Payment implements java.io.Serializable{

	private int paymentNum;
	private double paymentAmt;
	private Date paymentDate;
	private boolean isPaid;
	
	//Constructors
	public Payment(int paymentNum, double paymentAmt, Date paymentDate) {
		this.paymentNum = paymentNum;
		this.paymentAmt = paymentAmt;
		this.paymentDate = paymentDate;
	}
	
	public Payment() {
		
	}
	
	//Getters
	public int getPaymentNum() {
		return paymentNum;
	}
	
	public double getPaymentAmt() {
		return paymentAmt;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	public boolean getIsPaid() {
		return isPaid;
	}
	
	//Setters
	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}
	
	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	//ToString & print
	public String toString() {
		
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String date = f.format(paymentDate);
		
		return "\nNumber:\t" + paymentNum + "\n" +
				"Amount:\t" + paymentAmt + "\n" +
				"Date:\t" + date + "\n";
	}
	
	public void print() {
		System.out.println(toString());
	}
}
