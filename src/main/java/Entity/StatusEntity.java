package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status", schema = "Orders")
public class StatusEntity {
    private int idstatus;
    private String status;

    @Id
    @Column(name = "idstatus")
    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return idstatus == that.idstatus &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idstatus, status);
    }
}
