package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelpForm {
    private Stage stage;

    public HelpFormController.STATUS showDialog(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("helpForm.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HelpFormController ctrl = loader.getController();
        ctrl.setStage(stage);

        this.stage = stage;
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Function parameters");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);

        stage.showAndWait();
        return ctrl.getStatus();
    }


    public void closeWindow(){
        stage.close();
    }
}
