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
@Table(name = "sootvetstvie_kartinok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SootvetstvieKartinok.findAll", query = "SELECT s FROM SootvetstvieKartinok s"),
    @NamedQuery(name = "SootvetstvieKartinok.findByIdSootvetstvieKartinok", query = "SELECT s FROM SootvetstvieKartinok s WHERE s.sootvetstvieKartinokPK.idSootvetstvieKartinok = :idSootvetstvieKartinok"),
    @NamedQuery(name = "SootvetstvieKartinok.findByVoprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami", query = "SELECT s FROM SootvetstvieKartinok s WHERE s.sootvetstvieKartinokPK.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami = :voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami"),
    @NamedQuery(name = "SootvetstvieKartinok.findByKartinkaIdKartinka", query = "SELECT s FROM SootvetstvieKartinok s WHERE s.sootvetstvieKartinokPK.kartinkaIdKartinka = :kartinkaIdKartinka"),
    @NamedQuery(name = "SootvetstvieKartinok.findByKartinkaIdKartinka2", query = "SELECT s FROM SootvetstvieKartinok s WHERE s.sootvetstvieKartinokPK.kartinkaIdKartinka2 = :kartinkaIdKartinka2")})
public class SootvetstvieKartinok implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SootvetstvieKartinokPK sootvetstvieKartinokPK;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA", referencedColumnName = "ID_KARTINKA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kartinka kartinka;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA2", referencedColumnName = "ID_KARTINKA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kartinka kartinka1;
    @JoinColumn(name = "VOPROS-SOEDINENIE_LINIYAMI_ID_VOPROS-SOEDINENIE_LINIYAMI", referencedColumnName = "ID_VOPROS-SOEDINENIE_LINIYAMI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VoprosSoedinenieLiniyami voprosSoedinenieLiniyami;

    public SootvetstvieKartinok() {
    }

    public SootvetstvieKartinok(SootvetstvieKartinokPK sootvetstvieKartinokPK) {
        this.sootvetstvieKartinokPK = sootvetstvieKartinokPK;
    }

    public SootvetstvieKartinok(int idSootvetstvieKartinok, int voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami, int kartinkaIdKartinka, int kartinkaIdKartinka2) {
        this.sootvetstvieKartinokPK = new SootvetstvieKartinokPK(idSootvetstvieKartinok, voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami, kartinkaIdKartinka, kartinkaIdKartinka2);
    }

    public SootvetstvieKartinokPK getSootvetstvieKartinokPK() {
        return sootvetstvieKartinokPK;
    }

    public void setSootvetstvieKartinokPK(SootvetstvieKartinokPK sootvetstvieKartinokPK) {
        this.sootvetstvieKartinokPK = sootvetstvieKartinokPK;
    }

    public Kartinka getKartinka() {
        return kartinka;
    }

    public void setKartinka(Kartinka kartinka) {
        this.kartinka = kartinka;
    }

    public Kartinka getKartinka1() {
        return kartinka1;
    }

    public void setKartinka1(Kartinka kartinka1) {
        this.kartinka1 = kartinka1;
    }

    public VoprosSoedinenieLiniyami getVoprosSoedinenieLiniyami() {
        return voprosSoedinenieLiniyami;
    }

    public void setVoprosSoedinenieLiniyami(VoprosSoedinenieLiniyami voprosSoedinenieLiniyami) {
        this.voprosSoedinenieLiniyami = voprosSoedinenieLiniyami;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sootvetstvieKartinokPK != null ? sootvetstvieKartinokPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SootvetstvieKartinok)) {
            return false;
        }
        SootvetstvieKartinok other = (SootvetstvieKartinok) object;
        if ((this.sootvetstvieKartinokPK == null && other.sootvetstvieKartinokPK != null) || (this.sootvetstvieKartinokPK != null && !this.sootvetstvieKartinokPK.equals(other.sootvetstvieKartinokPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SootvetstvieKartinok[ sootvetstvieKartinokPK=" + sootvetstvieKartinokPK + " ]";
    }

}
