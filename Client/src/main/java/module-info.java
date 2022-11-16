module com.application.clirnt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.clirnt to javafx.fxml;
    exports com.application.clirnt;
}