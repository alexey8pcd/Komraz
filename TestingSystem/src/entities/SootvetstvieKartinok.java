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
@Table(name = "sootvetstvie_kartinok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SootvetstvieKartinok.findAll", query = "SELECT s FROM SootvetstvieKartinok s"),
    @NamedQuery(name = "SootvetstvieKartinok.findByIdSootvetstvieKartinok", query = "SELECT s FROM SootvetstvieKartinok s WHERE s.idSootvetstvieKartinok = :idSootvetstvieKartinok")})
public class SootvetstvieKartinok implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SOOTVETSTVIE_KARTINOK")
    private Integer idSootvetstvieKartinok;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA", referencedColumnName = "ID_KARTINKA")
    @ManyToOne(optional = false)
    private Kartinka kartinkaIdKartinka;
    @JoinColumn(name = "KARTINKA_ID_KARTINKA2", referencedColumnName = "ID_KARTINKA")
    @ManyToOne(optional = false)
    private Kartinka kartinkaIdKartinka2;
    @JoinColumn(name = "VOPROS_SOEDINENIE_LINIYAMI_ID_VOPROS_SOEDINENIE_LINIYAMI", referencedColumnName = "ID_VOPROS_SOEDINENIE_LINIYAMI")
    @ManyToOne(optional = false)
    private VoprosSoedinenieLiniyami voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;

    public SootvetstvieKartinok() {
    }

    public SootvetstvieKartinok(Integer idSootvetstvieKartinok) {
        this.idSootvetstvieKartinok = idSootvetstvieKartinok;
    }

    public Integer getIdSootvetstvieKartinok() {
        return idSootvetstvieKartinok;
    }

    public void setIdSootvetstvieKartinok(Integer idSootvetstvieKartinok) {
        this.idSootvetstvieKartinok = idSootvetstvieKartinok;
    }

    public Kartinka getKartinkaIdKartinka() {
        return kartinkaIdKartinka;
    }

    public void setKartinkaIdKartinka(Kartinka kartinkaIdKartinka) {
        this.kartinkaIdKartinka = kartinkaIdKartinka;
    }

    public Kartinka getKartinkaIdKartinka2() {
        return kartinkaIdKartinka2;
    }

    public void setKartinkaIdKartinka2(Kartinka kartinkaIdKartinka2) {
        this.kartinkaIdKartinka2 = kartinkaIdKartinka2;
    }

    public VoprosSoedinenieLiniyami getVoprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami() {
        return voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
    }

    public void setVoprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami(VoprosSoedinenieLiniyami voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami) {
        this.voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami = voprosSoedinenieLiniyamiIdVoprosSoedinenieLiniyami;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSootvetstvieKartinok != null ? idSootvetstvieKartinok.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SootvetstvieKartinok)) {
            return false;
        }
        SootvetstvieKartinok other = (SootvetstvieKartinok) object;
        if ((this.idSootvetstvieKartinok == null && other.idSootvetstvieKartinok != null) || (this.idSootvetstvieKartinok != null && !this.idSootvetstvieKartinok.equals(other.idSootvetstvieKartinok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SootvetstvieKartinok[ idSootvetstvieKartinok=" + idSootvetstvieKartinok + " ]";
    }

}
