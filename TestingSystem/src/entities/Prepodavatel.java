package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Solovenko
 */
@Entity
@Table(name = "prepodavatel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prepodavatel.findAll", 
            query = "SELECT p FROM Prepodavatel p"),
    @NamedQuery(name = "Prepodavatel.findByIdPrepodavatel", 
            query = "SELECT p FROM Prepodavatel p "
                    + "WHERE p.idPrepodavatel = :idPrepodavatel"),
    @NamedQuery(name = "Prepodavatel.findByFio", 
            query = "SELECT p FROM Prepodavatel p WHERE p.fio = :fio"),
    @NamedQuery(name = "Prepodavatel.findByLogin", 
            query = "SELECT p FROM Prepodavatel p WHERE p.login = :login"),
    @NamedQuery(name = "Prepodavatel.findByPassword", 
            query = "SELECT p FROM Prepodavatel p WHERE p.password = :password")})
public class Prepodavatel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PREPODAVATEL")
    private Integer idPrepodavatel;
    @Column(name = "FIO")
    private String fio;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prepodavatelIdPrepodavatel")
    private List<PrepodavatelDisciplina> prepodavatelDisciplinaList;

    public Prepodavatel() {
    }

    public Prepodavatel(Integer idPrepodavatel) {
        this.idPrepodavatel = idPrepodavatel;
    }

    public Integer getIdPrepodavatel() {
        return idPrepodavatel;
    }

    public void setIdPrepodavatel(Integer idPrepodavatel) {
        this.idPrepodavatel = idPrepodavatel;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<PrepodavatelDisciplina> getPrepodavatelDisciplinaList() {
        return prepodavatelDisciplinaList;
    }

    public void setPrepodavatelDisciplinaList(
            List<PrepodavatelDisciplina> prepodavatelDisciplinaList) {
        this.prepodavatelDisciplinaList = prepodavatelDisciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrepodavatel != null ? idPrepodavatel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prepodavatel)) {
            return false;
        }
        Prepodavatel other = (Prepodavatel) object;
        if ((this.idPrepodavatel == null && other.idPrepodavatel != null) 
                || (this.idPrepodavatel != null 
                && !this.idPrepodavatel.equals(other.idPrepodavatel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prepodavatel[ idPrepodavatel=" + idPrepodavatel + " ]";
    }

}
