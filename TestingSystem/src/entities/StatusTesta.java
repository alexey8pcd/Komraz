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
@Table(name = "status_testa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusTesta.findAll", query = "SELECT s FROM StatusTesta s"),
    @NamedQuery(name = "StatusTesta.findByIdStatusTesta", query = "SELECT s FROM StatusTesta s WHERE s.idStatusTesta = :idStatusTesta"),
    @NamedQuery(name = "StatusTesta.findByNaimenovanie", query = "SELECT s FROM StatusTesta s WHERE s.naimenovanie = :naimenovanie")})
public class StatusTesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_STATUS_TESTA")
    private Integer idStatusTesta;
    @Column(name = "NAIMENOVANIE")
    private String naimenovanie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusTestaIdStatusTesta")
    private List<Test> testList;

    public StatusTesta() {
    }

    public StatusTesta(Integer idStatusTesta) {
        this.idStatusTesta = idStatusTesta;
    }

    public Integer getIdStatusTesta() {
        return idStatusTesta;
    }

    public void setIdStatusTesta(Integer idStatusTesta) {
        this.idStatusTesta = idStatusTesta;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    @XmlTransient
    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatusTesta != null ? idStatusTesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusTesta)) {
            return false;
        }
        StatusTesta other = (StatusTesta) object;
        if ((this.idStatusTesta == null && other.idStatusTesta != null) || (this.idStatusTesta != null && !this.idStatusTesta.equals(other.idStatusTesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StatusTesta[ idStatusTesta=" + idStatusTesta + " ]";
    }

}
