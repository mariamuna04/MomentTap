// Created by Maria Muna on Nov 16 2022 13:06

// Necessary Modules For Client Application
module com.application.client {
    requires javafx.controls;
//    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

    opens com.application.client to javafx.fxml;
    exports com.application.client;
    exports com.application.utility;
    opens com.application.utility to javafx.fxml;
    exports com.application.controllers;
    opens com.application.controllers to javafx.fxml;
    exports com.application.serialShared;
    opens com.application.serialShared to javafx.fxml;
}