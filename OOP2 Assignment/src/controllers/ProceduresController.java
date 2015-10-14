package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Patient;
import models.Procedure;
import views.ProceduresView;

public class ProceduresController {

	private ProceduresView view;
	private ArrayList <Procedure> procList;
	private int procNum;
	
	public ProceduresController(ProceduresView view, ArrayList <Procedure> procList) {
		
		this.setView(view);
		this.setProcList(procList);
		procNum = 0;
		
		for (int x = 0; x < procList.size(); ++x) {
			
			if (procList.get(x).getProcNum() > procNum) {
				procNum = procList.get(x).getProcNum();
			}
		}
		
		view.getAddProcedure().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String procName = view.getNameField().getText();
				double procCost = 0;
				
				try {
					
					procCost = Double.parseDouble(view.getCostField().getText());
				} catch(NumberFormatException n) {
					valid = false;
					JOptionPane.showMessageDialog(null, "Please enter a number in the Cost field.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				++procNum;
				
				if (procName.equals("")) {
					valid = false;
					JOptionPane.showMessageDialog(null, "Some fields were left empty.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				if (valid) {
					procList.add(new Procedure(procName, procCost, procNum));
					
					view.setProcList(procList);
					view.updateList();
					view.updateProcDrop();
				}
			}
		});
		
		view.getDeleteProcedure().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Procedure selected = view.getList1().getSelectedValue();
				
				for (int x = 0; x < procList.size(); ++x) {
					
					if (procList.get(x) == selected) {
						procList.remove(x);
					}
				}
				
				view.setProcList(procList);
				view.updateList();
				view.updateProcDrop();
			}
		});
		
		view.getEditProcedure().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Procedure selected = view.getList1().getSelectedValue();
				
				JLabel nameLabel = new JLabel("Procedure Name: ");
				JLabel costLabel = new JLabel("Procedure Cost: ");
				JTextField nameField = new JTextField(selected.getProcName());
				JTextField costField = new JTextField(Double.toString(selected.getProcCost()));
				
				JPanel dialog = new JPanel();
				
				dialog.add(nameLabel);
				dialog.add(nameField);
				dialog.add(costLabel);
				dialog.add(costField);
				
				int result = JOptionPane.showConfirmDialog(null, dialog, 
			               "Edit Procedure " + selected.getProcNum(), JOptionPane.OK_CANCEL_OPTION);
				
				if (result == JOptionPane.OK_OPTION) {
					
					boolean valid = true;
					
					if (nameField.getText().equals("")) { 
						valid = false;
						JOptionPane.showMessageDialog(null, "Some fields were left empty.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					double cost = 0;
					
					try {
					cost = Double.parseDouble(costField.getText());
					} catch(NumberFormatException n) {
						valid = false;
						JOptionPane.showMessageDialog(null, "Please enter a number in the Cost field.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if (valid) {
						selected.setProcName(nameField.getText());
						selected.setProcCost(cost);
						view.updateList();
					}
				}
			}
		});
		
		view.getListP_Procedures().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			Patient selectedPat = (Patient) view.getPatientDropDown().getSelectedItem();
			
			view.updateList2(selectedPat);
			}
		});
		
		view.getAddP_Procedure().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			Procedure selectedProc = (Procedure) view.getProcDropDown().getSelectedItem();
			Patient selectedPat = (Patient) view.getPatientDropDown().getSelectedItem();
			String enteredDate = view.getDateField().getText();
			Date date = new Date();
			boolean valid = true;
			
			if (selectedProc == null) {
				valid = false;
				JOptionPane.showMessageDialog(null, "No procedure was selected in the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = format.parse(enteredDate);
			} catch (ParseException e1) {
				valid = false;
				JOptionPane.showMessageDialog(null, "Please enter a date in the format dd-mm-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			try {
				
				for (int x = 0; x < selectedPat.getP_procList().size(); ++x) {
					
					if (selectedPat.getP_procList().get(x) == selectedProc) {
						valid = false;
						JOptionPane.showMessageDialog(null, "This procedure has already been assigned to this patient.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch(NullPointerException n) {
				
				valid = false;
				JOptionPane.showMessageDialog(null, "No patient was selected in the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
 			
			if (valid == true) {
				selectedPat.getP_procList().add(selectedProc);
				selectedPat.getP_proc_dateList().add(date);
				
				System.out.println(date);
				
				view.updateList2(selectedPat);
			}
			}
		});
		
		view.getDeleteP_Procedure().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProc = view.getList2().getSelectedValue();
				Patient selectedPat = (Patient) view.getPatientDropDown().getSelectedItem();
				
				for (int x = 0; x < selectedPat.getP_procList().size(); ++x) {
					
					if(selectedPat.getP_procList().get(x) == selectedProc) {
						selectedPat.getP_procList().remove(x);
						selectedPat.getP_proc_dateList().remove(x);
						view.updateList2(selectedPat);
					}
				}
			}
		});
	}

	public ProceduresView getView() {
		return view;
	}

	public void setView(ProceduresView view) {
		this.view = view;
	}

	public ArrayList <Procedure> getProcList() {
		return procList;
	}

	public void setProcList(ArrayList <Procedure> procList) {
		this.procList = procList;
	}
}
