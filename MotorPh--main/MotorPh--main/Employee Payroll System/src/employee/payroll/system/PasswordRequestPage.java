package employee.payroll.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import employee.payroll.system.LOGINCONNECTION.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class PasswordRequestPage extends JFrame implements ActionListener {
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pst=null;

    private final JTextField usernameField;
    private final JButton submitButton;

    public PasswordRequestPage() {
        super("Password Request");

        // Create username field and submit button
        usernameField = new JTextField(20);
        submitButton = new JButton("Submit Request");
        submitButton.addActionListener(this);

        // Layout components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(new JLabel("Username:"), c);

        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(usernameField, c);

        c.gridy = 1;
        mainPanel.add(submitButton, c);

        // Add main panel to frame
        getContentPane().add(mainPanel);

        // Set frame properties
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = usernameField.getText();

            // Validation (optional)
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your username.");
                return;
            }

            // Logic to handle password request (replace with actual implementation)
            System.out.println("Password reset request sent for user: " + username );
            JOptionPane.showMessageDialog(this, "A password reset link has been sent to your email.");

            // Clear the username field after successful request
            usernameField.setText("");
        }
            
        }
        
        private void updateChangeLog() {
            try {
                // Get current date and time
                Date currentDate = GregorianCalendar.getInstance().getTime();
                DateFormat df = DateFormat.getDateInstance();
                String dateString = df.format(currentDate);

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String timeString = sdf.format(d);

                // Retrieve employee ID (replace with your logic)
                int value = Emp.empId; /* Your logic to get employee ID based on username */;

                // Prepare SQL statement
                String sql = "insert into changeLog (emp_id, date, status) values (?, ?, ?)";
                pst = conn.prepareStatement(sql);

                // Set values for prepared statement
                pst.setInt(1, value);
                pst.setString(2, timeString + " / " + dateString);
                pst.setString(3, "Password Reset Request");

                // Execute update
                pst.execute();

                JOptionPane.showMessageDialog(null, "Success!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                // Close resources (you might need to add closing for conn, rs, pst)
            }
        }

        public static void main(String[] args) {
            new PasswordRequestPage().setVisible(true);
        }
}