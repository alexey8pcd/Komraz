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
@Table(name = "kartinka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kartinka.findAll", query = "SELECT k FROM Kartinka k"),
    @NamedQuery(name = "Kartinka.findByIdKartinka", query = "SELECT k FROM Kartinka k WHERE k.idKartinka = :idKartinka"),
    @NamedQuery(name = "Kartinka.findByImgLink", query = "SELECT k FROM Kartinka k WHERE k.imgLink = :imgLink")})
public class Kartinka implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_KARTINKA")
    private Integer idKartinka;
    @Column(name = "IMG_LINK")
    private String imgLink;
    @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne(optional = false)
    private Disciplina disciplinaIdDisciplina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kartinkaIdKartinka")
    private List<SootvetstvieKartinok> sootvetstvieKartinokList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kartinkaIdKartinka2")
    private List<SootvetstvieKartinok> sootvetstvieKartinokList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kartinkaIdKartinka")
    private List<PolozhenieKartinki> polozhenieKartinkiList;

    public Kartinka() {
    }

    public Kartinka(Integer idKartinka) {
        this.idKartinka = idKartinka;
    }

    public Integer getIdKartinka() {
        return idKartinka;
    }

    public void setIdKartinka(Integer idKartinka) {
        this.idKartinka = idKartinka;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Disciplina getDisciplinaIdDisciplina() {
        return disciplinaIdDisciplina;
    }

    public void setDisciplinaIdDisciplina(Disciplina disciplinaIdDisciplina) {
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    @XmlTransient
    public List<SootvetstvieKartinok> getSootvetstvieKartinokList() {
        return sootvetstvieKartinokList;
    }

    public void setSootvetstvieKartinokList(List<SootvetstvieKartinok> sootvetstvieKartinokList) {
        this.sootvetstvieKartinokList = sootvetstvieKartinokList;
    }

    @XmlTransient
    public List<SootvetstvieKartinok> getSootvetstvieKartinokList1() {
        return sootvetstvieKartinokList1;
    }

    public void setSootvetstvieKartinokList1(List<SootvetstvieKartinok> sootvetstvieKartinokList1) {
        this.sootvetstvieKartinokList1 = sootvetstvieKartinokList1;
    }

    @XmlTransient
    public List<PolozhenieKartinki> getPolozhenieKartinkiList() {
        return polozhenieKartinkiList;
    }

    public void setPolozhenieKartinkiList(List<PolozhenieKartinki> polozhenieKartinkiList) {
        this.polozhenieKartinkiList = polozhenieKartinkiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKartinka != null ? idKartinka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kartinka)) {
            return false;
        }
        Kartinka other = (Kartinka) object;
        if ((this.idKartinka == null && other.idKartinka != null) || (this.idKartinka != null && !this.idKartinka.equals(other.idKartinka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Kartinka[ idKartinka=" + idKartinka + " ]";
    }

}
