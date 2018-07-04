package ControllerPkg;

import DAO.OrdersDAO;
import Entity.OrdersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchController {
    private List<OrdersEntity> ordersList = new OrdersDAO(Session.getSession()).getAllOrders();

    @FXML
    private TextField orderNumberText;
    @FXML
    private Button findOrderBtn;

    @FXML
    public void findOrder(javafx.event.ActionEvent actionEvent) {
        int orderNumber = Integer.parseInt(orderNumberText.getText());

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < ordersList.size(); i++) {
            numbers.add(ordersList.get(i).getIdOrders());
        }
if(numbers.contains(orderNumber)) {

    OrdersEntity oe = new OrdersDAO(Session.getSession()).getOrders(orderNumber);

    try {

        ControllerPkg.updateController.initOrderId(oe.getIdOrders());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/update.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Пошук");
        stage.setScene(new Scene(root));
        stage.show();
Stage primaryStage = (Stage)findOrderBtn.getScene().getWindow();
primaryStage.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не знайдено такого запису!");
            alert.showAndWait();
        }
    }
}
