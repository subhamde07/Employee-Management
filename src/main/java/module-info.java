module org.example.employeemanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.employeemanagement to javafx.fxml;
    exports org.example.employeemanagement;
}
