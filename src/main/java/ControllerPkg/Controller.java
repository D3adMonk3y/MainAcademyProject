package ControllerPkg;

import ControllerPkg.Session;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.URL;

import java.util.*;


public class Controller implements Initializable{

    private  org.hibernate.Session ses = Session.getSession();
    //create list with all orders
    private List<OrdersEntity> ordersList = new OrdersDAO(Session.getSession()).getAllOrders();
    private List<TableData> DataList = new ArrayList<>();

    private static boolean isDirector = false;

    public static void setIsDirector(boolean isD){
        isDirector = isD;
    }

    @FXML
    private Button employeesbtn;
    @FXML
    private Button reportsbtn;

    //all orders Table
    @FXML
    private TableView<TableData> allTable;
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

    //acepted orders table
    @FXML
    private TableView<TableData> aceptedTable;
    @FXML
    private TableColumn<TableData, Integer> idColA;
    @FXML
    private TableColumn<TableData, String> clientColA;
    @FXML
    private TableColumn<TableData, String> typeColA;
    @FXML
    private TableColumn<TableData, String> modelColA;
    @FXML
    private TableColumn<TableData, String> dateColA;
    @FXML
    private TableColumn<TableData, String> employeeColA;
    @FXML
    private TableColumn<TableData, Integer> urgentColA;

    //orders in work table
    @FXML
    private TableView<TableData> inWorkTable;
    @FXML
    private TableColumn<TableData, Integer> idColWork;
    @FXML
    private TableColumn<TableData, String> clientColWork;
    @FXML
    private TableColumn<TableData, String> typeColWork;
    @FXML
    private TableColumn<TableData, String> modelColWork;
    @FXML
    private TableColumn<TableData, String> dateColWork;
    @FXML
    private TableColumn<TableData, String> employeeColWork;
    @FXML
    private TableColumn<TableData, Integer> urgentColWork;

    //waiting table
    @FXML
    private TableView<TableData> waitingTable;
    @FXML
    private TableColumn<TableData, Integer> idColWait;
    @FXML
    private TableColumn<TableData, String> clientColWait;
    @FXML
    private TableColumn<TableData, String> typeColWait;
    @FXML
    private TableColumn<TableData, String> modelColWait;
    @FXML
    private TableColumn<TableData, String> dateColWait;
    @FXML
    private TableColumn<TableData, String> employeeColWait;
    @FXML
    private TableColumn<TableData, Integer> urgentColWait;

    //waitings for parts table
    @FXML
    private TableView<TableData> waitForPartsTable;
    @FXML
    private TableColumn<TableData, Integer> idColwfp;
    @FXML
    private TableColumn<TableData, String> clientColwfp;
    @FXML
    private TableColumn<TableData, String> typeColwfp;
    @FXML
    private TableColumn<TableData, String> modelColwfp;
    @FXML
    private TableColumn<TableData, String> dateColwfp;
    @FXML
    private TableColumn<TableData, String> employeeColwfp;
    @FXML
    private TableColumn<TableData, Integer> urgentColwfp;

    //waranty table
    @FXML
    private TableView<TableData> warrantyTable;
    @FXML
    private TableColumn<TableData, Integer> idColWarranty;
    @FXML
    private TableColumn<TableData, String> clientColWarranty;
    @FXML
    private TableColumn<TableData, String> typeColWarranty;
    @FXML
    private TableColumn<TableData, String> modelColWarranty;
    @FXML
    private TableColumn<TableData, String> dateColWarranty;
    @FXML
    private TableColumn<TableData, String> employeeColWarranty;
    @FXML
    private TableColumn<TableData, Integer> urgentColWarranty;

    //ready table
    @FXML
    private TableView<TableData> readyTable;
    @FXML
    private TableColumn<TableData, Integer> idColReady;
    @FXML
    private TableColumn<TableData, String> clientColReady;
    @FXML
    private TableColumn<TableData, String> typeColReady;
    @FXML
    private TableColumn<TableData, String> modelColReady;
    @FXML
    private TableColumn<TableData, String> dateColReady;
    @FXML
    private TableColumn<TableData, String> employeeColReady;
    @FXML
    private TableColumn<TableData, Integer> urgentColReady;

    //finished table
    @FXML
    private TableView<TableData> finishedTable;
    @FXML
    private TableColumn<TableData, Integer> idColFin;
    @FXML
    private TableColumn<TableData, String> clientColFin;
    @FXML
    private TableColumn<TableData, String> typeColFin;
    @FXML
    private TableColumn<TableData, String> modelColFin;
    @FXML
    private TableColumn<TableData, String> dateColFin;
    @FXML
    private TableColumn<TableData, String> employeeColFin;
    @FXML
    private TableColumn<TableData, Integer> urgentColFin;

    @FXML
    public void btn(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add work");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                ordersList = new OrdersDAO(ses).getAllOrders();
                initTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void findAction(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/search.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Пошук");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                ordersList = new OrdersDAO(ses).getAllOrders();
                initTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clientsAction(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/clients.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Клієнти");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                ordersList = new OrdersDAO(ses).getAllOrders();
                initTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void employeesAction(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/employees.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Працівники");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                ordersList = new OrdersDAO(ses).getAllOrders();
                initTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void reportsAction(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/reports.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Налаштування");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                ordersList = new OrdersDAO(ses).getAllOrders();
                initTable();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //all
        init(idCol, clientCol, typeCol, modelCol, dateCol, employeeCol, urgentCol);
        //acepted
        init(idColA, clientColA, typeColA, modelColA, dateColA, employeeColA, urgentColA);
        //inwork
        init(idColWork, clientColWork, typeColWork, modelColWork, dateColWork, employeeColWork, urgentColWork);
        //wait
        init(idColWait, clientColWait, typeColWait, modelColWait, dateColWait, employeeColWait, urgentColWait);
        //wait for parts
        init(idColwfp, clientColwfp, typeColwfp, modelColwfp, dateColwfp, employeeColwfp, urgentColwfp);
        //warranty
        init(idColWarranty, clientColWarranty, typeColWarranty, modelColWarranty, dateColWarranty,
                employeeColWarranty, urgentColWarranty);
        //ready
        init(idColReady, clientColReady, typeColReady, modelColReady, dateColReady, employeeColReady, urgentColReady);
        //finished
        init(idColFin, clientColFin, typeColFin, modelColFin, dateColFin, employeeColFin, urgentColFin);

        initTable();

        if(!isDirector){
            employeesbtn.setDisable(true);
            reportsbtn.setDisable(true);
        }

           }



// form different list on dependence of order status
    private void formData(Integer state){

       for (int i = 0; i < ordersList.size(); i++) {
            OrdersEntity oe = ordersList.get(i);

            if(oe.getStatus() != state && state != 0)
                continue;

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
    }

    //create 9 observable list for 9 tabs
    private void initTable(){
        formData(0);
        ObservableList<TableData> allData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(1);
        ObservableList<TableData> acceptedData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(2);
        ObservableList<TableData> workData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(3);
        ObservableList<TableData> waitData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(5);
        ObservableList<TableData> wfpData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(4);
        ObservableList<TableData> warrantyData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(6);
        ObservableList<TableData> readyData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);
        formData(7);
        ObservableList<TableData> finishedData = FXCollections.observableArrayList(DataList);
        DataList.removeAll(DataList);

        //set observable list to table
        allTable.setItems(allData);
        aceptedTable.setItems(acceptedData);
        inWorkTable.setItems(workData);
        waitingTable.setItems(waitData);
        waitForPartsTable.setItems(wfpData);
        warrantyTable.setItems(warrantyData);
        readyTable.setItems(readyData);
        finishedTable.setItems(finishedData);

        seturg(urgentCol);
        seturg(urgentColA);
        seturg(urgentColWork);
        seturg(urgentColWait);
        seturg(urgentColwfp);
        seturg(urgentColReady);
        seturg(urgentColWarranty);
        seturg(urgentColFin);

        // set action listeners for all tables
        setActionlisteners(allTable);
        setActionlisteners(aceptedTable);
        setActionlisteners(inWorkTable);
        setActionlisteners(waitingTable);
        setActionlisteners(waitForPartsTable);
        setActionlisteners(warrantyTable);
        setActionlisteners(readyTable);
        setActionlisteners(finishedTable);
 }

    public static void init(TableColumn id, TableColumn client, TableColumn type,
                            TableColumn model, TableColumn date, TableColumn employee, TableColumn urgent){
        //за//задаєм фабрики для кожної колонки в одній закладці з таблицею
        id.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("id"));
        client.setCellValueFactory(new PropertyValueFactory<TableData, String>("client"));
        type.setCellValueFactory(new PropertyValueFactory<TableData, String>("type"));
        model.setCellValueFactory(new PropertyValueFactory<TableData, String>("model"));
        date.setCellValueFactory(new PropertyValueFactory<TableData, String>("date"));
        employee.setCellValueFactory(new PropertyValueFactory<TableData,String>("employee"));
        urgent.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("urgent"));
    }

// set action listener for everyone row in given table
    public void setActionlisteners(TableView tableView){

        tableView.setRowFactory(tv -> {
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
                            ordersList = new OrdersDAO(Session.getSession()).getAllOrders();
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

    // make urgent order orange
    private void seturg(TableColumn tableColumn){
        tableColumn.setCellFactory(column -> {
            return new TableCell<TableData, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                    } else { //If the cell is not empty
                        setText(item.toString());
                        TableData td = getTableView().getItems().get(getIndex());
                        TableRow currentRow = getTableRow();

                        if (td.getUrgent() == 1) {
                            currentRow.setStyle("-fx-background-color: orange;");
                           }else{
                            currentRow.setStyle("");
                        }
                    }
                }
            };
        });
    }
}



