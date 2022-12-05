// Created by Maria Muna on Nov 16 2022 13:06

// Necessary Modules For Client Application
module com.application.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.application.client to javafx.fxml;
    exports com.application.client;
    exports com.application.utils;
    opens com.application.utils to javafx.fxml;
}