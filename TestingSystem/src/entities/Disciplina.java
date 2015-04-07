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
@Table(name = "disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d"),
    @NamedQuery(name = "Disciplina.findByIdDisciplina", query = "SELECT d FROM Disciplina d WHERE d.idDisciplina = :idDisciplina"),
    @NamedQuery(name = "Disciplina.findByNazvanie", query = "SELECT d FROM Disciplina d WHERE d.nazvanie = :nazvanie")})
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DISCIPLINA")
    private Integer idDisciplina;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplinaIdDisciplina")
    private List<Kartinka> kartinkaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
    private List<Vopros> voprosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
    private List<PrepodavatelDisciplina> prepodavatelDisciplinaList;

    public Disciplina() {
    }

    public Disciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @XmlTransient
    public List<Kartinka> getKartinkaList() {
        return kartinkaList;
    }

    public void setKartinkaList(List<Kartinka> kartinkaList) {
        this.kartinkaList = kartinkaList;
    }

    @XmlTransient
    public List<Vopros> getVoprosList() {
        return voprosList;
    }

    public void setVoprosList(List<Vopros> voprosList) {
        this.voprosList = voprosList;
    }

    @XmlTransient
    public List<PrepodavatelDisciplina> getPrepodavatelDisciplinaList() {
        return prepodavatelDisciplinaList;
    }

    public void setPrepodavatelDisciplinaList(List<PrepodavatelDisciplina> prepodavatelDisciplinaList) {
        this.prepodavatelDisciplinaList = prepodavatelDisciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Disciplina[ idDisciplina=" + idDisciplina + " ]";
    }

}
