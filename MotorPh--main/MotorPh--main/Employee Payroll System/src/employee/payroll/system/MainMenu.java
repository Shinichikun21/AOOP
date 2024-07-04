/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.payroll.system;

import employee.payroll.system.ADMINACCESS.*;
import employee.payroll.system.EMPLOYEACCESS.Deductions;
import employee.payroll.system.EMPLOYEACCESS.Overtim1;
import employee.payroll.system.EMPLOYEACCESS.ViewLeave;
import employee.payroll.system.EMPLOYEACCESS.empReport;
import employee.payroll.system.LOGINCONNECTION.Emp;
import employee.payroll.system.LOGINCONNECTION.db;
import employee.payroll.system.LOGINCONNECTION.login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class MainMenu extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        conn = db.java_db();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        txt_emp.setText(String.valueOf(Emp.empId));
    }

    // Auto-generated code for initializing components
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton7.setEnabled(false);
        jButton3 = new javax.swing.JButton();
        jButton3.setEnabled(false);
        jButton2 = new javax.swing.JButton();
        jButton2.setEnabled(false);
        add_employee = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton4.setEnabled(false);
        jButton5 = new javax.swing.JButton();
        jButton5.setEnabled(false);
        jLabel1 = new javax.swing.JLabel();
        txt_emp = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        btn_menu = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new Color(0, 0, 0));
        jPanel2.setLayout(null);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/employee/payroll/system/Images/logout.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblWhatWouldYou = new JLabel("What would you like to do?");
        lblWhatWouldYou.setToolTipText("Welcome!");
        lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
        lblWhatWouldYou.setForeground(Color.BLACK);
        lblWhatWouldYou.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblWhatWouldYou.setBounds(166, 10, 235, 30);
        jPanel2.add(lblWhatWouldYou);

        JLabel lblNewLabel = new JLabel("Welcome back!");
        lblNewLabel.setToolTipText("Welcome!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(0, 128, 128));
        lblNewLabel.setBounds(32, 10, 138, 30);
        jPanel2.add(lblNewLabel);
        jPanel2.add(jButton6);
        jButton6.setBounds(950, 10, 40, 30);

        jButton7.setFont(new Font("Arial", Font.BOLD, 13)); // NOI18N
        jButton7.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-salary-50.png"))); // NOI18N
        jButton7.setText("Salary");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(730, 471, 197, 57);

        jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton3.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-minus-30.png"))); // NOI18N
        jButton3.setText("Deductions");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(730, 304, 197, 57);

        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-loan-50.png"))); // NOI18N
        jButton2.setText("Allowance");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(730, 223, 197, 57);

        add_employee.setFont(new Font("Arial", Font.BOLD, 10)); // NOI18N
        add_employee.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-employee-preferences-50.png"))); // NOI18N
        add_employee.setText("Employee Manager");
        add_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_employeeActionPerformed(evt);
            }
        });
        jPanel2.add(add_employee);
        add_employee.setBounds(730, 60, 197, 57);

        jButton4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton4.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-overtime-50.png"))); // NOI18N
        jButton4.setText("Overtime");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(730, 141, 197, 57);

        jButton5.setFont(new Font("Arial", Font.BOLD, 10)); // NOI18N
        jButton5.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-view-50.png"))); // NOI18N
        jButton5.setText("View Leave Request");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(730, 388, 197, 57);

        jLabel1.setText("Logged in As :");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 590, 90, 16);

        txt_emp.setText("Employee ID");
        jPanel2.add(txt_emp);
        txt_emp.setBounds(90, 590, 80, 16);

        jLabel3.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/JPEG Image.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 1000, 630);

        jTextField1.setText("jTextField1");

        jMenu1.setText("Logout");

        jMenuItem1.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/Exit.png"))); // NOI18N
        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        btn_menu.setText("Menu");

        jMenuItem13.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/icons8-employee-preferences-50.png"))); // NOI18N
        jMenuItem13.setText("Employee Report");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        btn_menu.add(jMenuItem13);
        jMenuItem5.setText("changeLog");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Add Multiple Employees");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
                  CSVUploader.createAndShowGUI();
              }
          });
        
        mntmNewMenuItem.setIcon(new ImageIcon(MainMenu.class.getResource("/employee/payroll/system/Images/Add_Employee.png")));
        btn_menu.add(mntmNewMenuItem);
        btn_menu.add(jMenuItem5);

        jMenuBar1.add(btn_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );

        pack();
    }

    /* private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    } */

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Overtim1 j = new Overtim1();
        j.setVisible(true);
        this.dispose();
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        login x = new login();
        x.setVisible(true);
        this.dispose();
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        changeLog y = new changeLog();
        y.setVisible(true);
    }

    private void add_employeeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        addEmployee add = new addEmployee();
        add.setVisible(true);
        this.dispose();
    }

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        empReport em = new empReport();
        em.setVisible(true);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Deductions d = new Deductions();
        d.setVisible(true);
        this.dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Allowance a = new Allowance();
        a.setVisible(true);
        this.dispose();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ViewLeave l = new ViewLeave();
        l.setVisible(true);
        this.dispose();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        login x = new login();
        x.setVisible(true);
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton add_employee;
    private javax.swing.JMenu btn_menu;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel txt_emp;
    private JLabel lblWhatWouldYou;
}
