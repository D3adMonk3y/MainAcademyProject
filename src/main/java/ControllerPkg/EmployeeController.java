package ControllerPkg;

import DAO.ClientDAO;
import DAO.EmployeeDAO;
import DAO.OrdersDAO;
import Entity.ClientsEntity;
import Entity.EmployesEntity;
import Entity.OrdersEntity;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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

public class EmployeeController implements Initializable {

    List<EmployesEntity> employes = new EmployeeDAO(Session.getSession()).getAllEmployes();

    @FXML
    private TableView<EmployesEntity> employeeTable;
    @FXML
    private TableColumn<EmployesEntity, String> employeeNameCol;
    @FXML
    private TableColumn<EmployesEntity, String> employeePhoneCol;
    @FXML
    private TableColumn<EmployesEntity, Double> employeeSalaryCol;
    @FXML
    private TableColumn<EmployesEntity, String> employeeRankCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<EmployesEntity, String>("employeName"));
        employeePhoneCol.setCellValueFactory(new PropertyValueFactory<EmployesEntity, String>("employePhone"));
        employeeSalaryCol.setCellValueFactory(new PropertyValueFactory<EmployesEntity, Double>("salary"));
        employeeRankCol.setCellValueFactory(new PropertyValueFactory<EmployesEntity, String>("Rank"));

         ObservableList<EmployesEntity> employesData = FXCollections.observableArrayList(employes);
         employeeTable.setItems(employesData);

        employeeTable.setRowFactory(tv -> {
            TableRow<EmployesEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    EmployesEntity clickedRow = row.getItem();
                    try {
                        //передача в апдейт контролер номеру замовлення
                        ControllerPkg.UpdateEmployeeController.setEmployesId(clickedRow.getIdEmployes());

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/updateEmployee.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Update employee");
                        stage.setScene(new Scene(root1));
                        stage.show();
                        stage.setOnHiding((WindowEvent we) -> {
                           employes = new EmployeeDAO(Session.getSession()).getAllEmployes();
                            ObservableList<EmployesEntity> employeesData = FXCollections.observableArrayList(employes);
                            employeeTable.setItems(employeesData);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });

}
    @FXML
    public void removeEmployee(javafx.event.ActionEvent actionEvent) {
        EmployesEntity selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if(selectedEmployee != null){

                new EmployeeDAO(Session.getSession()).deleteEmployee(selectedEmployee);

            employes = new EmployeeDAO(Session.getSession()).getAllEmployes();
            ObservableList<EmployesEntity> employesData = FXCollections.observableArrayList(employes);
            employeeTable.setItems(employesData);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не вибраний працівник для видалення!");
            alert.showAndWait();
        }
    }

    @FXML
    public void addEmployee(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addEmployee.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Новий працівник");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                employes = new EmployeeDAO(Session.getSession()).getAllEmployes();
                ObservableList<EmployesEntity> employesData = FXCollections.observableArrayList(employes);
                employeeTable.setItems(employesData);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showOrders(javafx.event.ActionEvent actionEvent) {
        EmployesEntity selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {

            List<OrdersEntity> ordersList = new OrdersDAO(Session.getSession()).getAllOrders();
            List<OrdersEntity> employeeOrdersList = new ArrayList<>();
            for (int i = 0; i < ordersList.size(); i++) {
                if (ordersList.get(i).getEmployee() == selectedEmployee.getIdEmployes()) {
                    employeeOrdersList.add(ordersList.get(i));
                }
            }

            ControllerPkg.OrderController.setOrdersNumbersList(employeeOrdersList, false);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/orders.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Замовлення закріпленні за працівником");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не вибраний працівник для звіту!");
            alert.showAndWait();
        }

    }
}
