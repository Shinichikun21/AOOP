package employee.payroll.system.ADMINACCESS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CSVReader {
    private static final String JDBC_URL = "jdbc:sqlite:employee.db"; // SQLite JDBC URL

    public static void uploadEmployeesFromCSV(String fileName) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // Skip header line
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
