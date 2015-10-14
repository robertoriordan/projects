package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Patient;
import views.PatientsView;
import views.PaymentsView;
import views.ProceduresView;

public class PatientsController {

	private PatientsView view;
	private ArrayList <Patient> patientList;
	private int patId;
	
	public PatientsController(PatientsView view, ProceduresView procView, PaymentsView payView, ArrayList <Patient> patientList) {
		
		this.setView(view);
		this.setPatientList(patientList);
		patId = 0;
		
		for (int x = 0; x < patientList.size(); ++x) {
			
			if (patientList.get(x).getPatientNum() > patId) {
				patId = patientList.get(x).getPatientNum();
			}
		}
		
		view.getAddPatient().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String patName = view.getNameField().getText();
				String patAdd = view.getAddressField().getText();
				String patPhone = view.getNumberField().getText();
				++patId;
				
				if (patName.isEmpty() || patAdd.isEmpty() || patPhone.isEmpty()) {
					valid = false;
					JOptionPane.showMessageDialog(null, "Some fields were left empty.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				if (valid) {
					//Adds patient to list with attributes from text fields
					patientList.add(new Patient(patName, patAdd, patPhone, patId));
					
					view.setPatientList(patientList);
					view.updateList();
					
					procView.setPatientList(patientList);
					procView.updatePatDrop();
					
					payView.setPatientList(patientList);
					payView.updatePatDrop();
				}
				
			}
		});
		
		view.getRemovePatient().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Patient selected = view.getList1().getSelectedValue();
				
				for (int x = 0; x < patientList.size(); ++x) {
					
					if (patientList.get(x) == selected) {
						patientList.remove(x);
					}
				}

				view.setPatientList(patientList);
				view.updateList();
				
				procView.setPatientList(patientList);
				procView.updatePatDrop();
				
				payView.setPatientList(patientList);
				payView.updatePatDrop();
			}
		});
	}

	public PatientsView getView() {
		return view;
	}

	public void setView(PatientsView view) {
		this.view = view;
	}

	public ArrayList <Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList <Patient> patientList) {
		this.patientList = patientList;
	}
}
