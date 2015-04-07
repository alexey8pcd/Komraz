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
public class VoprosPeretaskivanieKartinokPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_VOPROS-PERETASKIVANIE_KARTINOK")
    private int idVoprosPeretaskivanieKartinok;
    @Basic(optional = false)
    @Column(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY")
    private int kolvoOblasteyIdKolvoOblastey;

    public VoprosPeretaskivanieKartinokPK() {
    }

    public VoprosPeretaskivanieKartinokPK(int idVoprosPeretaskivanieKartinok, int kolvoOblasteyIdKolvoOblastey) {
        this.idVoprosPeretaskivanieKartinok = idVoprosPeretaskivanieKartinok;
        this.kolvoOblasteyIdKolvoOblastey = kolvoOblasteyIdKolvoOblastey;
    }

    public int getIdVoprosPeretaskivanieKartinok() {
        return idVoprosPeretaskivanieKartinok;
    }

    public void setIdVoprosPeretaskivanieKartinok(int idVoprosPeretaskivanieKartinok) {
        this.idVoprosPeretaskivanieKartinok = idVoprosPeretaskivanieKartinok;
    }

    public int getKolvoOblasteyIdKolvoOblastey() {
        return kolvoOblasteyIdKolvoOblastey;
    }

    public void setKolvoOblasteyIdKolvoOblastey(int kolvoOblasteyIdKolvoOblastey) {
        this.kolvoOblasteyIdKolvoOblastey = kolvoOblasteyIdKolvoOblastey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVoprosPeretaskivanieKartinok;
        hash += (int) kolvoOblasteyIdKolvoOblastey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosPeretaskivanieKartinokPK)) {
            return false;
        }
        VoprosPeretaskivanieKartinokPK other = (VoprosPeretaskivanieKartinokPK) object;
        if (this.idVoprosPeretaskivanieKartinok != other.idVoprosPeretaskivanieKartinok) {
            return false;
        }
        if (this.kolvoOblasteyIdKolvoOblastey != other.kolvoOblasteyIdKolvoOblastey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosPeretaskivanieKartinokPK[ idVoprosPeretaskivanieKartinok=" + idVoprosPeretaskivanieKartinok + ", kolvoOblasteyIdKolvoOblastey=" + kolvoOblasteyIdKolvoOblastey + " ]";
    }

}
