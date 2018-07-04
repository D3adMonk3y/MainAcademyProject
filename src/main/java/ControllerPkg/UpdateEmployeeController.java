package ControllerPkg;

import DAO.EmployeeDAO;
import Entity.EmployesEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable{


    private static EmployesEntity ee;

    public static void setEmployesId(int employesId) {

        ee = new EmployeeDAO(Session.getSession()).getEmployee(employesId);
    }

    @FXML
    private TextField employeeName;
    @FXML
    private TextField employeePhone;
    @FXML
    private ComboBox<String> RankCombo;
    @FXML
    private ComboBox<Double> procentCombo;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField defaultSalary;
    @FXML
    private Button changeEmployee;

    @FXML
    public void changeEmployee(javafx.event.ActionEvent actionEvent) {
        ee.setEmployeName(employeeName.getText());
        ee.setEmployePhone(employeePhone.getText());
        ee.setRank(RankCombo.getSelectionModel().getSelectedItem());
        ee.setProcent(procentCombo.getSelectionModel().getSelectedItem());
        ee.setPassword(passwordText.getText());
        ee.setSalary(Double.parseDouble(defaultSalary.getText()));
        new EmployeeDAO(Session.getSession()).updateEmployee(ee);
        Stage stage = (Stage) changeEmployee.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     employeeName.setText(ee.getEmployeName());
        employeePhone.setText(ee.getEmployePhone());
        RankCombo.getItems().addAll("Майстер", "Менеджер", "Директор");
        RankCombo.getSelectionModel().select(ee.getRank());
        procentCombo.getItems().addAll(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0);
        procentCombo.getSelectionModel().select(ee.getProcent());
        passwordText.setText(ee.getPassword());
        defaultSalary.setText(ee.getSalary().toString());

    }
}
