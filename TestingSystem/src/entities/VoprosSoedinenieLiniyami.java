package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "vopros-soedinenie_liniyami")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoprosSoedinenieLiniyami.findAll", query = "SELECT v FROM VoprosSoedinenieLiniyami v"),
    @NamedQuery(name = "VoprosSoedinenieLiniyami.findByIdVoprosSoedinenieLiniyami", query = "SELECT v FROM VoprosSoedinenieLiniyami v WHERE v.voprosSoedinenieLiniyamiPK.idVoprosSoedinenieLiniyami = :idVoprosSoedinenieLiniyami"),
    @NamedQuery(name = "VoprosSoedinenieLiniyami.findByKolvoOblasteyIdKolvoOblastey", query = "SELECT v FROM VoprosSoedinenieLiniyami v WHERE v.voprosSoedinenieLiniyamiPK.kolvoOblasteyIdKolvoOblastey = :kolvoOblasteyIdKolvoOblastey")})
public class VoprosSoedinenieLiniyami implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VoprosSoedinenieLiniyamiPK voprosSoedinenieLiniyamiPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosSoedinenieLiniyami")
    private List<SootvetstvieKartinok> sootvetstvieKartinokList;
    @JoinColumn(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY", referencedColumnName = "ID_KOLVO_OBLASTEY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private KolvoOblastey kolvoOblastey;
    @JoinColumns({
        @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS"),
        @JoinColumn(name = "VOPROS_KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI", referencedColumnName = "KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI"),
        @JoinColumn(name = "VOPROS_TIP_VOPROSA_ID_ TIP_VOPROSA", referencedColumnName = "TIP_VOPROSA_ID_ TIP_VOPROSA")})
    @ManyToOne(optional = false)
    private Vopros vopros;

    public VoprosSoedinenieLiniyami() {
    }

    public VoprosSoedinenieLiniyami(VoprosSoedinenieLiniyamiPK voprosSoedinenieLiniyamiPK) {
        this.voprosSoedinenieLiniyamiPK = voprosSoedinenieLiniyamiPK;
    }

    public VoprosSoedinenieLiniyami(int idVoprosSoedinenieLiniyami, int kolvoOblasteyIdKolvoOblastey) {
        this.voprosSoedinenieLiniyamiPK = new VoprosSoedinenieLiniyamiPK(idVoprosSoedinenieLiniyami, kolvoOblasteyIdKolvoOblastey);
    }

    public VoprosSoedinenieLiniyamiPK getVoprosSoedinenieLiniyamiPK() {
        return voprosSoedinenieLiniyamiPK;
    }

    public void setVoprosSoedinenieLiniyamiPK(VoprosSoedinenieLiniyamiPK voprosSoedinenieLiniyamiPK) {
        this.voprosSoedinenieLiniyamiPK = voprosSoedinenieLiniyamiPK;
    }

    @XmlTransient
    public List<SootvetstvieKartinok> getSootvetstvieKartinokList() {
        return sootvetstvieKartinokList;
    }

    public void setSootvetstvieKartinokList(List<SootvetstvieKartinok> sootvetstvieKartinokList) {
        this.sootvetstvieKartinokList = sootvetstvieKartinokList;
    }

    public KolvoOblastey getKolvoOblastey() {
        return kolvoOblastey;
    }

    public void setKolvoOblastey(KolvoOblastey kolvoOblastey) {
        this.kolvoOblastey = kolvoOblastey;
    }

    public Vopros getVopros() {
        return vopros;
    }

    public void setVopros(Vopros vopros) {
        this.vopros = vopros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voprosSoedinenieLiniyamiPK != null ? voprosSoedinenieLiniyamiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosSoedinenieLiniyami)) {
            return false;
        }
        VoprosSoedinenieLiniyami other = (VoprosSoedinenieLiniyami) object;
        if ((this.voprosSoedinenieLiniyamiPK == null && other.voprosSoedinenieLiniyamiPK != null) || (this.voprosSoedinenieLiniyamiPK != null && !this.voprosSoedinenieLiniyamiPK.equals(other.voprosSoedinenieLiniyamiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosSoedinenieLiniyami[ voprosSoedinenieLiniyamiPK=" + voprosSoedinenieLiniyamiPK + " ]";
    }

}
