package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employes", schema = "Orders")
public class EmployesEntity {
    private int idEmployes;
    private String employeName;
    private Double salary;
    private String employePhone;
    private Double procent;
    private String Rank;
    private String password;

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Procents")
    public Double getProcent() {
        return procent;
    }

    public void setProcent(Double procent) {
        this.procent = procent;
    }

    @Basic
    @Column(name = "Rank")
    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    @Id
    @Column(name = "idEmployes")
    public int getIdEmployes() {
        return idEmployes;
    }

    public void setIdEmployes(int idEmployes) {
        this.idEmployes = idEmployes;
    }

    @Basic
    @Column(name = "EmployeName")
    public String getEmployeName() {
        return employeName;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }

    @Basic
    @Column(name = "Salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "EmployePhone")
    public String getEmployePhone() {
        return employePhone;
    }

    public void setEmployePhone(String employePhone) {
        this.employePhone = employePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployesEntity that = (EmployesEntity) o;
        return idEmployes == that.idEmployes &&
                Objects.equals(employeName, that.employeName) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(employePhone, that.employePhone) &&
                Objects.equals(procent, that.procent) &&
                Objects.equals(Rank, that.Rank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEmployes, employeName, salary, employePhone, procent, Rank);
    }
}
