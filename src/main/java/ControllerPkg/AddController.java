package ControllerPkg;

import DAO.*;
import Entity.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import ControllerPkg.AutoFill;
import javafx.util.StringConverter;

import javax.print.PrintException;

public class AddController implements Initializable {

    @FXML
    private ComboBox<ControllerPkg.ComboItem> TOTCombo;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> clientCombo;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> manufacturerCombo;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> employeeCombo;
    @FXML
    private TextArea problemText;
    @FXML
    private TextArea conditionText;
    @FXML
    private Button addOrder;
    @FXML
    private CheckBox UrgentBox;
    @FXML
    private TextField ModelText;
    @FXML
    private TextField SerialNumberText;

    public void initialize(URL location, ResourceBundle resources) {

        // fill type of tehnics combobox
        //Робить щоб із комбоббокса можна дістати поле value із comboitem
        TOTCombo.setConverter(new StringConverter<ControllerPkg.ComboItem>() {
            @Override
            public String toString(ControllerPkg.ComboItem object) {
                if(object==null)
                    return "";
                else
                return object.getLabel();
            }

            @Override
            public ControllerPkg.ComboItem fromString(String string) {
                return TOTCombo.getItems().stream().filter(
                        ap -> ap.getLabel().equals(string)).findFirst().orElse(null);
            }
        });

    List<TypeoftecnicsEntity> totList = new TOTDAO(Session.getSession()).getAllTOTs();
               for (int i = 0; i < totList.size(); i++) {
                TOTCombo.getItems().add(new ControllerPkg.ComboItem(totList.get(i).getIdType(), totList.get(i).getType()));
                }

        AutoFill.autoCompleteComboBoxPlus(TOTCombo,
                (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase())
                        || itemToCompare.toString().equals(typedText));
        TOTCombo.getSelectionModel().selectFirst();

//     fill manufacturers combobox
        manufacturerCombo.setConverter(new StringConverter<ControllerPkg.ComboItem>() {
            @Override
            public String toString(ControllerPkg.ComboItem object) {
                if(object==null)
                    return "";
                else
                    return object.getLabel();
            }

            @Override
            public ControllerPkg.ComboItem fromString(String string) {
                return manufacturerCombo.getItems().stream().filter(
                        ap -> ap.getLabel().equals(string)).findFirst().orElse(null);
            }
        });

    List<ManufacturerEntity> manufacturerEntities = new ManufacturerDAO(Session.getSession()).getAllManufacturers();
        for (int i = 0; i < manufacturerEntities.size(); i++) {
            manufacturerCombo.getItems().add(new ControllerPkg.ComboItem(manufacturerEntities.get(i).getIdManufacturer(),
                    manufacturerEntities.get(i).getManufacturer()));
        }

        AutoFill.autoCompleteComboBoxPlus(manufacturerCombo,
                (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase())
                        || itemToCompare.toString().equals(typedText));
        manufacturerCombo.getSelectionModel().selectFirst();

        //fill clients combox
        clientCombo.setConverter(new StringConverter<ControllerPkg.ComboItem>() {
            @Override
            public String toString(ControllerPkg.ComboItem object) {
                if(object==null)
                    return "";
                else
                    return object.getLabel();
            }

            @Override
            public ControllerPkg.ComboItem fromString(String string) {
                return clientCombo.getItems().stream().filter(
                        ap -> ap.getLabel().equals(string)).findFirst().orElse(null);
            }
        });

        List<ClientsEntity> clientsEntities = new ClientDAO(Session.getSession()).getAllClients();
        for (int i = 0; i <clientsEntities.size() ; i++) {
            clientCombo.getItems().add(new ControllerPkg.ComboItem(clientsEntities.get(i).getIdclients(), clientsEntities.get(i).getClientName()));
        }

        AutoFill.autoCompleteComboBoxPlus(clientCombo,
                (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase())
                        || itemToCompare.toString().equals(typedText));
        clientCombo.getSelectionModel().selectFirst();

        //fill workers combo
        employeeCombo.setConverter(new StringConverter<ControllerPkg.ComboItem>() {
            @Override
            public String toString(ControllerPkg.ComboItem object) {
                if(object==null)
                    return "";
                else
                    return object.getLabel();
            }

            @Override
            public ControllerPkg.ComboItem fromString(String string) {
                return employeeCombo.getItems().stream().filter(
                        ap -> ap.getLabel().equals(string)).findFirst().orElse(null);
            }
        });

        List<EmployesEntity> employesEntities = new EmployeeDAO(Session.getSession()).getAllEmployes();
        for (int i = 0; i < employesEntities.size() ; i++) {
            employeeCombo.getItems().add(new ControllerPkg.ComboItem(employesEntities.get(i).getIdEmployes(), employesEntities.get(i).getEmployeName()));
        }

        AutoFill.autoCompleteComboBoxPlus(employeeCombo,
                (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase())
                        || itemToCompare.toString().equals(typedText));
        employeeCombo.getSelectionModel().selectFirst();
    }

    // додавання нового виду техніки
    @FXML
    public void addTOT(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/addTOT.fxml"));

            Parent root = fxmlLoader1.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Add new type of technique");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                TOTCombo.getItems().removeAll(TOTCombo.getItems());
                List<TypeoftecnicsEntity> totList = new TOTDAO(Session.getSession()).getAllTOTs();
                for(int i = 0; i < totList.size(); i++) {
                    TOTCombo.getItems().add(new ControllerPkg.ComboItem(totList.get(i).getIdType(), totList.get(i).getType()));
                }
                TOTCombo.getSelectionModel().selectFirst();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    // додавання нового виробника
    @FXML
    public void addManufacturer(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/addManufacturer.fxml"));

            Parent root = fxmlLoader1.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Add new Manufacturer");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                manufacturerCombo.getItems().removeAll(manufacturerCombo.getItems());
                List<ManufacturerEntity> manufacturerList = new ManufacturerDAO(Session.getSession()).getAllManufacturers();
                for(int i = 0; i < manufacturerList.size(); i++) {
                    manufacturerCombo.getItems().add(new ControllerPkg.ComboItem(manufacturerList.get(i).getIdManufacturer(),
                            manufacturerList.get(i).getManufacturer()));
                }
                manufacturerCombo.getSelectionModel().selectFirst();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // додавання нового клієнта
    @FXML
    public void addClient(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/addClient.fxml"));

            Parent root = fxmlLoader1.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Add new Client");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnHiding((WindowEvent we) -> {
                clientCombo.getItems().removeAll(clientCombo.getItems());
                List<ClientsEntity> clientList = new ClientDAO(Session.getSession()).getAllClients();
                for(int i = 0; i < clientList.size(); i++) {
                    clientCombo.getItems().add(new ControllerPkg.ComboItem(clientList.get(i).getIdclients(), clientList.get(i).getClientName()));
                }
                clientCombo.getSelectionModel().selectFirst();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Додавання нового замовлення
    @FXML
    public void addOrder(javafx.event.ActionEvent actionEvent) {

        String problem = problemText.getText();
        String condition = conditionText.getText();
        OrdersDAO ordersDAO = new OrdersDAO(Session.getSession());

        //Перевірка чи всі поля заповненні
        if(problem.equals("") || condition.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не всі дані введенні!");
            alert.showAndWait();
        }else{
            OrdersEntity order = new OrdersEntity();
            order.setProblem(problem);
            order.setLookNstate(condition);
            order.setAddDate(new Date(System.currentTimeMillis()));
            order.setUrgent((byte)(UrgentBox.isSelected()?1:0));
            order.setSerialNumber(SerialNumberText.getText());
            order.setModel(ModelText.getText());
            order.setType(TOTCombo.getSelectionModel().getSelectedItem().getValue());
            order.setManufacturer(manufacturerCombo.getSelectionModel().getSelectedItem().getValue());
            order.setStatus(1);
            order.setClient(clientCombo.getSelectionModel().getSelectedItem().getValue());
            order.setEmployee(employeeCombo.getSelectionModel().getSelectedItem().getValue());
            order.setPrice(0.0);
            ordersDAO.addOrder(order);
            Stage stage =(Stage)addOrder.getScene().getWindow();
            stage.close();
        }
    }
}


