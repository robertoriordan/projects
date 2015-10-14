package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import models.Patient;
import models.Payment;
import views.PaymentsView;

public class PaymentsController {

	private PaymentsView view;
	private int payNum;
	private Date date;
	private double payAmt;
	
	public PaymentsController(PaymentsView view) {
		
		this.setView(view);
		payNum = 0;
		date = new Date();
		payAmt = 0;
		
		view.getAddPayment().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean valid = true;
				++payNum;
				
				try {
					payAmt = Double.parseDouble(view.getAmountField().getText());
				} catch(NumberFormatException n) {
					valid = false;
					JOptionPane.showMessageDialog(null, "Please enter a number in the Cost field.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				if (valid) {
					try {
						Patient selected = (Patient) view.getPatientDropDown().getSelectedItem();
						
						for (int x = 0; x < selected.getP_paymentList().size(); ++x) {
							
							if (selected.getP_paymentList().get(x).getPaymentNum() > payNum) {
								payNum = selected.getP_paymentList().get(x).getPaymentNum() + 1;
							}
						}
						
						selected.getP_paymentList().add(new Payment(payNum, payAmt, date));
						
						for (int x = 0; x < selected.getP_paymentList().size(); ++x) {
							
							System.out.println(selected.getP_paymentList().get(x));
						}
					} catch(NullPointerException n) {
						valid = false;
						JOptionPane.showMessageDialog(null, "No patient was selected in the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public PaymentsView getView() {
		return view;
	}

	public void setView(PaymentsView view) {
		this.view = view;
	}
}
