package ControllerPkg;

import DAO.TOTDAO;
import Entity.TypeoftecnicsEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class addTOTController {

    @FXML
    private TextField nameOfNewTot;
    @FXML
    private Button addTot;

    @FXML
    public void addTOT(javafx.event.ActionEvent actionEvent) {

        String nameOfNewTotString = nameOfNewTot.getText();
        TOTDAO totdao = new TOTDAO(Session.getSession());
        List<String> listOfTypes = new ArrayList<>();
        for(TypeoftecnicsEntity t : totdao.getAllTOTs()) {
            listOfTypes.add(t.getType());
        }
        if(nameOfNewTotString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Новий тип техніки не введений!");
            alert.showAndWait();
        }else  if(listOfTypes.contains(nameOfNewTotString)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Такий тип техніки вже інснує!");
            alert.showAndWait();
        }else{
             TypeoftecnicsEntity tot = new TypeoftecnicsEntity();
             tot.setType(nameOfNewTotString);
             totdao.addTOT(tot);
             Stage stage =(Stage)addTot.getScene().getWindow();
             stage.close();
}}
}



