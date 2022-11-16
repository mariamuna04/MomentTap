module com.application.clirnt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.client to javafx.fxml;
    exports com.application.client;
}