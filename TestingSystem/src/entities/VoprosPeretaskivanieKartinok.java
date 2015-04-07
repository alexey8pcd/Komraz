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
@Table(name = "vopros-peretaskivanie_kartinok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoprosPeretaskivanieKartinok.findAll", query = "SELECT v FROM VoprosPeretaskivanieKartinok v"),
    @NamedQuery(name = "VoprosPeretaskivanieKartinok.findByIdVoprosPeretaskivanieKartinok", query = "SELECT v FROM VoprosPeretaskivanieKartinok v WHERE v.voprosPeretaskivanieKartinokPK.idVoprosPeretaskivanieKartinok = :idVoprosPeretaskivanieKartinok"),
    @NamedQuery(name = "VoprosPeretaskivanieKartinok.findByKolvoOblasteyIdKolvoOblastey", query = "SELECT v FROM VoprosPeretaskivanieKartinok v WHERE v.voprosPeretaskivanieKartinokPK.kolvoOblasteyIdKolvoOblastey = :kolvoOblasteyIdKolvoOblastey")})
public class VoprosPeretaskivanieKartinok implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VoprosPeretaskivanieKartinokPK voprosPeretaskivanieKartinokPK;
    @JoinColumn(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY", referencedColumnName = "ID_KOLVO_OBLASTEY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private KolvoOblastey kolvoOblastey;
    @JoinColumns({
        @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS"),
        @JoinColumn(name = "VOPROS_KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI", referencedColumnName = "KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI"),
        @JoinColumn(name = "VOPROS_TIP_VOPROSA_ID_ TIP_VOPROSA", referencedColumnName = "TIP_VOPROSA_ID_ TIP_VOPROSA")})
    @ManyToOne(optional = false)
    private Vopros vopros;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosPeretaskivanieKartinok")
    private List<PolozhenieKartinki> polozhenieKartinkiList;

    public VoprosPeretaskivanieKartinok() {
    }

    public VoprosPeretaskivanieKartinok(VoprosPeretaskivanieKartinokPK voprosPeretaskivanieKartinokPK) {
        this.voprosPeretaskivanieKartinokPK = voprosPeretaskivanieKartinokPK;
    }

    public VoprosPeretaskivanieKartinok(int idVoprosPeretaskivanieKartinok, int kolvoOblasteyIdKolvoOblastey) {
        this.voprosPeretaskivanieKartinokPK = new VoprosPeretaskivanieKartinokPK(idVoprosPeretaskivanieKartinok, kolvoOblasteyIdKolvoOblastey);
    }

    public VoprosPeretaskivanieKartinokPK getVoprosPeretaskivanieKartinokPK() {
        return voprosPeretaskivanieKartinokPK;
    }

    public void setVoprosPeretaskivanieKartinokPK(VoprosPeretaskivanieKartinokPK voprosPeretaskivanieKartinokPK) {
        this.voprosPeretaskivanieKartinokPK = voprosPeretaskivanieKartinokPK;
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
        hash += (voprosPeretaskivanieKartinokPK != null ? voprosPeretaskivanieKartinokPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosPeretaskivanieKartinok)) {
            return false;
        }
        VoprosPeretaskivanieKartinok other = (VoprosPeretaskivanieKartinok) object;
        if ((this.voprosPeretaskivanieKartinokPK == null && other.voprosPeretaskivanieKartinokPK != null) || (this.voprosPeretaskivanieKartinokPK != null && !this.voprosPeretaskivanieKartinokPK.equals(other.voprosPeretaskivanieKartinokPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosPeretaskivanieKartinok[ voprosPeretaskivanieKartinokPK=" + voprosPeretaskivanieKartinokPK + " ]";
    }

}
