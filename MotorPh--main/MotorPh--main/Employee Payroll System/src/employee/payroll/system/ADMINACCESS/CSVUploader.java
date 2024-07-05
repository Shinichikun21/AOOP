package employee.payroll.system.ADMINACCESS;

import employee.payroll.system.MainMenu;
import employee.payroll.system.LOGINCONNECTION.db;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CSVUploader {
    

	public static void createAndShowGUI(MainMenu mainMenu) { // Pass the MainMenu instance
	    JFrame frame = new JFrame("CSV Uploader");
	    // Use WindowConstants.DISPOSE_ON_CLOSE to close only the CSVUploader
	    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
	    frame.setSize(400, 200);

	    JPanel panel = new JPanel();
	    frame.add(panel);
	    placeComponents(panel, frame);

	    frame.setVisible(true);
	}

	    private static WindowListener CustomWindowClosing(MainMenu mainMenu) {
	        return new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                ((JFrame) e.getSource()).dispose(); // Dispose only the CSVUploader frame
	            }
	        };
	    }

	    


    private static void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("File Path:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField filePathText = new JTextField(20);
        filePathText.setBounds(100, 20, 165, 25);
        panel.add(filePathText);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(10, 80, 150, 25);
        panel.add(uploadButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 80, 150, 25);
        panel.add(cancelButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 110, 300, 25);
        panel.add(statusLabel);

        uploadButton.addActionListener(e -> {
            String filePath = filePathText.getText();
            if (filePath.isEmpty()) {
                statusLabel.setText("Error: No file selected.");
                return;
            }

            try {
                CSVReader.uploadEmployeesFromCSV(filePath);
                statusLabel.setText("Upload successful!");
            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        cancelButton.addActionListener(e -> {
            frame.dispose();
            MainMenu j = new MainMenu();
            j.setVisible(true);
        });
    }

    private static class CSVReader {
        public static void uploadEmployeesFromCSV(String fileName) throws Exception {
            if (fileName.isEmpty()) {
                throw new IllegalArgumentException("File path cannot be blank.");
            }

            try (Connection conn = db.java_db();
                 BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                if (conn == null) {
                    throw new SQLException("Failed to establish connection to the database.");
                }

                String line = br.readLine(); // Skip header line
                if (line == null) {
                    throw new IllegalArgumentException("File is empty.");
                }

                while ((line = br.readLine()) != null) {
                    try {
                        String[] values = line.split("\t");
                        ParseEmployee employee = parseEmployee(values);
                        insertEmployee(conn, employee);
                    } catch (Exception e) {
                        System.err.println("Error processing line: " + line);
                        e.printStackTrace();
                    }
                }
                System.out.println("Upload completed successfully.");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                throw new Exception("Error reading or processing the file.");
            }
        }

        private static ParseEmployee parseEmployee(String[] values) {
            if (values.length != 19) {
                throw new IllegalArgumentException("Invalid number of fields in CSV line");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy");

            try {
                int employeeNumber = Integer.parseInt(values[0]);
                String lastName = values[1];
                String firstName = values[2];
                LocalDate birthday = LocalDate.parse(values[3], formatter);
                String address = values[4];
                String phoneNumber = values[5];
                String sssNumber = values[6];
                String philhealthNumber = values[7];
                String tinNumber = values[8];
                String pagIbigNumber = values[9];
                String status = values[10];
                String position = values[11];
                String immediateSupervisor = values[12];
                double basicSalary = Double.parseDouble(values[13]);
                double riceSubsidy = Double.parseDouble(values[14]);
                double phoneAllowance = Double.parseDouble(values[15]);
                double clothingAllowance = Double.parseDouble(values[16]);
                double grossSemiMonthlyRate = Double.parseDouble(values[17]);
                double hourlyRate = Double.parseDouble(values[18]);

                return new ParseEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
            } catch (NumberFormatException | DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid data format in CSV line", e);
            }
        }

        private static void insertEmployee(Connection conn, ParseEmployee employee) throws SQLException {
            String sql = "INSERT INTO tblempdata (empid, lname, fname, address, bday, phnum, sss, philhealth, tin, pagibig, status, position, manager, basic, rice, phallow, clothing, semi, hourly) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, employee.getEmployeeNumber());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3, employee.getFirstName());
                stmt.setString(4, employee.getAddress());
                stmt.setDate(5, employee.getSqlBirthday());
                stmt.setString(6, employee.getPhoneNumber());
                stmt.setString(7, employee.getSssNumber());
                stmt.setString(8, employee.getPhilhealthNumber());
                stmt.setString(9, employee.getTinNumber());
                stmt.setString(10, employee.getPagIbigNumber());
                stmt.setString(11, employee.getStatus());
                stmt.setString(12, employee.getPosition());
                stmt.setString(13, employee.getImmediateSupervisor());
                stmt.setDouble(14, employee.getBasicSalary());
                stmt.setDouble(15, employee.getRiceSubsidy());
                stmt.setDouble(16, employee.getPhoneAllowance());
                stmt.setDouble(17, employee.getClothingAllowance());
                stmt.setDouble(18, employee.getGrossSemiMonthlyRate());
                stmt.setDouble(19, employee.getHourlyRate());

                stmt.executeUpdate();
            }
        }
    }

    private static class ParseEmployee {
        private final int employeeNumber;
        private final String lastName;
        private final String firstName;
        private final LocalDate birthday;
        private final String address;
        private final String phoneNumber;
        private final String sssNumber;
        private final String philhealthNumber;
        private final String tinNumber;
        private final String pagIbigNumber;
        private final String status;
        private final String position;
        private final String immediateSupervisor;
        private final double basicSalary;
        private final double riceSubsidy;
        private final double phoneAllowance;
        private final double clothingAllowance;
        private final double grossSemiMonthlyRate;
        private final double hourlyRate;

        public ParseEmployee(int employeeNumber, String lastName, String firstName, LocalDate birthday, String address, String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber, String pagIbigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
            this.employeeNumber = employeeNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.birthday = birthday;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.sssNumber = sssNumber;
            this.philhealthNumber = philhealthNumber;
            this.tinNumber = tinNumber;
            this.pagIbigNumber = pagIbigNumber;
            this.status = status;
            this.position = position;
            this.immediateSupervisor = immediateSupervisor;
            this.basicSalary = basicSalary;
            this.riceSubsidy = riceSubsidy;
            this.phoneAllowance = phoneAllowance;
            this.clothingAllowance = clothingAllowance;
            this.grossSemiMonthlyRate = grossSemiMonthlyRate;
            this.hourlyRate = hourlyRate;
        }

        public int getEmployeeNumber() {
            return employeeNumber;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public java.sql.Date getSqlBirthday() {
            return java.sql.Date.valueOf(birthday);
        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getSssNumber() {
            return sssNumber;
        }

        public String getPhilhealthNumber() {
            return philhealthNumber;
        }

        public String getTinNumber() {
            return tinNumber;
        }

        public String getPagIbigNumber() {
            return pagIbigNumber;
        }

        public String getStatus() {
            return status;
        }

        public String getPosition() {
            return position;
        }

        public String getImmediateSupervisor() {
            return immediateSupervisor;
        }

        public double getBasicSalary() {
            return basicSalary;
        }

        public double getRiceSubsidy() {
            return riceSubsidy;
        }

        public double getPhoneAllowance() {
            return phoneAllowance;
        }

        public double getClothingAllowance() {
            return clothingAllowance;
        }

        public double getGrossSemiMonthlyRate() {
            return grossSemiMonthlyRate;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }
    }
}
