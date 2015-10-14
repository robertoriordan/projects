package models;

@SuppressWarnings("serial")
public class Procedure implements java.io.Serializable{

	private int procNum;
	private String procName;
	private double procCost;
	
	//Constructors
	public Procedure(String procName, double procCost, int procNum) {
		this.procName = procName;
		this.procCost = procCost;
		this.procNum = procNum;
	}
	
	public Procedure() {
		
	}
	
	//Getters
	public int getProcNum() {
		return procNum;
	}
	
	public String getProcName() {
		return procName;
	}
	
	public double getProcCost() {
		return procCost;
	}
	
	//Setters
	public void setProcNum(int procNum) {
		this.procNum = procNum;
	}
	
	public void setProcName(String procName) {
		this.procName = procName;
	}
	
	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}
	
	//ToString & Print
	public String toString() {
		return 	procNum +
				" | " + procName +
				" | €" + procCost;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
