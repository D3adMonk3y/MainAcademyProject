package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "Orders")
public class ClientsEntity {
    private int idclients;
    private String clientName;
    private String clientPhone;

    @Id
    @Column(name = "idclients")
    public int getIdclients() {
        return idclients;
    }

    public void setIdclients(int idclients) {
        this.idclients = idclients;
    }

    @Basic
    @Column(name = "clientName")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "clientPhone")
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return idclients == that.idclients &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(clientPhone, that.clientPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idclients, clientName, clientPhone);
    }
}
