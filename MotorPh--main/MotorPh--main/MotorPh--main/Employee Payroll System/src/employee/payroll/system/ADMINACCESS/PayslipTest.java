package employee.payroll.system.ADMINACCESS;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import org.junit.Test;
import org.mockito.Mockito;

public class PayslipTest {

	
	    @Test
	    public void testConfirmationDialogForSave() throws Exception {
	        // Mock UI elements (if applicable)
	        JTextField txt_id = new JTextField("123"); // Replace with existing ID

	        // Create a controller instance with mock/dummy values
	        SalarySlipController controller = new SalarySlipController();

	        // Simulate user interaction
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // Capture potential dialog output
	        System.setOut(new PrintStream(outputStream));

	        controller.jButton1ActionPerformed(null);

	        // Assert confirmation dialog message
	        String output = outputStream.toString();
	        assertFalse(output.contains("Are you sure you want to save record?"));

	        // Restore standard output
	        System.setOut(System.out);
	    }

	    @Test
	    public void testCalculateSalaryDetails() throws Exception {
	        // Mock/Dummy data (replace with your data structure)
	        String empId = "123";
	        String deductionAmount = "100.00";
	        double insurance = 50.00;
	        String tax = "20.00";
	        String salary = "1500.00";
	        String netSalary = "1380.00";

	        // Calculate expected total salary
	        double expectedTotal = Double.parseDouble(salary) + Float.parseFloat(netSalary) - Double.parseDouble(deductionAmount);

	        // Simulate controller instance (replace with actual object creation)
	        SalarySlipController controller = new SalarySlipController();

	        // Simulate setting employee ID (assuming a setter exists)
	        controller.setEmployeeId(empId);

	        // Simulate retrieving details (replace with actual method call)
	        controller.retrieveEmployeeDetailsFromMockData(deductionAmount, insurance, tax, salary, netSalary); 

	        // Assert retrieved deduction amount (assuming getters exist)
	        assertEquals(Double.parseDouble(deductionAmount), controller.getDeductionAmount(), 100.0);

	        // Assert calculated total salary
	        assertEquals(expectedTotal, controller.getTotalSalary(), 2780.0);
	    }
	}
	


