package ControllerPkg;

import DAO.ClientDAO;
import Entity.ClientsEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateClientController implements Initializable{

    static ClientsEntity ce;
    public static void setClientsId(int clientsId) {
       ce = new ClientDAO(Session.getSession()).getClient(clientsId);
    }

    @FXML
    private TextField clientName;
    @FXML
    private TextField clientPhone;
    @FXML
    private Button changeClient;

    @FXML
    public void changeClient(javafx.event.ActionEvent actionEvent) {
        ce.setClientName(clientName.getText());
        ce.setClientPhone(clientPhone.getText());
        new ClientDAO(Session.getSession()).updateClient(ce);
        Stage stage = (Stage) changeClient.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clientName.setText(ce.getClientName());
        clientPhone.setText(ce.getClientPhone());
    }
}
