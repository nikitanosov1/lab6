package main;

import application.FXMLMainFormController;
import application.TabulatedFunctionDoc;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;


public class Main extends Application {
    public static TabulatedFunctionDoc tabFDoc;

    public static void main(String[] args){
        launch(args);

        /*ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(0,10,10);
        for (int i = 0; i < arrayTabulatedFunction.getPointsCount(); i++) {
            arrayTabulatedFunction.setPointY(i, Math.log(arrayTabulatedFunction.getPointX(i)));
        }

        TabulatedFunctionDoc a = new TabulatedFunctionDoc();
        a.newFunction(0, 10, 5);
        a.saveFunctionAs("2.json");
        a.loadFunction("2.json");*/
    }

    @Override
    public void init() throws Exception {
        tabFDoc = new TabulatedFunctionDoc();
        tabFDoc.newFunction(0 , 10 ,15);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainForm.fxml"));
        Parent root = loader.load();
        FXMLMainFormController ctrl = loader.getController();
        ctrl.setStage(stage);
        tabFDoc.registerRedrawFunctionController(ctrl);
        Scene scene = new Scene(root);
        stage.setTitle("тим спик");
        stage.setScene(scene);
        stage.show();
    }
}