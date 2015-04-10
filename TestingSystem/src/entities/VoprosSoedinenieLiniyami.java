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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vopros_soedinenie_liniyami")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoprosSoedinenieLiniyami.findAll", 
            query = "SELECT v FROM VoprosSoedinenieLiniyami v"),
    @NamedQuery(name = "VoprosSoedinenieLiniyami.findByIdVoprosSoedinenieLiniyami", 
            query = "SELECT v FROM VoprosSoedinenieLiniyami v "
                    + "WHERE v.idVoprosSoedinenieLiniyami = "
                    + ":idVoprosSoedinenieLiniyami")})
public class VoprosSoedinenieLiniyami implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VOPROS_SOEDINENIE_LINIYAMI")
    private Integer idVoprosSoedinenieLiniyami;
    @OneToMany(cascade = CascadeType.ALL, 
            mappedBy = "voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami")
    private List<SootvetstvieKartinok> sootvetstvieKartinokList;
    @JoinColumn(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY", 
            referencedColumnName = "ID_KOLVO_OBLASTEY")
    @ManyToOne(optional = false)
    private KolvoOblastey kolvoOblasteyIdKolvoOblastey;
    @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS")
    @ManyToOne(optional = false)
    private Vopros voprosIdVopros;

    public VoprosSoedinenieLiniyami() {
    }

    public VoprosSoedinenieLiniyami(Integer idVoprosSoedinenieLiniyami) {
        this.idVoprosSoedinenieLiniyami = idVoprosSoedinenieLiniyami;
    }

    public Integer getIdVoprosSoedinenieLiniyami() {
        return idVoprosSoedinenieLiniyami;
    }

    public void setIdVoprosSoedinenieLiniyami(
            Integer idVoprosSoedinenieLiniyami) {
        this.idVoprosSoedinenieLiniyami = idVoprosSoedinenieLiniyami;
    }

    @XmlTransient
    public List<SootvetstvieKartinok> getSootvetstvieKartinokList() {
        return sootvetstvieKartinokList;
    }

    public void setSootvetstvieKartinokList(
            List<SootvetstvieKartinok> sootvetstvieKartinokList) {
        this.sootvetstvieKartinokList = sootvetstvieKartinokList;
    }

    public KolvoOblastey getKolvoOblasteyIdKolvoOblastey() {
        return kolvoOblasteyIdKolvoOblastey;
    }

    public void setKolvoOblasteyIdKolvoOblastey(
            KolvoOblastey kolvoOblasteyIdKolvoOblastey) {
        this.kolvoOblasteyIdKolvoOblastey = kolvoOblasteyIdKolvoOblastey;
    }

    public Vopros getVoprosIdVopros() {
        return voprosIdVopros;
    }

    public void setVoprosIdVopros(Vopros voprosIdVopros) {
        this.voprosIdVopros = voprosIdVopros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoprosSoedinenieLiniyami != null ? 
                idVoprosSoedinenieLiniyami.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosSoedinenieLiniyami)) {
            return false;
        }
        VoprosSoedinenieLiniyami other = (VoprosSoedinenieLiniyami) object;
        if ((this.idVoprosSoedinenieLiniyami == null 
                && other.idVoprosSoedinenieLiniyami != null) 
                || (this.idVoprosSoedinenieLiniyami != null 
                && !this.idVoprosSoedinenieLiniyami.
                        equals(other.idVoprosSoedinenieLiniyami))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosSoedinenieLiniyami[ idVoprosSoedinenieLiniyami=" 
                + idVoprosSoedinenieLiniyami + " ]";
    }

}
