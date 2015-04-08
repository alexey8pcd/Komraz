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
@Table(name = "kategoriya_slozhnosti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KategoriyaSlozhnosti.findAll", query = "SELECT k FROM KategoriyaSlozhnosti k"),
    @NamedQuery(name = "KategoriyaSlozhnosti.findByIdKategoriyaSlozhnosti", query = "SELECT k FROM KategoriyaSlozhnosti k WHERE k.idKategoriyaSlozhnosti = :idKategoriyaSlozhnosti"),
    @NamedQuery(name = "KategoriyaSlozhnosti.findByNazvanie", query = "SELECT k FROM KategoriyaSlozhnosti k WHERE k.nazvanie = :nazvanie")})
public class KategoriyaSlozhnosti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ KATEGORIYA_SLOZHNOSTI")
    private Integer idKategoriyaSlozhnosti;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoriyaSlozhnostiIdKategoriyaSlozhnosti")
    private List<Vopros> voprosList;

    public KategoriyaSlozhnosti() {
    }

    public KategoriyaSlozhnosti(Integer idKategoriyaSlozhnosti) {
        this.idKategoriyaSlozhnosti = idKategoriyaSlozhnosti;
    }

    public Integer getIdKategoriyaSlozhnosti() {
        return idKategoriyaSlozhnosti;
    }

    public void setIdKategoriyaSlozhnosti(Integer idKategoriyaSlozhnosti) {
        this.idKategoriyaSlozhnosti = idKategoriyaSlozhnosti;
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
        hash += (idKategoriyaSlozhnosti != null ? idKategoriyaSlozhnosti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KategoriyaSlozhnosti)) {
            return false;
        }
        KategoriyaSlozhnosti other = (KategoriyaSlozhnosti) object;
        if ((this.idKategoriyaSlozhnosti == null && other.idKategoriyaSlozhnosti != null) || (this.idKategoriyaSlozhnosti != null && !this.idKategoriyaSlozhnosti.equals(other.idKategoriyaSlozhnosti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.KategoriyaSlozhnosti[ idKategoriyaSlozhnosti=" + idKategoriyaSlozhnosti + " ]";
    }

}
