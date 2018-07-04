package ControllerPkg;

import DAO.ClientDAO;

import DAO.OrdersDAO;
import Entity.ClientsEntity;

import Entity.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable{

    @FXML
    private TableView<ClientsEntity> clientsTable;
    @FXML
    private TableColumn<ClientsEntity, String> clientNameCol;
    @FXML
    private TableColumn<ClientsEntity, String> clientPhoneCol;

    @FXML
    public void removeClient(javafx.event.ActionEvent actionEvent) {
        ClientsEntity selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        if(selectedClient != null){
            new ClientDAO(Session.getSession()).deleteClient(selectedClient);
            clients = new ClientDAO(Session.getSession()).getAllClients();
            ObservableList<ClientsEntity> clientsData = FXCollections.observableArrayList(clients);
            clientsTable.setItems(clientsData);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не вибраний клієнт для видалення!");
            alert.showAndWait();
        }
    }

    @FXML
    public void addClient(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addClient.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Новий клієнт");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                clients = new ClientDAO(Session.getSession()).getAllClients();
                ObservableList<ClientsEntity> clientsData = FXCollections.observableArrayList(clients);
                clientsTable.setItems(clientsData);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showOrders(javafx.event.ActionEvent actionEvent) {
        ClientsEntity selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {

            List<OrdersEntity> ordersList = new OrdersDAO(Session.getSession()).getAllOrders();
            List<OrdersEntity> clientOrdersList = new ArrayList<>();
            for (int i = 0; i < ordersList.size(); i++) {
                if (ordersList.get(i).getClient() == selectedClient.getIdclients()) {
                    clientOrdersList.add(ordersList.get(i));
                }
            }

            ControllerPkg.OrderController.setOrdersNumbersList(clientOrdersList, true);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/orders.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Замовлення клієнта");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не вибраний клієнт для видалення!");
            alert.showAndWait();
        }

    }

    List<ClientsEntity> clients = new ClientDAO(Session.getSession()).getAllClients();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientNameCol.setCellValueFactory(new PropertyValueFactory<ClientsEntity, String>("clientName"));
        clientPhoneCol.setCellValueFactory(new PropertyValueFactory<ClientsEntity, String>("clientPhone"));

        ObservableList<ClientsEntity> clientsData = FXCollections.observableArrayList(clients);
        clientsTable.setItems(clientsData);

        clientsTable.setRowFactory(tv -> {
            TableRow<ClientsEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    ClientsEntity clickedRow = row.getItem();
                    try {
                        //передача в апдейт контролер номеру замовлення
                        ControllerPkg.UpdateClientController.setClientsId(clickedRow.getIdclients());

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/updateClient.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Update client");
                        stage.setScene(new Scene(root1));
                        stage.show();
                        stage.setOnHiding((WindowEvent we) -> {
                            clients = new ClientDAO(Session.getSession()).getAllClients();
                            ObservableList<ClientsEntity> clientData = FXCollections.observableArrayList(clients);
                            clientsTable.setItems(clientData);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }
  }
