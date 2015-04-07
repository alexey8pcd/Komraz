package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Solovenko
 */
@Embeddable
public class PolozhenieKartinkiPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_POLOZHENIE_KARTINKI")
    private int idPolozhenieKartinki;
    @Basic(optional = false)
    @Column(name = "KARTINKA_ID_KARTINKA")
    private int kartinkaIdKartinka;
    @Basic(optional = false)
    @Column(name = "VOPROS-PERETASKIVANIE_KARTINOK_ID_VOPROS-PERETASKIVANIE_KARTINOK")
    private int voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
    @Basic(optional = false)
    @Column(name = "PORYADKOVIY_NOMER_ID_PORYADKOVIY_NOMER")
    private int poryadkoviyNomerIdPoryadkoviyNomer;

    public PolozhenieKartinkiPK() {
    }

    public PolozhenieKartinkiPK(int idPolozhenieKartinki, int kartinkaIdKartinka, int voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok, int poryadkoviyNomerIdPoryadkoviyNomer) {
        this.idPolozhenieKartinki = idPolozhenieKartinki;
        this.kartinkaIdKartinka = kartinkaIdKartinka;
        this.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok = voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
        this.poryadkoviyNomerIdPoryadkoviyNomer = poryadkoviyNomerIdPoryadkoviyNomer;
    }

    public int getIdPolozhenieKartinki() {
        return idPolozhenieKartinki;
    }

    public void setIdPolozhenieKartinki(int idPolozhenieKartinki) {
        this.idPolozhenieKartinki = idPolozhenieKartinki;
    }

    public int getKartinkaIdKartinka() {
        return kartinkaIdKartinka;
    }

    public void setKartinkaIdKartinka(int kartinkaIdKartinka) {
        this.kartinkaIdKartinka = kartinkaIdKartinka;
    }

    public int getVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok() {
        return voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
    }

    public void setVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok(int voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok) {
        this.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok = voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
    }

    public int getPoryadkoviyNomerIdPoryadkoviyNomer() {
        return poryadkoviyNomerIdPoryadkoviyNomer;
    }

    public void setPoryadkoviyNomerIdPoryadkoviyNomer(int poryadkoviyNomerIdPoryadkoviyNomer) {
        this.poryadkoviyNomerIdPoryadkoviyNomer = poryadkoviyNomerIdPoryadkoviyNomer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPolozhenieKartinki;
        hash += (int) kartinkaIdKartinka;
        hash += (int) voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok;
        hash += (int) poryadkoviyNomerIdPoryadkoviyNomer;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolozhenieKartinkiPK)) {
            return false;
        }
        PolozhenieKartinkiPK other = (PolozhenieKartinkiPK) object;
        if (this.idPolozhenieKartinki != other.idPolozhenieKartinki) {
            return false;
        }
        if (this.kartinkaIdKartinka != other.kartinkaIdKartinka) {
            return false;
        }
        if (this.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok != other.voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok) {
            return false;
        }
        if (this.poryadkoviyNomerIdPoryadkoviyNomer != other.poryadkoviyNomerIdPoryadkoviyNomer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PolozhenieKartinkiPK[ idPolozhenieKartinki=" + idPolozhenieKartinki + ", kartinkaIdKartinka=" + kartinkaIdKartinka + ", voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok=" + voprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok + ", poryadkoviyNomerIdPoryadkoviyNomer=" + poryadkoviyNomerIdPoryadkoviyNomer + " ]";
    }

}
