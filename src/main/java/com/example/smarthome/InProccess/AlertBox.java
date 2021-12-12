package com.example.smarthome.InProccess;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public  static void  Display(String Tittle,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Tittle);

        Label label = new Label(message);
        Button button = new Button(" Продовжити ");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(4);
        layout.getChildren().addAll(label,button);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
