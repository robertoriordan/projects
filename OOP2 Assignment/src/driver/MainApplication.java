package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import models.Patient;
import models.Procedure;
import views.Frame;
import views.PatientsView;
import views.PaymentsView;
import views.ProceduresView;
import views.ReportsView;
import controllers.FrameController;
import controllers.PatientsController;
import controllers.PaymentsController;
import controllers.ProceduresController;
import controllers.ReportsController;

public class MainApplication {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		ArrayList <Patient> patientList = new ArrayList <Patient> ();
		ArrayList <Procedure> procList = new ArrayList <Procedure> ();
		
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("patientList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         patientList = (ArrayList <Patient>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("ArrayList <Patient> class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("procList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         procList = (ArrayList <Procedure>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("ArrayList <Procedure> class not found");
	         c.printStackTrace();
	         return;
	      }
	      
		
		PatientsView p_view = new PatientsView(patientList);
		ProceduresView proc_view = new ProceduresView(patientList, procList);
		PaymentsView pay_view = new PaymentsView(patientList);
		ReportsView rep_view = new ReportsView(patientList);
		Frame window = new Frame(p_view, proc_view, pay_view, rep_view);
		
		PatientsController p_ctrl = new PatientsController(window.getPatientsPanel(), window.getProcPanel(), window.getPayPanel(), patientList);
		ProceduresController proc_ctrl = new ProceduresController(window.getProcPanel(), procList);
		PaymentsController pay_ctrl = new PaymentsController(window.getPayPanel());
		FrameController frame_ctrl = new FrameController(window);
		ReportsController rep_ctrl = new ReportsController(window.getReportPanel(), window.getProcPanel());

	}

}
