/**
 Opens Tuition Manager GUI
 @author Victoria Chen, Bridget Zhang
 */
module com.example.tuitionmanagergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tuitionmanagergui to javafx.fxml;
    exports com.example.tuitionmanagergui;
}