package ControllerPkg;

import DAO.ClientDAO;
import Entity.ClientsEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class addClientController {

        @FXML
        private TextField nameOfNewClient;
        @FXML
        private TextField phoneNumber;
        @FXML
        private Button addClient;

        @FXML
        public void addClient(javafx.event.ActionEvent actionEvent) {

            String nameOfNewClientString = nameOfNewClient.getText();
            String phoneNumberStr = phoneNumber.getText();
            ClientDAO clientDAO = new ClientDAO(Session.getSession());
            List<String> listOfClientsNumber = new ArrayList<>();
            for(ClientsEntity m : clientDAO.getAllClients()){
                listOfClientsNumber.add(m.getClientPhone());
            }
            if(nameOfNewClientString.equals("") || phoneNumberStr.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Помилка");
                alert.setContentText("Не всі дані про клієнта введенні!");
                alert.showAndWait();
            }else if(listOfClientsNumber.contains(phoneNumberStr)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Помилка");
                alert.setContentText("Такий номер телефону вже інснує!");
                alert.showAndWait();
            }else{
                ClientsEntity client = new ClientsEntity();
                client.setClientName(nameOfNewClientString);
                client.setClientPhone(phoneNumberStr);
                clientDAO.addClient(client);
                Stage stage =(Stage)addClient.getScene().getWindow();
                stage.close();

            }
        }
}



