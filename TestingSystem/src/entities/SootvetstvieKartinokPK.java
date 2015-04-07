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
public class SootvetstvieKartinokPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_SOOTVETSTVIE_KARTINOK")
    private int idSootvetstvieKartinok;
    @Basic(optional = false)
    @Column(name = "VOPROS-SOEDINENIE_LINIYAMI_ID_VOPROS-SOEDINENIE_LINIYAMI")
    private int voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
    @Basic(optional = false)
    @Column(name = "KARTINKA_ID_KARTINKA")
    private int kartinkaIdKartinka;
    @Basic(optional = false)
    @Column(name = "KARTINKA_ID_KARTINKA2")
    private int kartinkaIdKartinka2;

    public SootvetstvieKartinokPK() {
    }

    public SootvetstvieKartinokPK(int idSootvetstvieKartinok, int voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami, int kartinkaIdKartinka, int kartinkaIdKartinka2) {
        this.idSootvetstvieKartinok = idSootvetstvieKartinok;
        this.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami = voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
        this.kartinkaIdKartinka = kartinkaIdKartinka;
        this.kartinkaIdKartinka2 = kartinkaIdKartinka2;
    }

    public int getIdSootvetstvieKartinok() {
        return idSootvetstvieKartinok;
    }

    public void setIdSootvetstvieKartinok(int idSootvetstvieKartinok) {
        this.idSootvetstvieKartinok = idSootvetstvieKartinok;
    }

    public int getVoprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami() {
        return voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
    }

    public void setVoprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami(int voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami) {
        this.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami = voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
    }

    public int getKartinkaIdKartinka() {
        return kartinkaIdKartinka;
    }

    public void setKartinkaIdKartinka(int kartinkaIdKartinka) {
        this.kartinkaIdKartinka = kartinkaIdKartinka;
    }

    public int getKartinkaIdKartinka2() {
        return kartinkaIdKartinka2;
    }

    public void setKartinkaIdKartinka2(int kartinkaIdKartinka2) {
        this.kartinkaIdKartinka2 = kartinkaIdKartinka2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSootvetstvieKartinok;
        hash += (int) voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
        hash += (int) kartinkaIdKartinka;
        hash += (int) kartinkaIdKartinka2;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SootvetstvieKartinokPK)) {
            return false;
        }
        SootvetstvieKartinokPK other = (SootvetstvieKartinokPK) object;
        if (this.idSootvetstvieKartinok != other.idSootvetstvieKartinok) {
            return false;
        }
        if (this.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami != other.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami) {
            return false;
        }
        if (this.kartinkaIdKartinka != other.kartinkaIdKartinka) {
            return false;
        }
        if (this.kartinkaIdKartinka2 != other.kartinkaIdKartinka2) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SootvetstvieKartinokPK[ idSootvetstvieKartinok=" + idSootvetstvieKartinok + ", voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami=" + voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami + ", kartinkaIdKartinka=" + kartinkaIdKartinka + ", kartinkaIdKartinka2=" + kartinkaIdKartinka2 + " ]";
    }

}
