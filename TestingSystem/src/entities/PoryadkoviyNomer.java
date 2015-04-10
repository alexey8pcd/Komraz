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
@Table(name = "poryadkoviy_nomer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoryadkoviyNomer.findAll", query = "SELECT p FROM PoryadkoviyNomer p"),
    @NamedQuery(name = "PoryadkoviyNomer.findByIdPoryadkoviyNomer", query = "SELECT p FROM PoryadkoviyNomer p WHERE p.idPoryadkoviyNomer = :idPoryadkoviyNomer"),
    @NamedQuery(name = "PoryadkoviyNomer.findByNomer", query = "SELECT p FROM PoryadkoviyNomer p WHERE p.nomer = :nomer")})
public class PoryadkoviyNomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PORYADKOVIY_NOMER")
    private Integer idPoryadkoviyNomer;
    @Column(name = "NOMER")
    private Integer nomer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poryadkoviyNomerIdPoryadkoviyNomer")
    private List<PolozhenieKartinki> polozhenieKartinkiList;

    public PoryadkoviyNomer() {
    }

    public PoryadkoviyNomer(Integer idPoryadkoviyNomer) {
        this.idPoryadkoviyNomer = idPoryadkoviyNomer;
    }

    public Integer getIdPoryadkoviyNomer() {
        return idPoryadkoviyNomer;
    }

    public void setIdPoryadkoviyNomer(Integer idPoryadkoviyNomer) {
        this.idPoryadkoviyNomer = idPoryadkoviyNomer;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }

    @XmlTransient
    public List<PolozhenieKartinki> getPolozhenieKartinkiList() {
        return polozhenieKartinkiList;
    }

    public void setPolozhenieKartinkiList(List<PolozhenieKartinki> polozhenieKartinkiList) {
        this.polozhenieKartinkiList = polozhenieKartinkiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoryadkoviyNomer != null ? idPoryadkoviyNomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoryadkoviyNomer)) {
            return false;
        }
        PoryadkoviyNomer other = (PoryadkoviyNomer) object;
        if ((this.idPoryadkoviyNomer == null && other.idPoryadkoviyNomer != null) || (this.idPoryadkoviyNomer != null && !this.idPoryadkoviyNomer.equals(other.idPoryadkoviyNomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PoryadkoviyNomer[ idPoryadkoviyNomer=" + idPoryadkoviyNomer + " ]";
    }

}
