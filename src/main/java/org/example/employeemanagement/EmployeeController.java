package org.example.employeemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField salaryField;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadEmployeeData(); // Load data into the table when the application starts

        // Add double-click event to the table rows
        employeeTable.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Employee rowData = row.getItem();
                    populateFields(rowData);
                }
            });
            return row;
        });
    }

    private void loadEmployeeData() {
        try {
            List<Employee> employees = EmployeeDAO.getAllEmployees();
            employeeTable.getItems().setAll(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddEmployee() {
        try {
            String name = nameField.getText();
            String position = positionField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            Employee employee = new Employee(name, position, salary);
            EmployeeDAO.createEmployee(employee);
            loadEmployeeData();

            // Clear the input fields
            nameField.clear();
            positionField.clear();
            salaryField.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateEmployee() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

            if (selectedEmployee != null) {
                selectedEmployee.setName(nameField.getText());
                selectedEmployee.setPosition(positionField.getText());
                selectedEmployee.setSalary(Integer.parseInt(salaryField.getText()));

                EmployeeDAO.updateEmployee(selectedEmployee);
                loadEmployeeData(); // Refresh the table

                // Clear the input fields
                nameField.clear();
                positionField.clear();
                salaryField.clear();
            } else {
                System.out.println("No employee selected for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

            if (selectedEmployee != null) {
                EmployeeDAO.deleteEmployee(selectedEmployee.getId());
                loadEmployeeData(); // Refresh the table
                // Clear the input fields
                nameField.clear();
                positionField.clear();
                salaryField.clear();
            } else {
                System.out.println("No employee selected for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateFields(Employee employee) {
        nameField.setText(employee.getName());
        positionField.setText(employee.getPosition());
        salaryField.setText(String.valueOf(employee.getSalary()));
    }
}
