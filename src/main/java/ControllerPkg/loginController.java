package ControllerPkg;

import DAO.EmployeeDAO;
import Entity.EmployesEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable{

    @FXML
    private ComboBox<ComboItem> employeeCombo;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginButton;

    @FXML
    public void login(javafx.event.ActionEvent actionEvent) {
         EmployesEntity selectedEE = new EmployeeDAO(Session.getSession())
                         .getEmployee(employeeCombo.getSelectionModel()
                         .getSelectedItem().getValue());
         if(selectedEE.getPassword().equals(passwordText.getText())){
             if(selectedEE.getRank().equals("Директор")){
             Controller.setIsDirector(true);
             }
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Проект");
                        stage.setScene(new Scene(root1,800,600));
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
         }else{
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Помилка");
                        alert.setContentText("Не вірний пароль");
                        alert.showAndWait();
         }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<EmployesEntity> listEmployee = new EmployeeDAO(Session.getSession()).getAllEmployes();
        for (int i = 0; i < listEmployee.size(); i++) {
            ComboItem comboItem = new ComboItem(listEmployee.get(i).getIdEmployes(), listEmployee.get(i).getEmployeName());
            employeeCombo.getItems().add(comboItem);
        }
        employeeCombo.getSelectionModel().selectFirst();
    }
}
