package ControllerPkg;

import DAO.EmployeeDAO;
import DAO.OrdersDAO;
import Entity.EmployesEntity;
import Entity.OrdersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController implements Initializable{

    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<ComboItem> employeeCombo;
    @FXML
    private Button reportBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<EmployesEntity> employesEntities = new EmployeeDAO(Session.getSession()).getAllEmployes();
        for (int i = 0; i < employesEntities.size() ; i++) {
            employeeCombo.getItems().add(new ComboItem(employesEntities.get(i).getIdEmployes(), employesEntities.get(i).getEmployeName()));
        }
        employeeCombo.getItems().add(new ComboItem(-1, "Всі"));
        employeeCombo.getSelectionModel().selectFirst();
    }

    public void report(javafx.event.ActionEvent actionEvent) {
        List<OrdersEntity> ordersList = new OrdersDAO(Session.getSession()).getAllOrders();
        List<OrdersEntity> filteredList = new ArrayList<>();
        Date date = ordersList.get(1).getAddDate();
        for (int i = 0; i < ordersList.size(); i++) {
            if(beginDate.getValue().isBefore(ordersList.get(i).getAddDate().toLocalDate())
                    && endDate.getValue().isAfter(ordersList.get(i).getAddDate().toLocalDate())){
                if(employeeCombo.getSelectionModel().getSelectedItem().getValue() == -1){
                    filteredList.add(ordersList.get(i));
                }else{
                    if(ordersList.get(i).getEmployee() == employeeCombo.getSelectionModel().getSelectedItem().getValue()){
                        filteredList.add(ordersList.get(i));
                    }
                }
            }
        }

        ControllerPkg.ReportOrdersController.setOrdersNumbersList(filteredList, beginDate.getValue(), endDate.getValue(),
                employeeCombo.getSelectionModel().getSelectedItem().getValue());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/reportsOrders.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Замовлення закріпленні за працівником");
            stage.setScene(new Scene(root1));
            Stage stage1 = (Stage)reportBtn.getScene().getWindow();
            stage1.close();
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
