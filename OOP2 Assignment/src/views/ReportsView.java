package views;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import models.Patient;

public class ReportsView extends JPanel{

	private static final long serialVersionUID = 4439274430814959020L;
	
	private JButton generalReportButton;
	private JButton overdueReportButton;
	
	private JTextArea report;
	
	private JScrollPane scroll;
	
	private ArrayList <Patient> patientList;
	
	
	public ReportsView(ArrayList <Patient> patientList) {
		generalReportButton = new JButton("Generate General Report");
		overdueReportButton = new JButton("Generate Overdue Payment Report");
		
		report = new JTextArea();
		
		report.setEditable(false);
		
		scroll = new JScrollPane(report);
		
		this.patientList = patientList;
		
		addComponents();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void addComponents() {
		
		JPanel buttons = new JPanel();
		
		add(Box.createVerticalStrut(10));
		
		buttons.add(Box.createHorizontalStrut(40));
		buttons.add(generalReportButton);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(overdueReportButton);
		
		Dimension max = new Dimension(500, 100);
		buttons.setMaximumSize(max);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		
		add(buttons);
		
		add(Box.createVerticalStrut(10));
		
		add(scroll);
	}
	
	public void updateReport(String reportText) {
		
		report.setText(reportText);
	}

	public JButton getGeneralReportButton() {
		return generalReportButton;
	}

	public void setGeneralReportButton(JButton generalReportButton) {
		this.generalReportButton = generalReportButton;
	}

	public JButton getOverdueReportButton() {
		return overdueReportButton;
	}

	public void setOverdueReportButton(JButton overdueReportButton) {
		this.overdueReportButton = overdueReportButton;
	}

	public JTextArea getReport() {
		return report;
	}

	public void setReport(JTextArea report) {
		this.report = report;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
