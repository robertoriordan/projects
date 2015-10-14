package views;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.Patient;
import models.Procedure;

public class ProceduresView extends JPanel {

	private static final long serialVersionUID = -7147250264439991747L;
	
	private JLabel nameLabel;
	private JLabel costLabel;
	
	private JTextField nameField;
	private JTextField costField;
	
	private JButton addProcedure;
	private JButton editProcedure;
	private JButton deleteProcedure;
	
	private JPanel text;
	private JPanel buttons;
	
	private DefaultListModel <Procedure> listModel;
	private JList <Procedure> list1;
	private JScrollPane listScroll;
	
	private JLabel patientsLabel;
	private JLabel procLabel;
	private JLabel dateLabel;
	
	private JComboBox <Patient> patientDropDown;
	private JComboBox <Procedure> procDropDown;
	
	private JTextField dateField;
	
	private JButton addP_Procedure;
	private JButton deleteP_Procedure;
	private JButton	listP_Procedures;
	
	private JPanel patients;
	private JPanel procedures;
	private JPanel dateInput;
	private JPanel buttons2;
	
	private DefaultListModel <Procedure> listModel2;
	private JList <Procedure> list2;
	private JScrollPane listScroll2;
	
	private ArrayList <Patient> patientList;
	private ArrayList <Procedure> procList;
	
	//Constructor
	public ProceduresView(ArrayList <Patient> patientList, ArrayList <Procedure> procList) {
		
		this.patientList = patientList;
		this.procList = procList;
		
		nameLabel = new JLabel("Procedure Name: ");
		costLabel = new JLabel("Procedure Cost: ");
		
		nameField = new JTextField();
		costField = new JTextField();
		
		addProcedure = new JButton("Add Procedure");
		editProcedure = new JButton("Edit Procedure");
		deleteProcedure = new JButton("Delete Procedure");
		
		text = new JPanel();
		buttons = new JPanel();
		
		listModel = new DefaultListModel <Procedure> ();
		
		for (int x = 0; x < procList.size(); ++x) {
			
			listModel.addElement(procList.get(x));
		}
		
		list1 = new JList <Procedure> (listModel);
		listScroll = new JScrollPane(list1);
		
		patientsLabel = new JLabel("Patients:       ");
		procLabel = new JLabel("Procedures: ");
		dateLabel = new JLabel("Date: ");
		
		patientDropDown = new JComboBox <Patient> ();
		
		for (int x = 0; x < patientList.size(); ++x) {
			
			patientDropDown.addItem(patientList.get(x));
		}
		
		procDropDown = new JComboBox <Procedure> ();
		
		for (int x = 0; x < procList.size(); ++x) {
			
			procDropDown.addItem(procList.get(x));
		}
		
		dateField = new JTextField();
		
		addP_Procedure = new JButton("Add Procedure");
		deleteP_Procedure = new JButton("Delete Procedure");
		listP_Procedures = new JButton("List Procedures");
		
		patients = new JPanel();
		procedures = new JPanel();
		dateInput = new JPanel();
		buttons2 = new JPanel();
		
		listModel2 = new DefaultListModel <Procedure> ();
		list2 = new JList <Procedure> (listModel2);
		listScroll2 = new JScrollPane(list2);
		
		addComponents();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
	}
	
	public void updateList() {
		
		listModel.removeAllElements();
		
		for(int x = 0; x < procList.size(); ++x) {
			listModel.addElement(procList.get(x));
		}
	}
	
	public void updateList2(Patient patient) {
		
		listModel2.removeAllElements();
		
		for(int x = 0; x < patient.getP_procList().size(); ++x) {
			listModel2.addElement(patient.getP_procList().get(x));
		}
	}
	
	public void updatePatDrop() {
		
		patientDropDown.removeAllItems();
		
		for (int x = 0; x < patientList.size(); ++x) {
			
			patientDropDown.addItem(patientList.get(x));
		}
	}
	
	public void updateProcDrop() {
		
		procDropDown.removeAllItems();
		
		for (int x = 0; x < procList.size(); ++x) {
			
			procDropDown.addItem(procList.get(x));
		}
	}
	
	public void addComponents() {
		
		add(Box.createVerticalStrut(10));
		
		text.add(nameLabel);
		text.add(nameField);
		text.add(Box.createHorizontalStrut(10));
		text.add(costLabel);
		text.add(costField);
		
		Dimension max = new Dimension(500, 100);
		text.setMaximumSize(max);
		text.setLayout(new BoxLayout(text, BoxLayout.LINE_AXIS));
		
		add(text);
		
		add(Box.createVerticalStrut(10));
		
		buttons.add(addProcedure);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(editProcedure);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(deleteProcedure);
		
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		
		add(buttons);
		
		add(Box.createVerticalStrut(10));
		
		add(listScroll);
		
		add(Box.createVerticalStrut(10));
		
		patients.add(patientsLabel);
		patients.add(patientDropDown);
		
		patients.setMaximumSize(max);
		patients.setLayout(new BoxLayout(patients, BoxLayout.LINE_AXIS));
		
		add(patients);
		
		add(Box.createVerticalStrut(10));
		
		procedures.add(procLabel);
		procedures.add(procDropDown);
		
		procedures.setMaximumSize(max);
		procedures.setLayout(new BoxLayout(procedures, BoxLayout.LINE_AXIS));
		
		add(procedures);
		
		add(Box.createVerticalStrut(10));
		
		dateInput.add(dateLabel);
		dateInput.add(dateField);
		
		dateInput.setMaximumSize(new Dimension(120, 100));
		dateInput.setLayout(new BoxLayout(dateInput, BoxLayout.LINE_AXIS));
		
		add(dateInput);
		
		add(Box.createVerticalStrut(10));
		
		buttons2.add(addP_Procedure);
		buttons2.add(Box.createHorizontalStrut(10));
		buttons2.add(deleteP_Procedure);
		buttons2.add(Box.createHorizontalStrut(10));
		buttons2.add(listP_Procedures);
		
		buttons2.setLayout(new BoxLayout(buttons2, BoxLayout.LINE_AXIS));
		
		add(buttons2);
		
		add(Box.createVerticalStrut(10));
		
		add(listScroll2);
	}

	public JComboBox<Patient> getPatientDropDown() {
		return patientDropDown;
	}

	public void setPatientDropDown(JComboBox<Patient> patientDropDown) {
		this.patientDropDown = patientDropDown;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getCostLabel() {
		return costLabel;
	}

	public void setCostLabel(JLabel costLabel) {
		this.costLabel = costLabel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getCostField() {
		return costField;
	}

	public void setCostField(JTextField costField) {
		this.costField = costField;
	}

	public JButton getAddProcedure() {
		return addProcedure;
	}

	public void setAddProcedure(JButton addProcedure) {
		this.addProcedure = addProcedure;
	}

	public JButton getEditProcedure() {
		return editProcedure;
	}

	public void setEditProcedure(JButton editProcedure) {
		this.editProcedure = editProcedure;
	}

	public JButton getDeleteProcedure() {
		return deleteProcedure;
	}

	public void setDeleteProcedure(JButton deleteProcedure) {
		this.deleteProcedure = deleteProcedure;
	}

	public JPanel getText() {
		return text;
	}

	public void setText(JPanel text) {
		this.text = text;
	}

	public JPanel getButtons() {
		return buttons;
	}

	public void setButtons(JPanel buttons) {
		this.buttons = buttons;
	}

	public DefaultListModel<Procedure> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<Procedure> listModel) {
		this.listModel = listModel;
	}

	public JList<Procedure> getList1() {
		return list1;
	}

	public void setList1(JList<Procedure> list1) {
		this.list1 = list1;
	}

	public JScrollPane getListScroll() {
		return listScroll;
	}

	public void setListScroll(JScrollPane listScroll) {
		this.listScroll = listScroll;
	}

	public ArrayList<Procedure> getProcList() {
		return procList;
	}

	public void setProcList(ArrayList<Procedure> procList) {
		this.procList = procList;
	}

	public JLabel getPatientsLabel() {
		return patientsLabel;
	}

	public void setPatientsLabel(JLabel patientsLabel) {
		this.patientsLabel = patientsLabel;
	}

	public JLabel getProcLabel() {
		return procLabel;
	}

	public void setProcLabel(JLabel procLabel) {
		this.procLabel = procLabel;
	}

	public JComboBox<Procedure> getProcDropDown() {
		return procDropDown;
	}

	public void setProcDropDown(JComboBox<Procedure> procDropDown) {
		this.procDropDown = procDropDown;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	public JTextField getDateField() {
		return dateField;
	}

	public void setDateField(JTextField dateField) {
		this.dateField = dateField;
	}

	public JPanel getPatients() {
		return patients;
	}

	public void setPatients(JPanel patients) {
		this.patients = patients;
	}

	public JPanel getProcedures() {
		return procedures;
	}

	public void setProcedures(JPanel procedures) {
		this.procedures = procedures;
	}

	public JPanel getDateInput() {
		return dateInput;
	}

	public void setDateInput(JPanel dateInput) {
		this.dateInput = dateInput;
	}

	public JButton getAddP_Procedure() {
		return addP_Procedure;
	}

	public void setAddP_Procedure(JButton addP_Procedure) {
		this.addP_Procedure = addP_Procedure;
	}

	public JButton getDeleteP_Procedure() {
		return deleteP_Procedure;
	}

	public void setDeleteP_Procedure(JButton deleteP_Procedure) {
		this.deleteP_Procedure = deleteP_Procedure;
	}

	public JPanel getButtons2() {
		return buttons2;
	}

	public void setButtons2(JPanel buttons2) {
		this.buttons2 = buttons2;
	}

	public DefaultListModel<Procedure> getListModel2() {
		return listModel2;
	}

	public void setListModel2(DefaultListModel<Procedure> listModel2) {
		this.listModel2 = listModel2;
	}

	public JList<Procedure> getList2() {
		return list2;
	}

	public void setList2(JList<Procedure> list2) {
		this.list2 = list2;
	}

	public JScrollPane getListScroll2() {
		return listScroll2;
	}

	public void setListScroll2(JScrollPane listScroll2) {
		this.listScroll2 = listScroll2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getListP_Procedures() {
		return listP_Procedures;
	}

	public void setListP_Procedures(JButton listP_Procedures) {
		this.listP_Procedures = listP_Procedures;
	}
	
	
}
