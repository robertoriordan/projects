package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Patient;
import models.Procedure;
import views.Frame;

public class FrameController {

	private Frame window;
	private ArrayList <Patient> patientList;
	private ArrayList <Procedure> procList;
	
	public FrameController(Frame window) {
		
		this.setWindow(window);
		patientList = new ArrayList <Patient> ();
		procList = new ArrayList <Procedure> ();
		
		window.getSave().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				patientList = window.getPatientsPanel().getPatientList();
				procList = window.getProcPanel().getProcList();
				
			      try
			      {
			         FileOutputStream fileOut = new FileOutputStream("patientList.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(patientList);
			         out.close();
			         fileOut.close();
			         System.out.printf("\npatientList arrayList is saved in patientList.ser");
			      }catch(IOException i)
			      {
			          i.printStackTrace();
			      }
			      
			      try
			      {
			         FileOutputStream fileOut = new FileOutputStream("procList.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(procList);
			         out.close();
			         fileOut.close();
			         System.out.printf("\nprocList arrayList data is saved in procList.ser");
			      }catch(IOException i)
			      {
			          i.printStackTrace();
			      }
			}
		});
	}

	public Frame getWindow() {
		return window;
	}

	public void setWindow(Frame window) {
		this.window = window;
	}
	
}
