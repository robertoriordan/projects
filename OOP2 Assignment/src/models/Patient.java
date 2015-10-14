package models;

import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings("serial")
public class Patient implements java.io.Serializable{
	
	private int patientNum;
	private String patientName;
	private String patientAdd;
	private String patientPhone;
	private ArrayList <Payment> p_paymentList;
	private ArrayList <Procedure> p_procList;
	private ArrayList <Date> p_proc_dateList;
	
	//Constructors
	public Patient(String patientName, String patientAdd, String patientPhone, int patientNum) {
		
		this.patientName = patientName;
		this.patientAdd = patientAdd;
		this.patientPhone = patientPhone;
		this.patientNum = patientNum;
		this.p_paymentList = new ArrayList <Payment> ();
		this.p_procList = new ArrayList <Procedure> ();
		this.p_proc_dateList = new ArrayList <Date> ();
	}
	
	public Patient() {
		
	}
	
	public double moneyOwed() {
		double totalProcCost = 0;
		double totalPayed = 0;
		
		for (int x = 0; x < p_procList.size(); ++x) {
			totalProcCost += p_procList.get(x).getProcCost();
		}
		
		for (int x = 0; x < p_paymentList.size(); ++x) {
			totalPayed += p_paymentList.get(x).getPaymentAmt();
		}
		
		return totalProcCost - totalPayed;
	}
	
	//Getters
	public int getPatientNum() {
		return patientNum;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public String getPatientAdd() {
		return patientAdd;
	}
	
	public String getPatientPhone() {
		return patientPhone;
	}
	
	public ArrayList <Payment> getP_paymentList() {
		return p_paymentList;
	}
	
	public ArrayList <Procedure> getP_procList() {
		return p_procList;
	}
	
	//Setters
	public void setPatientNum(int patientNum) {
		this.patientNum = patientNum;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public void setPatientAdd(String patientAdd) {
		this.patientAdd = patientAdd;
	}
	
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	public void setP_paymentList(ArrayList <Payment> p_paymentList) {
		this.p_paymentList = p_paymentList;
	}
	
	public void setP_procList(ArrayList <Procedure> p_procList) {
		this.p_procList = p_procList;
	}
	
	public ArrayList<Date> getP_proc_dateList() {
		return p_proc_dateList;
	}

	public void setP_proc_dateList(ArrayList<Date> p_proc_dateList) {
		this.p_proc_dateList = p_proc_dateList;
	}

	//ToString & print
	public String toString() {
		return	patientNum + " | " +
				patientName + " | " +
				patientAdd + " | " +
				patientPhone;  
	}
	
	public void print() {
		System.out.println(toString());
	}
}