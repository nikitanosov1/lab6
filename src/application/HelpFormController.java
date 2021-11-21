package application;

import functions.ArrayTabulatedFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;

public class HelpFormController {
    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    @FXML
    private double leftBorder;

    @FXML
    private double rightBorder;

    @FXML
    private int pointsCount;

    @FXML
    private TextField textLeftDomainBorder;

    @FXML
    private TextField textRightDomainBorder;

    @FXML
    private Spinner<Integer> spinner;


    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);

    @FXML
    private Button btOK;

    @FXML
    private Button btCancel;

    public void initialize(){
        spinner.setValueFactory(svf);
    }

    public STATUS getStatus(){
        return status;
    }

    private STATUS status;
    enum STATUS{OK, CANCEL};

    @FXML
    private void btOKNewClick(ActionEvent av){
        try {
            leftBorder = Double.parseDouble(textLeftDomainBorder.getText());
            rightBorder = Double.parseDouble(textRightDomainBorder.getText());
            pointsCount = Integer.parseInt(spinner.getEditor().getText());
            Main.tabFDoc.newFunction(leftBorder, rightBorder, pointsCount);
            stage.hide();
            status = STATUS.OK;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void btCancelNewClick(ActionEvent av){
        stage.hide();
        status = STATUS.CANCEL;
    }
}
