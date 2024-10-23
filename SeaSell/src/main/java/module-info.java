module com.mycompany.seasell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens com.mycompany.seasell to javafx.fxml;
    exports com.mycompany.seasell;
}
