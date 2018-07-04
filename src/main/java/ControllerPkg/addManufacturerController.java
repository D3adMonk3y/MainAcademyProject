package ControllerPkg;

import DAO.ManufacturerDAO;
import Entity.ManufacturerEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class addManufacturerController {

        @FXML
        private TextField nameOfNewManufacturer;
        @FXML
        private Button addManufacturer;

        @FXML
        public void addManufacturer(javafx.event.ActionEvent actionEvent) {

            String nameOfNewManufacturerString = nameOfNewManufacturer.getText();
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO(Session.getSession());
            List<String> listOfManufacturers = new ArrayList<>();
            for(ManufacturerEntity m : manufacturerDAO.getAllManufacturers()){
                listOfManufacturers.add(m.getManufacturer());
            }
            if(nameOfNewManufacturerString.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Помилка");
                alert.setContentText("Новий виробник не введений!");
                alert.showAndWait();
            }else if(listOfManufacturers.contains(nameOfNewManufacturerString)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Помилка");
                alert.setContentText("Такий виробник вже інснує!");
                alert.showAndWait();
            }else{
                ManufacturerEntity manufacturer = new ManufacturerEntity();
                manufacturer.setManufacturer(nameOfNewManufacturerString);
                manufacturerDAO.addManufacturer(manufacturer);
                Stage stage =(Stage)addManufacturer.getScene().getWindow();
                stage.close();

            }}

    }

