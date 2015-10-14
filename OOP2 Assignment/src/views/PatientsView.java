package views;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.Patient;

public class PatientsView extends JPanel{

	private static final long serialVersionUID = -2726684111905166880L;
	
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel numberLabel;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField numberField;
	
	private JButton addPatient;
	private JButton removePatient;
	
	private JPanel text;
	private JPanel buttons;
	
	private DefaultListModel <Patient> listModel;
	private JList <Patient> list1;
	private JScrollPane listScroll;
	
	private ArrayList <Patient> patientList;
	
	//Constructor
	public PatientsView(ArrayList <Patient> patientList) {
		
		this.patientList = patientList;
		
		nameLabel = new JLabel("Patient Name: ");
		addressLabel = new JLabel("Address: ");
		numberLabel = new JLabel("Contact Number: ");
		
		nameField = new JTextField(10);
		addressField = new JTextField(10);
		numberField = new JTextField(10);
		
		addPatient = new JButton("Add Patient");
		removePatient = new JButton("Remove Patient");
		
		text = new JPanel();
		buttons = new JPanel();
		
		listModel = new DefaultListModel <Patient> ();
		
		for(int x = 0; x < patientList.size(); ++x) {
			listModel.addElement(patientList.get(x));
		}
		
		list1 = new JList <Patient> (listModel);
		listScroll = new JScrollPane(list1);
		
		addComponents();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void addComponents() {
		
		add(Box.createVerticalStrut(10));
		
		text.add(nameLabel);
		text.add(nameField);
		text.add(Box.createHorizontalStrut(10));
		text.add(addressLabel);
		text.add(addressField);
		text.add(Box.createHorizontalStrut(10));
		text.add(numberLabel);
		text.add(numberField);
		
		add(text);
		
		add(Box.createVerticalStrut(10));
		
		Dimension max = new Dimension(500, 100);
		text.setMaximumSize(max);
		text.setLayout(new BoxLayout(text, BoxLayout.LINE_AXIS));
		
		buttons.add(Box.createHorizontalStrut(120));
		buttons.add(addPatient);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(removePatient);
		
		add(buttons);
		
		add(Box.createVerticalStrut(10));
		
		buttons.setMaximumSize(max);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		
		add(listScroll);
	}
	
	public void updateList() {
		
		listModel.removeAllElements();
		
		for(int x = 0; x < patientList.size(); ++x) {
			listModel.addElement(patientList.get(x));
		}

	}
	
	
	//Getters & Setters
	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getAddressLabel() {
		return addressLabel;
	}

	public void setAddressLabel(JLabel addressLabel) {
		this.addressLabel = addressLabel;
	}

	public JLabel getNumberLabel() {
		return numberLabel;
	}

	public void setNumberLabel(JLabel numberLabel) {
		this.numberLabel = numberLabel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public JTextField getNumberField() {
		return numberField;
	}

	public void setNumberField(JTextField numberField) {
		this.numberField = numberField;
	}

	public JButton getAddPatient() {
		return addPatient;
	}

	public void setAddPatient(JButton addPatient) {
		this.addPatient = addPatient;
	}

	public JButton getRemovePatient() {
		return removePatient;
	}

	public void setRemovePatient(JButton removePatient) {
		this.removePatient = removePatient;
	}

	public DefaultListModel<Patient> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<Patient> listModel) {
		this.listModel = listModel;
	}

	public JList<Patient> getList1() {
		return list1;
	}

	public void setList1(JList<Patient> list1) {
		this.list1 = list1;
	}

	public JScrollPane getListScroll() {
		return listScroll;
	}

	public void setListScroll(JScrollPane listScroll) {
		this.listScroll = listScroll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList <Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList <Patient> patientList) {
		this.patientList = patientList;
	}
}
