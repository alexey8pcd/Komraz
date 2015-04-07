package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Solovenko
 */
@Entity
@Table(name = "polozhenie_kartinki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PolozhenieKartinki.findAll", query = "SELECT p FROM PolozhenieKartinki p"),
    @NamedQuery(name = "PolozhenieKartinki.findByIdPolozhenieKartinki", query = "SELECT p FROM PolozhenieKartinki p WHERE p.polozhenieKartinkiPK.idPolozhenieKartinki = :idPolozhenieKartinki"),
    @NamedQuery(name = "PolozhenieKartinki.findByKartinkaIdKartinka", query = "SELECT p FROM PolozhenieKartinki p WHERE p.polozhenieKartinkiPK.kartinkaIdKartinka = :kartinkaIdKartinka"),
    @NamedQuery(name = "PolozhenieKartinki.findByVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok", query = "SELECT p FROM PolozhenieKartinki p WHERE p.polozhenieKartinkiPK.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok = :voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok"),
    @NamedQuery(name = "PolozhenieKartinki.findByPoryadkoviyNomerIdPoryadkoviyNomer", query = "SELECT p FROM PolozhenieKartinki p WHERE p.polozhenieKartinkiPK.poryadkoviyNomerIdPoryadkoviyNomer = :poryadkoviyNomerIdPoryadkoviyNomer")})
public class PolozhenieKartinki implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PolozhenieKartinkiPK polozhenieKartinkiPK;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA", referencedColumnName = "ID_KARTINKA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kartinka kartinka;
    @JoinColumn(name = "PORYADKOVIY_NOMER_ID_PORYADKOVIY_NOMER", referencedColumnName = "ID_PORYADKOVIY_NOMER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PoryadkoviyNomer poryadkoviyNomer;
    @JoinColumn(name = "VOPROS-PERETASKIVANIE_KARTINOK_ID_VOPROS-PERETASKIVANIE_KARTINOK", referencedColumnName = "ID_VOPROS-PERETASKIVANIE_KARTINOK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VoprosPeretaskivanieKartinok voprosPeretaskivanieKartinok;

    public PolozhenieKartinki() {
    }

    public PolozhenieKartinki(PolozhenieKartinkiPK polozhenieKartinkiPK) {
        this.polozhenieKartinkiPK = polozhenieKartinkiPK;
    }

    public PolozhenieKartinki(int idPolozhenieKartinki, int kartinkaIdKartinka, int voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok, int poryadkoviyNomerIdPoryadkoviyNomer) {
        this.polozhenieKartinkiPK = new PolozhenieKartinkiPK(idPolozhenieKartinki, kartinkaIdKartinka, voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok, poryadkoviyNomerIdPoryadkoviyNomer);
    }

    public PolozhenieKartinkiPK getPolozhenieKartinkiPK() {
        return polozhenieKartinkiPK;
    }

    public void setPolozhenieKartinkiPK(PolozhenieKartinkiPK polozhenieKartinkiPK) {
        this.polozhenieKartinkiPK = polozhenieKartinkiPK;
    }

    public Kartinka getKartinka() {
        return kartinka;
    }

    public void setKartinka(Kartinka kartinka) {
        this.kartinka = kartinka;
    }

    public PoryadkoviyNomer getPoryadkoviyNomer() {
        return poryadkoviyNomer;
    }

    public void setPoryadkoviyNomer(PoryadkoviyNomer poryadkoviyNomer) {
        this.poryadkoviyNomer = poryadkoviyNomer;
    }

    public VoprosPeretaskivanieKartinok getVoprosPeretaskivanieKartinok() {
        return voprosPeretaskivanieKartinok;
    }

    public void setVoprosPeretaskivanieKartinok(VoprosPeretaskivanieKartinok voprosPeretaskivanieKartinok) {
        this.voprosPeretaskivanieKartinok = voprosPeretaskivanieKartinok;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (polozhenieKartinkiPK != null ? polozhenieKartinkiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolozhenieKartinki)) {
            return false;
        }
        PolozhenieKartinki other = (PolozhenieKartinki) object;
        if ((this.polozhenieKartinkiPK == null && other.polozhenieKartinkiPK != null) || (this.polozhenieKartinkiPK != null && !this.polozhenieKartinkiPK.equals(other.polozhenieKartinkiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PolozhenieKartinki[ polozhenieKartinkiPK=" + polozhenieKartinkiPK + " ]";
    }

}
