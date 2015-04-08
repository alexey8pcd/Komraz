package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "PolozhenieKartinki.findAll", 
            query = "SELECT p FROM PolozhenieKartinki p"),
    @NamedQuery(name = "PolozhenieKartinki.findByIdPolozhenieKartinki", 
            query = "SELECT p FROM PolozhenieKartinki p "
                    + "WHERE p.idPolozhenieKartinki = :idPolozhenieKartinki")})
public class PolozhenieKartinki implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_POLOZHENIE_KARTINKI")
    private Integer idPolozhenieKartinki;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA", 
            referencedColumnName = "ID_KARTINKA")
    @ManyToOne(optional = false)
    private Kartinka kartinkaIdKartinka;
    @JoinColumn(name = "PORYADKOVIY_NOMER_ID_PORYADKOVIY_NOMER", 
            referencedColumnName = "ID_PORYADKOVIY_NOMER")
    @ManyToOne(optional = false)
    private PoryadkoviyNomer poryadkoviyNomerIdPoryadkoviyNomer;
    @JoinColumn(name = "VOPROS-PERETASKIVANIE_KARTINOK_"
            + "ID_VOPROS-PERETASKIVANIE_KARTINOK", 
            referencedColumnName = "ID_VOPROS-PERETASKIVANIE_KARTINOK")
    @ManyToOne(optional = false)
    private VoprosPeretaskivanieKartinok 
            voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;

    public PolozhenieKartinki() {
    }

    public PolozhenieKartinki(Integer idPolozhenieKartinki) {
        this.idPolozhenieKartinki = idPolozhenieKartinki;
    }

    public Integer getIdPolozhenieKartinki() {
        return idPolozhenieKartinki;
    }

    public void setIdPolozhenieKartinki(Integer idPolozhenieKartinki) {
        this.idPolozhenieKartinki = idPolozhenieKartinki;
    }

    public Kartinka getKartinkaIdKartinka() {
        return kartinkaIdKartinka;
    }

    public void setKartinkaIdKartinka(Kartinka kartinkaIdKartinka) {
        this.kartinkaIdKartinka = kartinkaIdKartinka;
    }

    public PoryadkoviyNomer getPoryadkoviyNomerIdPoryadkoviyNomer() {
        return poryadkoviyNomerIdPoryadkoviyNomer;
    }

    public void setPoryadkoviyNomerIdPoryadkoviyNomer(
            PoryadkoviyNomer poryadkoviyNomerIdPoryadkoviyNomer) {
        this.poryadkoviyNomerIdPoryadkoviyNomer = 
                poryadkoviyNomerIdPoryadkoviyNomer;
    }

    public VoprosPeretaskivanieKartinok 
        getVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok() {
        return voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
    }

    public void setVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok(
            VoprosPeretaskivanieKartinok voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok) {
        this.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok = 
                voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPolozhenieKartinki != null ? idPolozhenieKartinki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolozhenieKartinki)) {
            return false;
        }
        PolozhenieKartinki other = (PolozhenieKartinki) object;
        if ((this.idPolozhenieKartinki == null && other.idPolozhenieKartinki != null) || (this.idPolozhenieKartinki != null && !this.idPolozhenieKartinki.equals(other.idPolozhenieKartinki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PolozhenieKartinki[ idPolozhenieKartinki=" + idPolozhenieKartinki + " ]";
    }

}
