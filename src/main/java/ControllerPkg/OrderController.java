package ControllerPkg;

import DAO.*;
import Entity.OrdersEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable{

    private Session ses = ControllerPkg.Session.getSession();
    private static List<OrdersEntity> ordersList = new ArrayList<>();
    private static Boolean clnt;

    public static void setOrdersNumbersList(List<OrdersEntity> ordersNumbersList, Boolean CE) {
        OrderController.ordersList = ordersNumbersList;
        clnt = CE;
    }

    @FXML
    private TableView<TableData> ordersTable;
    @FXML
    private TableColumn<TableData, Integer> idCol;
    @FXML
    private TableColumn<TableData, String> clientCol;
    @FXML
    private TableColumn<TableData, String> typeCol;
    @FXML
    private TableColumn<TableData, String> modelCol;
    @FXML
    private TableColumn<TableData, String> dateCol;
    @FXML
    private TableColumn<TableData, String> employeeCol;
    @FXML
    private TableColumn<TableData, Integer> urgentCol;
    @FXML
    private TableColumn<TableData, String> statusCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusCol.setCellValueFactory(new PropertyValueFactory<TableData,String>("status"));
        idCol.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("id"));
        clientCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("client"));
        typeCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("type"));
        modelCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("model"));
        dateCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("date"));
        employeeCol.setCellValueFactory(new PropertyValueFactory<TableData,String>("employee"));
        urgentCol.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("urgent"));

       initTable();

        ordersTable.setRowFactory(tv -> {
            TableRow<TableData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    TableData clickedRow = row.getItem();
                    try {
                        //передача в апдейт контролер номеру замовлення
                        ControllerPkg.updateController.initOrderId(clickedRow.getId());

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/update.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        ControllerPkg.updateController.initOrderId(clickedRow.getId());
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Update order");
                        stage.setScene(new Scene(root1));
                        stage.show();
                        stage.setOnHiding((WindowEvent we) -> {
                            List<OrdersEntity> temp = new OrdersDAO(ControllerPkg.Session.getSession()).getAllOrders();

                            if(clnt){
                                int clientid = ordersList.get(0).getClient();
                                ordersList.removeAll(ordersList);
                                for (int i = 0; i < temp.size(); i++) {
                                    if(temp.get(i).getClient() == clientid)
                                        ordersList.add(temp.get(i));
                                }}else{
                                int employeeid = ordersList.get(1).getEmployee();
                                ordersList.removeAll(ordersList);
                                for (int i = 0; i < temp.size(); i++) {
                                    if(temp.get(i).getEmployee() == employeeid)
                                        ordersList.add(temp.get(i));
                                }}

                            initTable();
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    private void initTable(){
        List<TableData> DataList = new ArrayList<>();

        for (int i = 0; i < ordersList.size(); i++) {
            OrdersEntity oe = ordersList.get(i);

            SimpleIntegerProperty id = new SimpleIntegerProperty();
            id.set(oe.getIdOrders());

            SimpleStringProperty client = new SimpleStringProperty();
            client.set(new ClientDAO(ses).getClient(oe.getClient()).getClientName());

            SimpleStringProperty tot = new SimpleStringProperty();
            tot.set(new TOTDAO(ses).getTOT(oe.getType()).getType());

            SimpleStringProperty model = new SimpleStringProperty();
            model.set(new ManufacturerDAO(ses).getManufacturer(oe.getManufacturer()).getManufacturer() + " " +
                    oe.getModel());

            SimpleStringProperty employee = new SimpleStringProperty();
            employee.set(new EmployeeDAO(ses).getEmployee(oe.getEmployee()).getEmployeName());

            SimpleStringProperty date = new SimpleStringProperty();
            date.set(oe.getAddDate().toLocalDate().toString());

            SimpleIntegerProperty urgent = new SimpleIntegerProperty();
            urgent.set(oe.getUrgent());

            SimpleStringProperty status = new SimpleStringProperty();
            status.set(new StatusDAO(ses).getStatus(oe.getStatus()).getStatus());

            TableData tableData = new TableData(id, client, tot, model, employee, date, urgent, status);

            DataList.add(tableData);
        }
        ObservableList<TableData> ordersData = FXCollections.observableArrayList(DataList);


        ordersTable.setItems(ordersData);
    }
}
