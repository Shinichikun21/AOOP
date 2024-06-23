/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package employee.payroll.system;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import employee.payroll.system.LOGINCONNECTION.Emp;
import employee.payroll.system.LOGINCONNECTION.db;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author almos
 */
public class Overtime extends javax.swing.JFrame {
     Connection conn=null;
     ResultSet rs=null;
     PreparedStatement pst=null;

    /**
     * Creates new form LeaveRequest
     */
     public Overtime() {
    	getContentPane().setBackground(new Color(167, 199, 245));
        initComponents();
        conn=db.java_db();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, 
        size.height/2 - getHeight()/2);
        loadLeaveRequestData();
        txt_emp.setText(String.valueOf(Emp.empId).toString());
        
    }private void loadLeaveRequestData() {
    try {
        String sql = "SELECT request_id, employee_id, firstname, surname, reason, hours, status FROM Overtime";
        pst = conn.prepareStatement(sql);

        rs = pst.executeQuery();

        if (rs.next()) {
            txt_id.setText(rs.getString("request_id")); // 
            txt_empid.setText(rs.getString("employee_id")); // 
            txt_firstname.setText(rs.getString("firstname"));
            txt_surname.setText(rs.getString("surname"));
            txt_reason.setText(rs.getString("reason"));
            txt_start.setText(rs.getString("hours"));
            txt_status.setText(rs.getString("status"));

            lbl_req.setText("LIST REQUEST");
        } else {
            JOptionPane.showMessageDialog(null, "No leave request found for this employee.");
            lbl_req.setText("There is no Request");
        }
        
        // Logging to Audit table
        String value0 = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String values = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String val = txt_id.getText(); // Assuming txt_empid holds the ID of the employee who initiated the action
        String reg = "INSERT INTO Audit (emp_id, date, status) VALUES (?, ?, ?)";
        pst = conn.prepareStatement(reg);
        pst.setString(1, val); // Log the ID of the employee who initiated the action
        pst.setString(2, value0 + " / " + values);
        pst.setString(3, "Viewed Leave Request");
        pst.executeUpdate();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setForeground(new Color(0, 0, 0));
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setForeground(new Color(0, 0, 0));
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setForeground(new Color(0, 0, 0));
        lbl_req = new javax.swing.JLabel();
        lbl_req.setForeground(new Color(0, 0, 0));
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setForeground(new Color(0, 0, 0));
        txt_emp = new javax.swing.JLabel();
        txt_emp.setForeground(new Color(0, 0, 0));
        Reason = new javax.swing.JLabel();
        Reason.setForeground(new Color(0, 0, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_reason = new javax.swing.JTextArea();
        txt_reason.setTabSize(9);
        txt_id = new javax.swing.JTextField();
        txt_firstname = new javax.swing.JTextField();
        txt_surname = new javax.swing.JTextField();
        txt_start = new javax.swing.JTextField();
        lbl_accept = new javax.swing.JButton();
        lbl_accept.setForeground(new Color(0, 0, 0));
        lbl_accept.setBackground(new Color(119, 191, 142));
        lbl_decline = new javax.swing.JButton();
        lbl_decline.setForeground(new Color(0, 0, 0));
        lbl_decline.setBackground(new Color(255, 128, 128));
        Status = new javax.swing.JLabel();
        Status.setForeground(new Color(0, 0, 0));
        txt_status = new javax.swing.JTextField();
        lbl_next = new javax.swing.JButton();
        lbl_next.setBackground(new Color(255, 255, 255));
        lbl_next.setIcon(new ImageIcon(Overtime.class.getResource("/employee/payroll/system/Images/icons8-next-20.png")));
        lbl_prev = new javax.swing.JButton();
        lbl_prev.setBackground(new Color(255, 255, 255));
        lbl_prev.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lbl_prev.setIcon(new ImageIcon(Overtime.class.getResource("/employee/payroll/system/Images/icons8-previous-20.png")));
        lbl_prev.setForeground(new Color(0, 0, 0));
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setForeground(new Color(0, 0, 0));
        txt_empid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton2.setForeground(new Color(0, 0, 0));
        jButton2.setBackground(new Color(252, 131, 124));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("EMPLOYEE OVERTIME REQUEST");

        jLabel3.setText("Employee ID:");

        jLabel4.setText("FIrst Name");

        jLabel5.setText("Surname:");

        jLabel6.setText("Hours:");

        lbl_req.setText("Request");

        jLabel9.setText("logged in As");

        txt_emp.setText("Employee ID");

        Reason.setText("Reason:");

        txt_reason.setColumns(20);
        txt_reason.setRows(5);
        jScrollPane1.setViewportView(txt_reason);

        txt_id.setEditable(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_firstname.setEditable(false);

        txt_surname.setEditable(false);

        txt_start.setEditable(false);

        lbl_accept.setText("Accept");
        lbl_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_acceptActionPerformed(evt);
            }
        });

        lbl_decline.setText("Decline");
        lbl_decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_declineActionPerformed(evt);
            }
        });

        Status.setText("Status:");

        txt_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_statusActionPerformed(evt);
            }
        });
        lbl_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_nextActionPerformed(evt);
            }
        });
        lbl_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_prevActionPerformed(evt);
            }
        });

        jLabel8.setText("Request No.");

        txt_empid.setEditable(false);

        jButton2.setText("Delete Record");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(53)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel9)
        					.addGap(18)
        					.addComponent(txt_emp, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        					.addGap(0, 417, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(Reason)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(Status, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(txt_status, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
        					.addContainerGap(59, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel3)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(txt_empid, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(jLabel5)
        										.addComponent(jLabel4))
        									.addGap(24)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        										.addComponent(txt_firstname, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
        										.addComponent(txt_surname, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel8)
        									.addGap(18)
        									.addComponent(txt_id, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
        							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel6)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(txt_start, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
        									.addGap(68))
        								.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        									.addComponent(lbl_accept, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(lbl_decline)
        									.addGap(24))))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(lbl_prev)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(lbl_next)
        							.addGap(28)))
        					.addGap(42))))
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap(165, Short.MAX_VALUE)
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
        					.addGap(70))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(267)
        					.addComponent(lbl_req)
        					.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)))
        			.addComponent(jButton2)
        			.addGap(26))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lbl_req)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(15)
        					.addComponent(jButton2)))
        			.addGap(26)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel6)
        					.addComponent(txt_start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel8)
        						.addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(71)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_accept, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lbl_decline))
        					.addPreferredGap(ComponentPlacement.UNRELATED))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel3)
        						.addComponent(txt_empid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel4)
        						.addComponent(txt_firstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel5)
        						.addComponent(txt_surname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(34)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(Reason)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(Status)
        						.addComponent(txt_status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel9)
        						.addComponent(txt_emp))
        					.addGap(153))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(5)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lbl_prev, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lbl_next, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap(172, Short.MAX_VALUE))))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_declineActionPerformed
        // TODO add your handling code here:
    try {
    // Update the status of the current request
    String statusUpdateSql = "";
    String action = "";
    if (evt.getSource() == lbl_accept) {
        statusUpdateSql = "UPDATE Overtime SET status = 'Accepted' WHERE request_id = ?";
        action = "Accepted";
        txt_status.setText("Accepted");
    } else if (evt.getSource() == lbl_decline) {
        statusUpdateSql = "UPDATE Overtime SET status = 'Declined' WHERE request_id = ?";
        action = "Declined";
        txt_status.setText("Declined");
    }
    pst = conn.prepareStatement(statusUpdateSql);
    pst.setInt(1, Integer.parseInt(txt_id.getText()));
    pst.executeUpdate();

    // Logging to Audit table for accepting or declining the request
    String value0 = new SimpleDateFormat("HH:mm:ss").format(new Date());
    String values = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    String val = txt_empid.getText(); // Assuming txt_empid holds the ID of the employee who accepted or declined the request
    String reg = "INSERT INTO Audit (emp_id, date, status) VALUES (?, ?, ?)";
    pst = conn.prepareStatement(reg);
    pst.setString(1, val);
    pst.setString(2, value0 + " / " + values);
    pst.setString(3, action + " Overtime");
    pst.executeUpdate();
    
    // Show a message indicating that the request is accepted or declined
    JOptionPane.showMessageDialog(null, "The Request is " + action);

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
} finally {
    try {
        if (pst != null) pst.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}



    }//GEN-LAST:event_lbl_declineActionPerformed

    private void lbl_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_acceptActionPerformed
        // TODO add your handling code here:
 try {
    // Update the status of the current request
    String statusUpdateSql = "";
    String action = "";
    if (evt.getSource() == lbl_accept) {
        statusUpdateSql = "UPDATE LeaveRequests SET status = 'Accepted' WHERE request_id = ?";
        action = "Accepted";
        txt_status.setText("Accepted");
    } else if (evt.getSource() == lbl_decline) {
        statusUpdateSql = "UPDATE LeaveRequests SET status = 'Declined' WHERE request_id = ?";
        action = "Declined";
        txt_status.setText("Declined");
    }
    pst = conn.prepareStatement(statusUpdateSql);
    pst.setInt(1, Integer.parseInt(txt_id.getText()));
    pst.executeUpdate();

    // Logging to Audit table for accepting or declining the request
    String value0 = new SimpleDateFormat("HH:mm:ss").format(new Date());
    String values = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    String val = txt_empid.getText(); // Assuming txt_empid holds the ID of the employee who accepted or declined the request
    String reg = "INSERT INTO Audit (emp_id, date, status) VALUES (?, ?, ?)";
    pst = conn.prepareStatement(reg);
    pst.setString(1, val);
    pst.setString(2, value0 + " / " + values);
    pst.setString(3, action + " Leave Requests");
    pst.executeUpdate();
    
    // Show a message indicating that the request is accepted or declined
    JOptionPane.showMessageDialog(null, "The Request is " + action);

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
} finally {
    try {
        if (pst != null) pst.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}





    }//GEN-LAST:event_lbl_acceptActionPerformed

    private void lbl_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_prevActionPerformed
        // TODO add your handling code here:
        // Assuming this method is called when the "Previous" button is clicked
    try {
        // Get the current request ID from txt_id
        int currentRequestId = Integer.parseInt(txt_id.getText());

        // Fetch the previous leave request based on the current request ID
        String previousRequestSql = "SELECT request_id, employee_id, firstname, surname, reason, hours, status " +
                                    "FROM Overtime WHERE request_id < ? ORDER BY request_id DESC LIMIT 1";
        pst = conn.prepareStatement(previousRequestSql);
        pst.setInt(1, currentRequestId);
        rs = pst.executeQuery();

        if (rs.next()) {
            // Update GUI with the details of the previous request 
            txt_id.setText(rs.getString("request_id"));
            txt_empid.setText(rs.getString("employee_id"));
            txt_firstname.setText(rs.getString("firstname"));
            txt_surname.setText(rs.getString("surname"));
            txt_reason.setText(rs.getString("reason"));
            txt_start.setText(rs.getString("hours"));
            txt_status.setText(rs.getString("status"));

            lbl_req.setText("LIST REQUEST");

            // Log the action to the Audit table
            logToAuditTable("Viewed Previous Leave Request");
        } else {
            JOptionPane.showMessageDialog(null, "No previous leave request found.");
        }
    } catch (SQLException | NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    }//GEN-LAST:event_lbl_prevActionPerformed

    private void lbl_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_nextActionPerformed
      try {
        // Get the current request ID from txt_id
        int currentRequestId = Integer.parseInt(txt_id.getText());

        // Fetch the next leave request based on the current request ID
        String nextRequestSql = "SELECT request_id, employee_id, firstname, surname, reason, hours, status " +
                                "FROM Overtime WHERE request_id > ? ORDER BY request_id ASC LIMIT 1";
        pst = conn.prepareStatement(nextRequestSql);
        pst.setInt(1, currentRequestId);
        rs = pst.executeQuery();

        if (rs.next()) {
            // Update GUI with the details of the next request 
            txt_id.setText(rs.getString("request_id"));
            txt_empid.setText(rs.getString("employee_id"));
            txt_firstname.setText(rs.getString("firstname"));
            txt_surname.setText(rs.getString("surname"));
            txt_reason.setText(rs.getString("reason"));
            txt_start.setText(rs.getString("hours"));
            txt_status.setText(rs.getString("status"));

            lbl_req.setText("LIST REQUEST");

            // Log the action to the Audit table
            logToAuditTable("Viewed Leave Request");
        } else {
            JOptionPane.showMessageDialog(null, "No next leave request found.");
        }
    } catch (SQLException | NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
        

// Method to log actions to the Audit table
private void logToAuditTable(String action) {
    try {
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Log the action to the Audit table
        String reg = "INSERT INTO Audit (emp_id, date, status) VALUES (?, ?, ?)";
        pst = conn.prepareStatement(reg);
        pst.setString(1, txt_id.getText());
        pst.setString(2, currentTime + " / " + currentDate);
        pst.setString(3, action);
        pst.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }



    }//GEN-LAST:event_lbl_nextActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record?", "Delete", JOptionPane.YES_NO_OPTION);
if (p == 0) {
    try {
        // Delete record from LeaveRequests table
        String sql = "DELETE FROM LeaveRequests WHERE request_id = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_id.getText());
        pst.executeUpdate();

        // Delete record from Users table
        String sq = "DELETE FROM Users WHERE emp_id = ?";
        pst = conn.prepareStatement(sq);
        pst.setString(1, txt_id.getText());
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Record Deleted");

        // Logging to Audit table
        String value0 = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String values = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String val = txt_empid.getText(); // Assuming txt_empid holds the ID of the employee who initiated the action
        String reg = "INSERT INTO Audit (emp_id, date, status) VALUES (?, ?, ?)";
        pst = conn.prepareStatement(reg);
        pst.setString(1, val); // Log the ID of the employee who initiated the action
        pst.setString(2, value0 + " / " + values);
        pst.setString(3, "Record Deleted");
        pst.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}




    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_statusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Overtime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Overtime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Overtime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Overtime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Overtime().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Reason;
    private javax.swing.JLabel Status;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lbl_accept;
    private javax.swing.JButton lbl_decline;
    private javax.swing.JButton lbl_next;
    private javax.swing.JButton lbl_prev;
    private javax.swing.JLabel lbl_req;
    private javax.swing.JLabel txt_emp;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextArea txt_reason;
    private javax.swing.JTextField txt_start;
    private javax.swing.JTextField txt_status;
    private javax.swing.JTextField txt_surname;
    // End of variables declaration//GEN-END:variables

}
