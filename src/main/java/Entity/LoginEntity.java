package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login", schema = "Orders")
public class LoginEntity {
    private int idlogin;
    private String login;
    private String password;

    @Id
    @Column(name = "idlogin")
    public int getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(int idlogin) {
        this.idlogin = idlogin;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEntity that = (LoginEntity) o;
        return idlogin == that.idlogin &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idlogin, login, password);
    }
}
