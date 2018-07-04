package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "manufacturer", schema = "Orders")
public class ManufacturerEntity {
    private int idManufacturer;
    private String manufacturer;

    @Id
    @Column(name = "idManufacturer")
    public int getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    @Basic
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerEntity that = (ManufacturerEntity) o;
        return idManufacturer == that.idManufacturer &&
                Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idManufacturer, manufacturer);
    }
}
