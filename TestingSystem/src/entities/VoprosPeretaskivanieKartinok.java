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
@Table(name = "vopros_peretaskivanie_kartinok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoprosPeretaskivanieKartinok.findAll", 
            query = "SELECT v FROM VoprosPeretaskivanieKartinok v"),
    @NamedQuery(name = "VoprosPeretaskivanieKartinok."
            + "findByIdVoprosPeretaskivanieKartinok", 
            query = "SELECT v FROM VoprosPeretaskivanieKartinok v "
                    + "WHERE v.idVoprosPeretaskivanieKartinok = "
                    + ":idVoprosPeretaskivanieKartinok")})
public class VoprosPeretaskivanieKartinok implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VOPROS_PERETASKIVANIE_KARTINOK")
    private Integer idVoprosPeretaskivanieKartinok;
    @JoinColumn(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY", 
            referencedColumnName = "ID_KOLVO_OBLASTEY")
    @ManyToOne(optional = false)
    private KolvoOblastey kolvoOblasteyIdKolvoOblastey;
    @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS")
    @ManyToOne(optional = false)
    private Vopros voprosIdVopros;
    @OneToMany(cascade = CascadeType.ALL, 
            mappedBy = "voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok")
    private List<PolozhenieKartinki> polozhenieKartinkiList;

    public VoprosPeretaskivanieKartinok() {
    }

    public VoprosPeretaskivanieKartinok(Integer idVoprosPeretaskivanieKartinok) {
        this.idVoprosPeretaskivanieKartinok = idVoprosPeretaskivanieKartinok;
    }

    public Integer getIdVoprosPeretaskivanieKartinok() {
        return idVoprosPeretaskivanieKartinok;
    }

    public void setIdVoprosPeretaskivanieKartinok(
            Integer idVoprosPeretaskivanieKartinok) {
        this.idVoprosPeretaskivanieKartinok = idVoprosPeretaskivanieKartinok;
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

    @XmlTransient
    public List<PolozhenieKartinki> getPolozhenieKartinkiList() {
        return polozhenieKartinkiList;
    }

    public void setPolozhenieKartinkiList(
            List<PolozhenieKartinki> polozhenieKartinkiList) {
        this.polozhenieKartinkiList = polozhenieKartinkiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoprosPeretaskivanieKartinok != null 
                ? idVoprosPeretaskivanieKartinok.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosPeretaskivanieKartinok)) {
            return false;
        }
        VoprosPeretaskivanieKartinok other = (VoprosPeretaskivanieKartinok) object;
        if ((this.idVoprosPeretaskivanieKartinok == null && 
                other.idVoprosPeretaskivanieKartinok != null) 
                || (this.idVoprosPeretaskivanieKartinok != null 
                && !this.idVoprosPeretaskivanieKartinok.
                        equals(other.idVoprosPeretaskivanieKartinok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosPeretaskivanieKartinok[ "
                + "idVoprosPeretaskivanieKartinok=" + 
                idVoprosPeretaskivanieKartinok + " ]";
    }

}
