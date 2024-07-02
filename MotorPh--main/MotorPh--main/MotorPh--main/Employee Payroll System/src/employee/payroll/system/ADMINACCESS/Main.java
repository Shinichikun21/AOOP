package employee.payroll.system.ADMINACCESS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Data Upload");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Upload CSV File:");
        frame.add(label);

        JButton button = new JButton("Select File");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String csvFile = selectedFile.getPath();
                    CSVReader.uploadEmployeesFromCSV(csvFile);
                    JOptionPane.showMessageDialog(null, "CSV file uploaded successfully!", "Upload Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        frame.add(button);

        frame.setVisible(true);
    }
}
