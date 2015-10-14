package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class Frame extends JFrame{

	private static final long serialVersionUID = -7302379632274385914L;
	
	private JTabbedPane tabs;
	private PatientsView patientsPanel;
	private ProceduresView procPanel;
	private PaymentsView payPanel;
	private ReportsView reportPanel;
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem save;
	
	public Frame(PatientsView patientsPanel, ProceduresView procPanel, PaymentsView payPanel, ReportsView reportPanel) {
		
		super("Dental Clinic Manager");
		
		this.patientsPanel = patientsPanel;
		this.procPanel = procPanel;
		this.payPanel = payPanel;
		this.reportPanel = reportPanel;
		tabs = new JTabbedPane();
		bar = new JMenuBar();
		file = new JMenu("File");
		save = new JMenuItem("Save");
		
		addComponents();
		
		setSize(500,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addComponents() {
		
		tabs.addTab("Patients", patientsPanel);
		tabs.addTab("Procedures", procPanel);
		tabs.addTab("Payments", payPanel);
		tabs.addTab("Reports", reportPanel);
		
		file.add(save);
		bar.add(file);
		
		setJMenuBar(bar);
		getContentPane().add(tabs);
		
	}

	//Getters & Setters
	public JTabbedPane getTabs() {
		return tabs;
	}

	public void setTabs(JTabbedPane tabs) {
		this.tabs = tabs;
	}

	public PatientsView getPatientsPanel() {
		return patientsPanel;
	}

	public void setPatientsPanel(PatientsView patientsPanel) {
		this.patientsPanel = patientsPanel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProceduresView getProcPanel() {
		return procPanel;
	}

	public void setProcPanel(ProceduresView procPanel) {
		this.procPanel = procPanel;
	}

	public PaymentsView getPayPanel() {
		return payPanel;
	}

	public void setPayPanel(PaymentsView payPanel) {
		this.payPanel = payPanel;
	}

	public ReportsView getReportPanel() {
		return reportPanel;
	}

	public void setReportPanel(ReportsView reportPanel) {
		this.reportPanel = reportPanel;
	}

	public JMenuBar getBar() {
		return bar;
	}

	public void setBar(JMenuBar bar) {
		this.bar = bar;
	}

	public JMenu getFile() {
		return file;
	}

	public void setFile(JMenu file) {
		this.file = file;
	}

	public JMenuItem getSave() {
		return save;
	}

	public void setSave(JMenuItem save) {
		this.save = save;
	}
}
