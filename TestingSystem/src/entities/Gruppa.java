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
@Table(name = "gruppa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruppa.findAll", query = "SELECT g FROM Gruppa g"),
    @NamedQuery(name = "Gruppa.findByIdGruppa", query = "SELECT g FROM Gruppa g WHERE g.idGruppa = :idGruppa"),
    @NamedQuery(name = "Gruppa.findByNazvanie", query = "SELECT g FROM Gruppa g WHERE g.nazvanie = :nazvanie")})
public class Gruppa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPPA")
    private Integer idGruppa;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gruppaIdGruppa")
    private List<Student> studentList;

    public Gruppa() {
    }

    public Gruppa(Integer idGruppa) {
        this.idGruppa = idGruppa;
    }

    public Integer getIdGruppa() {
        return idGruppa;
    }

    public void setIdGruppa(Integer idGruppa) {
        this.idGruppa = idGruppa;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGruppa != null ? idGruppa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruppa)) {
            return false;
        }
        Gruppa other = (Gruppa) object;
        if ((this.idGruppa == null && other.idGruppa != null) || (this.idGruppa != null && !this.idGruppa.equals(other.idGruppa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Gruppa[ idGruppa=" + idGruppa + " ]";
    }

}
