module com.example.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqljdbc4;


    opens com.example.smarthome to javafx.fxml;
    exports com.example.smarthome;
    exports com.example.smarthome.Pmart;
    opens com.example.smarthome.Pmart to javafx.fxml;
    exports com.example.smarthome.Controllers;
    opens com.example.smarthome.Controllers to javafx.fxml;
    exports com.example.smarthome.DataBase;
    opens com.example.smarthome.DataBase to javafx.fxml;
    exports com.example.smarthome.Entity;
    opens com.example.smarthome.Entity to javafx.fxml;
    exports com.example.smarthome.InProccess;
    opens com.example.smarthome.InProccess to javafx.fxml;
}