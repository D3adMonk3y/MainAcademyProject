package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "Orders")
public class OrdersEntity {
    @Id
    @Column(name = "idOrders")
    private int idOrders;
    @Basic
    @Column(name = "type")
    private Integer type;
    @Basic
    @Column(name = "manufacturer")
    private Integer manufacturer;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "serialNumber")
    private String serialNumber;
    @Basic
    @Column(name = "problem")
    private String problem;
    @Basic
    @Column(name = "lookNstate")
    private String lookNstate;
    @Basic
    @Column(name = "client")
    private Integer client;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "employee")
    private Integer employee;
    @Basic
    @Column(name = "addDate")
    private Date addDate;
    @Basic
    @Column(name = "urgent")
    private Byte urgent;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "workDone")
    private String workDone;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "idType", insertable=false, updatable=false)
    private TypeoftecnicsEntity typeoftecnicsByType;
    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "idManufacturer", insertable=false, updatable=false)
    private ManufacturerEntity manufacturerByManufacturer;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "idclients", insertable=false, updatable=false)
    private ClientsEntity clientsByClient;
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "idstatus", insertable=false, updatable=false)
    private StatusEntity statusByStatus;
    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "idEmployes", insertable=false, updatable=false)
    private EmployesEntity employesByEmployee;


    public int getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(int idOrders) {
        this.idOrders = idOrders;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }


    public String getLookNstate() {
        return lookNstate;
    }

    public void setLookNstate(String lookNstate) {
        this.lookNstate = lookNstate;
    }


    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }


    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public Byte getUrgent() {
        return urgent;
    }

    public void setUrgent(Byte urgent) {
        this.urgent = urgent;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return idOrders == that.idOrders &&
                Objects.equals(type, that.type) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(model, that.model) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(problem, that.problem) &&
                Objects.equals(lookNstate, that.lookNstate) &&
                Objects.equals(client, that.client) &&
                Objects.equals(status, that.status) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(addDate, that.addDate) &&
                Objects.equals(urgent, that.urgent) &&
                Objects.equals(price, that.price) &&
                Objects.equals(workDone, that.workDone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrders, type, manufacturer, model, serialNumber, problem, lookNstate, client, status, employee, addDate, urgent, price, workDone);
    }


    public TypeoftecnicsEntity getTypeoftecnicsByType() {
        return typeoftecnicsByType;
    }

    public void setTypeoftecnicsByType(TypeoftecnicsEntity typeoftecnicsByType) {
        this.typeoftecnicsByType = typeoftecnicsByType;
    }


    public ManufacturerEntity getManufacturerByManufacturer() {
        return manufacturerByManufacturer;
    }

    public void setManufacturerByManufacturer(ManufacturerEntity manufacturerByManufacturer) {
        this.manufacturerByManufacturer = manufacturerByManufacturer;
    }


    public ClientsEntity getClientsByClient() {
        return clientsByClient;
    }

    public void setClientsByClient(ClientsEntity clientsByClient) {
        this.clientsByClient = clientsByClient;
    }

    public StatusEntity getStatusByStatus() {
        return statusByStatus;
    }

    public void setStatusByStatus(StatusEntity statusByStatus) {
        this.statusByStatus = statusByStatus;
    }


    public EmployesEntity getEmployesByEmployee() {
        return employesByEmployee;
    }

    public void setEmployesByEmployee(EmployesEntity employesByEmployee) {
        this.employesByEmployee = employesByEmployee;
    }
}
