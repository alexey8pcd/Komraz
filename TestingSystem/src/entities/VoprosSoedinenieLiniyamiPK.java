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
public class VoprosSoedinenieLiniyamiPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_VOPROS-SOEDINENIE_LINIYAMI")
    private int idVoprosSoedinenieLiniyami;
    @Basic(optional = false)
    @Column(name = "KOLVO_OBLASTEY_ID_KOLVO_OBLASTEY")
    private int kolvoOblasteyIdKolvoOblastey;

    public VoprosSoedinenieLiniyamiPK() {
    }

    public VoprosSoedinenieLiniyamiPK(int idVoprosSoedinenieLiniyami, int kolvoOblasteyIdKolvoOblastey) {
        this.idVoprosSoedinenieLiniyami = idVoprosSoedinenieLiniyami;
        this.kolvoOblasteyIdKolvoOblastey = kolvoOblasteyIdKolvoOblastey;
    }

    public int getIdVoprosSoedinenieLiniyami() {
        return idVoprosSoedinenieLiniyami;
    }

    public void setIdVoprosSoedinenieLiniyami(int idVoprosSoedinenieLiniyami) {
        this.idVoprosSoedinenieLiniyami = idVoprosSoedinenieLiniyami;
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
        hash += (int) idVoprosSoedinenieLiniyami;
        hash += (int) kolvoOblasteyIdKolvoOblastey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosSoedinenieLiniyamiPK)) {
            return false;
        }
        VoprosSoedinenieLiniyamiPK other = (VoprosSoedinenieLiniyamiPK) object;
        if (this.idVoprosSoedinenieLiniyami != other.idVoprosSoedinenieLiniyami) {
            return false;
        }
        if (this.kolvoOblasteyIdKolvoOblastey != other.kolvoOblasteyIdKolvoOblastey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosSoedinenieLiniyamiPK[ idVoprosSoedinenieLiniyami=" + idVoprosSoedinenieLiniyami + ", kolvoOblasteyIdKolvoOblastey=" + kolvoOblasteyIdKolvoOblastey + " ]";
    }

}
