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
@Table(name = "kolvo_oblastey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KolvoOblastey.findAll", query = "SELECT k FROM KolvoOblastey k"),
    @NamedQuery(name = "KolvoOblastey.findByIdKolvoOblastey", query = "SELECT k FROM KolvoOblastey k WHERE k.idKolvoOblastey = :idKolvoOblastey"),
    @NamedQuery(name = "KolvoOblastey.findByKolvo", query = "SELECT k FROM KolvoOblastey k WHERE k.kolvo = :kolvo")})
public class KolvoOblastey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_KOLVO_OBLASTEY")
    private Integer idKolvoOblastey;
    @Column(name = "KOLVO")
    private Integer kolvo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kolvoOblasteyIdKolvoOblastey")
    private List<VoprosPeretaskivanieKartinok> voprosPeretaskivanieKartinokList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kolvoOblasteyIdKolvoOblastey")
    private List<VoprosSoedinenieLiniyami> voprosSoedinenieLiniyamiList;

    public KolvoOblastey() {
    }

    public KolvoOblastey(Integer idKolvoOblastey) {
        this.idKolvoOblastey = idKolvoOblastey;
    }

    public Integer getIdKolvoOblastey() {
        return idKolvoOblastey;
    }

    public void setIdKolvoOblastey(Integer idKolvoOblastey) {
        this.idKolvoOblastey = idKolvoOblastey;
    }

    public Integer getKolvo() {
        return kolvo;
    }

    public void setKolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    @XmlTransient
    public List<VoprosPeretaskivanieKartinok> getVoprosPeretaskivanieKartinokList() {
        return voprosPeretaskivanieKartinokList;
    }

    public void setVoprosPeretaskivanieKartinokList(List<VoprosPeretaskivanieKartinok> voprosPeretaskivanieKartinokList) {
        this.voprosPeretaskivanieKartinokList = voprosPeretaskivanieKartinokList;
    }

    @XmlTransient
    public List<VoprosSoedinenieLiniyami> getVoprosSoedinenieLiniyamiList() {
        return voprosSoedinenieLiniyamiList;
    }

    public void setVoprosSoedinenieLiniyamiList(List<VoprosSoedinenieLiniyami> voprosSoedinenieLiniyamiList) {
        this.voprosSoedinenieLiniyamiList = voprosSoedinenieLiniyamiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKolvoOblastey != null ? idKolvoOblastey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KolvoOblastey)) {
            return false;
        }
        KolvoOblastey other = (KolvoOblastey) object;
        if ((this.idKolvoOblastey == null && other.idKolvoOblastey != null) || (this.idKolvoOblastey != null && !this.idKolvoOblastey.equals(other.idKolvoOblastey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.KolvoOblastey[ idKolvoOblastey=" + idKolvoOblastey + " ]";
    }

}
