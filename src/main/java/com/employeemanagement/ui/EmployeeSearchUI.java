package com.employeemanagement.ui;

import com.employeemanagement.controllers.EmployeeSearchController;
import com.employeemanagement.models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchUI extends JFrame {
    private EmployeeSearchController employeeSearchController;

    private JTable employeeTable;
    private JButton deleteButton;
    private JButton exportButton;
    private JButton backButton;

    public EmployeeSearchUI(EmployeeSearchController employeeSearchController) {
        this.employeeSearchController = employeeSearchController;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Employee Search");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        deleteButton = new JButton("Delete");
        exportButton = new JButton("Export to CSV");
        backButton = new JButton("Back");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                    employeeSearchController.deleteEmployee(employeeId);
                    refreshTable();
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(EmployeeSearchUI.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        employeeSearchController.exportToCSV(getEmployeeData(), filePath);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.SOUTH);
        panel.add(exportButton, BorderLayout.EAST);
        panel.add(backButton, BorderLayout.WEST);

        add(panel);

        refreshTable();
    }

    public void refreshTable() {
        List<Employee> employees = employeeSearchController.getAllEmployees();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Date of Birth");
        model.addColumn("Date of Joining");
        model.addColumn("Grade");

        for (Employee employee : employees) {
            model.addRow(new Object[]{
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDateOfBirth(),
                    employee.getDateOfJoining(),
                    employee.getGrade()
            });
        }

        employeeTable.setModel(model);
    }

    private List<Employee> getEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        int rowCount = model.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            int employeeId = (int) model.getValueAt(rowIndex, 0);
            String firstName = (String) model.getValueAt(rowIndex, 1);
            String lastName = (String) model.getValueAt(rowIndex, 2);
            String dob = (String) model.getValueAt(rowIndex, 3);
            String doj = (String) model.getValueAt(rowIndex, 4);
            String grade = (String) model.getValueAt(rowIndex, 5);

            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDateOfBirth(new SimpleDateFormat("YYYY-MM-DD").parse(dob));
            employee.setDateOfJoining(new SimpleDateFormat("YYYY-MM-DD").parse(doj));
            employee.setGrade(grade);

            employees.add(employee);
        }

        return employees;
    }


}
