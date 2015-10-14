package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.Patient;
import models.Payment;
import models.Procedure;
import views.ProceduresView;
import views.ReportsView;

public class ReportsController {

	private ReportsView view;
	
	private ProceduresView procView;
	
	public ReportsController(ReportsView view, ProceduresView procView) {
		
		this.setView(view);
		this.setProcView(procView);
		ArrayList <Patient> patientList = procView.getPatientList();
		
		view.getGeneralReportButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				sortList(patientList);
				String reportText = "";
				Patient current = new Patient();
				ArrayList <Procedure> currentProcList = new ArrayList <Procedure> ();
				Procedure currentProc = new Procedure();
				ArrayList <Date> currentProcDateList = new ArrayList <Date> ();
				Date currentDate = new Date();
				DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				String dateText = "";
				ArrayList <Payment> currentPayList = new ArrayList <Payment> ();
				Payment currentPayment = new Payment();
				
				for (int x = 0; x < patientList.size(); ++x) {
					current = patientList.get(x);
					currentProcList = current.getP_procList();
					currentProcDateList = current.getP_proc_dateList();
					currentPayList = current.getP_paymentList();
					
					
					reportText += 
							"\nPatient:\n" +
							"\nNumber:\t" + current.getPatientNum() +
							"\nName:\t" + current.getPatientName() +
							"\nAddress:\t" + current.getPatientAdd() +
							"\nContact:\t" + current.getPatientPhone() +
							"\n\nProcedures:\n";
					
					for (int y = 0; y < currentProcList.size(); ++y) {
						
						currentProc = currentProcList.get(y);
						currentDate = currentProcDateList.get(y);
						dateText = f.format(currentDate);
						
						reportText +=
								"\nDate:\t" + dateText +
								"\nNumber:\t" + currentProc.getProcNum() +
								"\nName:\t" + currentProc.getProcName() +
								"\nCost:\t" + currentProc.getProcCost() + "\n";
					}
					
					reportText +=
							"\nPayments:\n";
					
					for (int y = 0; y < currentPayList.size(); ++y) {
						
						currentPayment = currentPayList.get(y);
						
						reportText += 
								currentPayment.toString();
					}
					
					reportText += "\n--------------------------------------------\n";
				}
				
				view.updateReport(reportText);
			}
		});
		
		view.getOverdueReportButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				sortList(patientList);
				Date today = new Date();
				String reportText = "";
				Patient current = new Patient();
				ArrayList <Procedure> currentProcList = new ArrayList <Procedure> ();
				Procedure currentProc = new Procedure();
				ArrayList <Date> currentProcDateList = new ArrayList <Date> ();
				Date currentDate = new Date();
				DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				String dateText = "";
				ArrayList <Payment> currentPayList = new ArrayList <Payment> ();
				Payment currentPayment = new Payment();
				long differenceInDays = 0;
				
				for (int x = 0; x < patientList.size(); ++x) {
					
					current = patientList.get(x);
					currentProcList = current.getP_procList();
					currentProcDateList = current.getP_proc_dateList();
					currentPayList = current.getP_paymentList();
					
					boolean overdue = false;
					
					for (int y = 0; y < currentPayList.size(); ++y) {
						
						currentPayment = currentPayList.get(y);
						
						//Change current date to test overdue 
						//(If changed to an overdue date, all patients with outstanding payments appear in report)
						/*try {
							currentDate = f.parse("12-04-2014");
						} catch (ParseException e1) {
						
						}*/
						currentDate = currentPayment.getPaymentDate();
						
						differenceInDays = getDateDiff(today, currentDate, TimeUnit.DAYS);
						differenceInDays = Math.abs(differenceInDays);
						
						if ((differenceInDays / 30) > 6 && current.moneyOwed() > 0) {
							overdue = true;
							y = currentPayList.size();
						}

					}
					
					if (overdue) {
						reportText += 
								"\nPatient:\n" +
								"\nNumber:\t" + current.getPatientNum() +
								"\nName:\t" + current.getPatientName() +
								"\nAddress:\t" + current.getPatientAdd() +
								"\nContact:\t" + current.getPatientPhone() +
								"\n\nProcedures:\n";
						
						for (int y = 0; y < currentProcList.size(); ++y) {
							
							currentProc = currentProcList.get(y);
							currentDate = currentProcDateList.get(y);
							dateText = f.format(currentDate);
							
							reportText +=
									"\nDate:\t" + dateText +
									"\nNumber:\t" + currentProc.getProcNum() +
									"\nName:\t" + currentProc.getProcName() +
									"\nCost:\t" + currentProc.getProcCost() + "\n";
						}
						
						reportText +=
								"\nPayments:\n";
						
						for (int y = 0; y < currentPayList.size(); ++y) {
							
							currentPayment = currentPayList.get(y);
							
							reportText += 
									currentPayment.toString();
						}
						
						reportText += "\n--------------------------------------------\n";
					}
					
					view.updateReport(reportText);
				}
			}
		});
	}
	
	/*
	It should be noted that I had a lot of trouble with finding the difference between 
	the two dates for the overdue report and credit should be given to this stack overflow
	page for the method below: http://stackoverflow.com/questions/1555262/calculating-the-
	difference-between-two-java-date-instances?page=1&tab=votes#tab-top
	*/
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public void sortList(ArrayList <Patient> patientList) {
		
		boolean swap = true;
		
		while (swap) {
			
			swap = false;
			
			for (int x = 0; x < patientList.size() - 1; ++x) {
				
				int comparison = 0;
				
				Patient patient1 = patientList.get(x);
				Patient patient2 = patientList.get(x +1);
				
				String name1 = patient1.getPatientName().toLowerCase();
				String name2 = patient2.getPatientName().toLowerCase();
				
				comparison = name1.compareTo(name2);
				
				if (comparison > 0) {
					patientList.set(x, patient2);
					patientList.set(x + 1, patient1);
					swap = true;
				}
			
			}
		}
	}

	public ReportsView getView() {
		return view;
	}

	public void setView(ReportsView view) {
		this.view = view;
	}

	public ProceduresView getProcView() {
		return procView;
	}

	public void setProcView(ProceduresView procView) {
		this.procView = procView;
	}
}
