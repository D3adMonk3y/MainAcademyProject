package ControllerPkg;

import DAO.ClientDAO;
import DAO.EmployeeDAO;
import Entity.ClientsEntity;
import Entity.EmployesEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addEmployeeController implements Initializable{
    @FXML
    private TextField employeeName;
    @FXML
    private TextField employeePhone;
    @FXML
    private TextField passwordText;
    @FXML
    private ComboBox<String> RankCombo;
    @FXML
    private ComboBox<Double> procentCombo;
    @FXML
    private TextField defaultSalary;
    @FXML
    private Button addEmployee;

    @FXML
    public void addEmployee(javafx.event.ActionEvent actionEvent) {

        String nameOfNewEmployeeString = employeeName.getText();
        String phoneNumberStr = employeePhone.getText();
        EmployeeDAO employeeDAO = new EmployeeDAO(Session.getSession());
        List<String> listOfEmployesNumber = new ArrayList<>();
        for(EmployesEntity m : employeeDAO.getAllEmployes()){
            listOfEmployesNumber.add(m.getEmployePhone());
        }
        if(nameOfNewEmployeeString.equals("") || phoneNumberStr.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Не всі дані про працівника введенні!");
            alert.showAndWait();
        }else if(listOfEmployesNumber.contains(phoneNumberStr)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setContentText("Такий номер телефону вже інснує!");
            alert.showAndWait();
        }else{
            EmployesEntity employesEntity = new EmployesEntity();
            employesEntity.setEmployeName(nameOfNewEmployeeString);
            employesEntity.setEmployePhone(phoneNumberStr);
            employesEntity.setProcent(procentCombo.getSelectionModel().getSelectedItem());
            employesEntity.setRank(RankCombo.getSelectionModel().getSelectedItem());
            employesEntity.setSalary(1500.0);
            employesEntity.setPassword(passwordText.getText());
            employesEntity.setSalary(Double.parseDouble(defaultSalary.getText()));
            employeeDAO.addEmployee(employesEntity);
            Stage stage =(Stage)addEmployee.getScene().getWindow();
            stage.close();

        }}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RankCombo.getItems().addAll("Майстер", "Менеджер", "Директор");
        RankCombo.getSelectionModel().selectFirst();
        procentCombo.getItems().addAll(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0);
        procentCombo.getSelectionModel().selectFirst();
    }
}



