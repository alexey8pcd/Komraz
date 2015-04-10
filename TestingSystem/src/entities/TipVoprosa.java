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
@Table(name = "tip_voprosa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipVoprosa.findAll", query = "SELECT t FROM TipVoprosa t"),
    @NamedQuery(name = "TipVoprosa.findByIdTipVoprosa", query = "SELECT t FROM TipVoprosa t WHERE t.idTipVoprosa = :idTipVoprosa"),
    @NamedQuery(name = "TipVoprosa.findByNazvanie", query = "SELECT t FROM TipVoprosa t WHERE t.nazvanie = :nazvanie")})
public class TipVoprosa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIP_VOPROSA")
    private Integer idTipVoprosa;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipVoprosaIdTipVoprosa")
    private List<Vopros> voprosList;

    public TipVoprosa() {
    }

    public TipVoprosa(Integer idTipVoprosa) {
        this.idTipVoprosa = idTipVoprosa;
    }

    public Integer getIdTipVoprosa() {
        return idTipVoprosa;
    }

    public void setIdTipVoprosa(Integer idTipVoprosa) {
        this.idTipVoprosa = idTipVoprosa;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @XmlTransient
    public List<Vopros> getVoprosList() {
        return voprosList;
    }

    public void setVoprosList(List<Vopros> voprosList) {
        this.voprosList = voprosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipVoprosa != null ? idTipVoprosa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipVoprosa)) {
            return false;
        }
        TipVoprosa other = (TipVoprosa) object;
        if ((this.idTipVoprosa == null && other.idTipVoprosa != null) || (this.idTipVoprosa != null && !this.idTipVoprosa.equals(other.idTipVoprosa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipVoprosa[ idTipVoprosa=" + idTipVoprosa + " ]";
    }

}
