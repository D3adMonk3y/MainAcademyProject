package ControllerPkg;

import DAO.*;
import Entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.print.PrintException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class updateController implements Initializable{
   private org.hibernate.Session ses = Session.getSession();


    private static int idOrder;

    public static void initOrderId(Integer id) {
        idOrder = id;

      }


    private OrdersEntity oe = new OrdersDAO(ses).getOrders(idOrder);


    private int oldStatus = (oe.getStatus() == 7) ? -1 : oe.getStatus();


    @FXML
    private ComboBox<ControllerPkg.ComboItem> clientCombo;
    @FXML
    private TextField clientPhone;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> TOTCombo;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> manufacturerCombo;
    @FXML
    private TextField modelText;
    @FXML
    private TextField serialNumberText;
    @FXML
    private TextArea conditionText;
    @FXML
    private TextArea problemText;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> employeeCombo;
    @FXML
    private ComboBox<ControllerPkg.ComboItem> orderStatusCombo;
    @FXML
    private TextArea workDoneText;
    @FXML
    private TextField price;
    @FXML
    private CheckBox urgentBox;
    @FXML
    private Button updateOrder;
    @FXML
    private Label dateLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //fill clients combobox
        setConvrt(clientCombo);

        List<ClientsEntity> clientsEntities = new ClientDAO(ses).getAllClients();
        for (int i = 0; i <clientsEntities.size() ; i++) {
            clientCombo.getItems().add(new ComboItem(clientsEntities.get(i).getIdclients(), clientsEntities.get(i).getClientName()));
        }
        clientCombo.getSelectionModel().select(
                new ComboItem(oe.getClient(), new ClientDAO(ses).getClient(oe.getClient()).getClientName()));

        //fill tot combobox
        setConvrt(TOTCombo);

        List<TypeoftecnicsEntity> totList = new TOTDAO(ses).getAllTOTs();
        for (int i = 0; i < totList.size(); i++) {
            TOTCombo.getItems().add(new ComboItem(totList.get(i).getIdType(), totList.get(i).getType()));
        }
        TOTCombo.getSelectionModel().select(
                new ComboItem(oe.getType(), new TOTDAO(ses).getTOT(oe.getType()).getType()));

        //fill manufacturer combobox
        setConvrt(manufacturerCombo);

        List<ManufacturerEntity> manufacturerEntities = new ManufacturerDAO(ses).getAllManufacturers();
        for (int i = 0; i < manufacturerEntities.size(); i++) {
            manufacturerCombo.getItems().add(new ComboItem(manufacturerEntities.get(i).getIdManufacturer(),
                    manufacturerEntities.get(i).getManufacturer()));
        }
        manufacturerCombo.getSelectionModel().select(
                new ComboItem(oe.getManufacturer(),
                        new ManufacturerDAO(ses).getManufacturer(oe.getManufacturer()).getManufacturer()));

        //fill employee combobox
        setConvrt(employeeCombo);

        List<EmployesEntity> employesEntities = new EmployeeDAO(ses).getAllEmployes();
        for (int i = 0; i < employesEntities.size() ; i++) {
            employeeCombo.getItems().add(new ComboItem(employesEntities.get(i).getIdEmployes(), employesEntities.get(i).getEmployeName()));
        }
        employeeCombo.getSelectionModel().select(
                new ComboItem(oe.getEmployee(), new EmployeeDAO(ses).getEmployee(oe.getEmployee()).getEmployeName()));

        //fill order status
        setConvrt(orderStatusCombo);

        List<StatusEntity> statusEntities = new StatusDAO(ses).getAllStatus();
        for (int i = 0; i < statusEntities.size() ; i++) {
            orderStatusCombo.getItems().add(new ComboItem(statusEntities.get(i).getIdstatus(), statusEntities.get(i).getStatus()));
        }
        orderStatusCombo.getSelectionModel().select(
                new ComboItem(oe.getStatus(), new StatusDAO(ses).getStatus(oe.getStatus()).getStatus()));

        clientPhone.setText(new ClientDAO(ses).getClient(
                oe.getClient()).getClientPhone());

        modelText.setText(oe.getModel());

        serialNumberText.setText(oe.getSerialNumber());

        conditionText.setText(oe.getLookNstate());

        problemText.setText(oe.getProblem());

        workDoneText.setText(oe.getWorkDone());

        price.setText(oe.getPrice().toString());

        dateLabel.setText(oe.getAddDate().toLocalDate().toString());

        if(oe.getUrgent() == 1)
        urgentBox.setSelected(true);
        else
            urgentBox.setSelected(false);



    }

    private void setConvrt(ComboBox<ComboItem> comboBox){

        comboBox.setConverter(new StringConverter<ComboItem>() {
            @Override
            public String toString(ComboItem object) {
                if(object==null)
                    return "";
                else
                    return object.getLabel();
            }

            @Override
            public ComboItem fromString(String string) {
                return comboBox.getItems().stream().filter(
                        ap -> ap.getLabel().equals(string)).findFirst().orElse(null);
            }
        });

        AutoFill.autoCompleteComboBoxPlus(comboBox,
                (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase())
                        || itemToCompare.toString().equals(typedText));
    }

    @FXML
    public void updateOrder(javafx.event.ActionEvent actionEvent){
        oe.setModel(modelText.getText());
        oe.setLookNstate(conditionText.getText());
        oe.setEmployee(employeeCombo.getSelectionModel().getSelectedItem().getValue());
        oe.setClient(clientCombo.getSelectionModel().getSelectedItem().getValue());

        oe.setStatus(orderStatusCombo.getSelectionModel().getSelectedItem().getValue());
        if(oldStatus!=-1 && oe.getStatus() == 7){
            EmployesEntity ee = new EmployeeDAO(Session.getSession()).getEmployee(oe.getEmployee());
            ee.setSalary(ee.getSalary()+oe.getPrice()*ee.getProcent());
        }

        oe.setManufacturer(manufacturerCombo.getSelectionModel().getSelectedItem().getValue());
        oe.setSerialNumber(serialNumberText.getText());
        oe.setPrice(Double.valueOf(price.getText()));
        oe.setType(TOTCombo.getSelectionModel().getSelectedItem().getValue());
        oe.setWorkDone(workDoneText.getText());
        oe.setUrgent((byte)(urgentBox.isSelected() ? 1 : 0));

        new OrdersDAO(ses).updateOrders(oe);
        Stage stage =(Stage)updateOrder.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void print(javafx.event.ActionEvent actionEvent){
        String printText = "\t\t\t\t\t\t\tКвитанція № " + oe.getIdOrders() + "\n\n\n" +
                "\tКлієнт: " + clientCombo.getSelectionModel().getSelectedItem().getLabel() + " \n" +
                "\tДата прийому: " + dateLabel.getText() + "\n" +
                "\t" + TOTCombo.getSelectionModel().getSelectedItem().getLabel() +
                " " + manufacturerCombo.getSelectionModel().getSelectedItem().getLabel() +
                " " + modelText.getText() + "\n" +
                "\tСерійний номер: " + serialNumberText.getText() + "\n"  +
                "\tЩо потрібно зробити:\n" +
                "\t" + problemText.getText() + "\n" +
                "\tСтан техніки:\n" +
                "\t" + conditionText.getText() + "\n\n\n\n\n" +
                "\tПідпис____________\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tМП____________\n\n\n\n\n" +
                "\t\t\t\t\t\t\tСервісний центр 'FixIt'\n" +
                "\t\t\t\t\t\t\tм. Тернопіль, вул. Такато 23, буд. 5\n" +
                "\t\t\t\t\t\t\tтел: (096)9982988";

        try {
            PrintOrder.print(printText);
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Друк не вдався!");
            alert.showAndWait();
        }
    }
}