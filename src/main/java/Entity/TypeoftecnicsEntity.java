package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "typeoftecnics", schema = "Orders")
public class TypeoftecnicsEntity {
    private int idType;
    private String type;

    @Id
    @Column(name = "idType")
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeoftecnicsEntity that = (TypeoftecnicsEntity) o;
        return idType == that.idType &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idType, type);
    }
}
