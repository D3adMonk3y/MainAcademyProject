package ControllerPkg;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty client;
    private final SimpleStringProperty type;
    private final SimpleStringProperty model;
    private final SimpleStringProperty employee;
    private final SimpleStringProperty date;
    private final SimpleIntegerProperty urgent;
    private final SimpleStringProperty status;

    public TableData(SimpleIntegerProperty id, SimpleStringProperty client,
                     SimpleStringProperty type, SimpleStringProperty model,
                     SimpleStringProperty employee, SimpleStringProperty date,
                     SimpleIntegerProperty urgent, SimpleStringProperty status) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.model = model;
        this.employee = employee;
        this.date = date;
        this.urgent = urgent;
        this.status = status;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getClient() {
        return client.get();
    }

    public SimpleStringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getEmployee() {
        return employee.get();
    }

    public SimpleStringProperty employeeProperty() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee.set(employee);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getUrgent() {
        return urgent.get();
    }

    public SimpleIntegerProperty urgentProperty() {
        return urgent;
    }

    public void setUrgent(int urgent) {
        this.urgent.set(urgent);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
