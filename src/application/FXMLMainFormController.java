package application;

import functions.FunctionPoint;
import functions.FunctionPointT;
import functions.InappropriateFunctionPointException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import java.util.ArrayList;


public class FXMLMainFormController {
    @FXML
    private Label edX;

    @FXML
    private Label edY;

    @FXML
    private TextField textPointX;

    @FXML
    private TextField textPointY;

    @FXML
    private TableView<FunctionPointT> table;

    @FXML
    private TableColumn<FunctionPointT, Double> xColumn;

    @FXML
    private TableColumn<FunctionPointT, Double> yColumn;

    @FXML
    private Label labelIndexSelectLine;

    @FXML
    private void initialize(){
        xColumn.setCellValueFactory(new PropertyValueFactory<FunctionPointT, Double>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<FunctionPointT, Double>("y"));
        redraw();
    }

    @FXML
    public void redraw(){
        ObservableList<FunctionPointT> data = table.getItems();
        data.clear();
        for(int i = 0; i < Main.tabFDoc.getPointsCount(); ++i) {
            data.add(new FunctionPointT(Main.tabFDoc.getPointX(i), Main.tabFDoc.getPointY(i)));
        }
        table.setItems(data);
        labelIndexSelectLine.setText("Number of points: " + Integer.toString(Main.tabFDoc.getPointsCount()));

    }

    @FXML
    private void btAddPointNewClick(ActionEvent av){
        try {
            Main.tabFDoc.addPoint(new FunctionPoint(Double.parseDouble(textPointX.getText()), Double.parseDouble(textPointY.getText())));
        } catch (InappropriateFunctionPointException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btDeleteNewClick(ActionEvent av){
        int selectedID = table.getSelectionModel().getSelectedIndex();
        try {
            Main.tabFDoc.deletePoint(selectedID);
        } catch (InappropriateFunctionPointException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tableMouseReleased(){
        labelIndexSelectLine.setText("Point " + Integer.toString(table.getSelectionModel().getSelectedIndex() + 1) + " of " + Integer.toString(Main.tabFDoc.getPointsCount()));
    }

    @FXML
    private void tableKeyReleased(){
        labelIndexSelectLine.setText("Point " + Integer.toString(table.getSelectionModel().getSelectedIndex() + 1) + " of " + Integer.toString(Main.tabFDoc.getPointsCount()));
    }

}
