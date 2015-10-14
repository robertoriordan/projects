package views;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Patient;

public class PaymentsView extends JPanel{

	private static final long serialVersionUID = -7192265074704204637L;
	
	private JLabel patientsLabel;
	private JLabel amountLabel;
	
	private JTextField amountField;
	
	private JComboBox <Patient> patientDropDown;
	
	private JPanel patient;
	private JPanel amount;
	
	private JButton addPayment;
	
	private ArrayList <Patient> patientList;
	
	public PaymentsView(ArrayList <Patient> patientList) {
		
		this.patientList = patientList;
		
		patientsLabel = new JLabel("Patients: ");
		amountLabel = new JLabel("Amount:  ");
		
		amountField = new JTextField();
		
		patientDropDown = new JComboBox <Patient> ();
		
		for (int x = 0; x < patientList.size(); ++x) {
			
			patientDropDown.addItem(patientList.get(x));
		}
		
		patient = new JPanel();
		amount = new JPanel();
		
		addPayment = new JButton("Add Payment");
		
		addComponents();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void addComponents() {
		
		add(Box.createVerticalStrut(30));
		
		patient.add(patientsLabel);
		patient.add(patientDropDown);
		
		Dimension max = new Dimension(500, 20);
		patient.setMaximumSize(max);
		patient.setMinimumSize(max);
		patient.setLayout(new BoxLayout(patient, BoxLayout.LINE_AXIS));
		
		add(patient);
		
		add(Box.createVerticalStrut(10));
		
		amount.add(amountLabel);
		amount.add(amountField);
		
		amount.setMaximumSize(new Dimension(120, 100));
		amount.setMinimumSize(new Dimension(120, 100));
		amount.setLayout(new BoxLayout(amount, BoxLayout.LINE_AXIS));
		
		add(amount);
		
		add(Box.createVerticalStrut(10));
		
		JPanel button = new JPanel();
		
		button.add(addPayment);
		add(button);
	}
	
	public void updatePatDrop() {
		
		patientDropDown.removeAllItems();
		
		for (int x = 0; x < patientList.size(); ++x) {
			
			patientDropDown.addItem(patientList.get(x));
		}
	}

	public JLabel getPatientsLabel() {
		return patientsLabel;
	}

	public void setPatientsLabel(JLabel patientsLabel) {
		this.patientsLabel = patientsLabel;
	}

	public JLabel getAmountLabel() {
		return amountLabel;
	}

	public void setAmountLabel(JLabel amountLabel) {
		this.amountLabel = amountLabel;
	}

	public JTextField getAmountField() {
		return amountField;
	}

	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}

	public JComboBox<Patient> getPatientDropDown() {
		return patientDropDown;
	}

	public void setPatientDropDown(JComboBox<Patient> patientDropDown) {
		this.patientDropDown = patientDropDown;
	}

	public JPanel getPatient() {
		return patient;
	}

	public void setPatient(JPanel patient) {
		this.patient = patient;
	}

	public JPanel getAmount() {
		return amount;
	}

	public void setAmount(JPanel amount) {
		this.amount = amount;
	}

	public JButton getAddPayment() {
		return addPayment;
	}

	public void setAddPayment(JButton addPayment) {
		this.addPayment = addPayment;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}
}
