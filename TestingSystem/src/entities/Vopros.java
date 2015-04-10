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
@Table(name = "vopros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vopros.findAll", query = "SELECT v FROM Vopros v"),
    @NamedQuery(name = "Vopros.findByIdVopros", query = "SELECT v FROM Vopros v WHERE v.idVopros = :idVopros"),
    @NamedQuery(name = "Vopros.findByNazvanie", query = "SELECT v FROM Vopros v WHERE v.nazvanie = :nazvanie"),
    @NamedQuery(name = "Vopros.findByFormulirovka", query = "SELECT v FROM Vopros v WHERE v.formulirovka = :formulirovka"),
    @NamedQuery(name = "Vopros.findByBall", query = "SELECT v FROM Vopros v WHERE v.ball = :ball")})
public class Vopros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VOPROS")
    private Integer idVopros;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @Column(name = "FORMULIROVKA")
    private String formulirovka;
    @Column(name = "BALL")
    private Integer ball;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosIdVopros")
    private List<VoprosLatex> voprosLatexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosIdVopros")
    private List<TestVopros> testVoprosList;
    @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne(optional = false)
    private Disciplina disciplinaIdDisciplina;
    @JoinColumn(name = "KATEGORIYA_SLOZHNOSTI_ID_KATEGORIYA_SLOZHNOSTI", referencedColumnName = "ID_KATEGORIYA_SLOZHNOSTI")
    @ManyToOne(optional = false)
    private KategoriyaSlozhnosti kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    @JoinColumn(name = "TIP_VOPROSA_ID_TIP_VOPROSA", referencedColumnName = "ID_TIP_VOPROSA")
    @ManyToOne(optional = false)
    private TipVoprosa tipVoprosaIdTipVoprosa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosIdVopros")
    private List<VoprosPeretaskivanieKartinok> voprosPeretaskivanieKartinokList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voprosIdVopros")
    private List<VoprosSoedinenieLiniyami> voprosSoedinenieLiniyamiList;

    public Vopros() {
    }

    public Vopros(Integer idVopros) {
        this.idVopros = idVopros;
    }

    public Integer getIdVopros() {
        return idVopros;
    }

    public void setIdVopros(Integer idVopros) {
        this.idVopros = idVopros;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getFormulirovka() {
        return formulirovka;
    }

    public void setFormulirovka(String formulirovka) {
        this.formulirovka = formulirovka;
    }

    public Integer getBall() {
        return ball;
    }

    public void setBall(Integer ball) {
        this.ball = ball;
    }

    @XmlTransient
    public List<VoprosLatex> getVoprosLatexList() {
        return voprosLatexList;
    }

    public void setVoprosLatexList(List<VoprosLatex> voprosLatexList) {
        this.voprosLatexList = voprosLatexList;
    }

    @XmlTransient
    public List<TestVopros> getTestVoprosList() {
        return testVoprosList;
    }

    public void setTestVoprosList(List<TestVopros> testVoprosList) {
        this.testVoprosList = testVoprosList;
    }

    public Disciplina getDisciplinaIdDisciplina() {
        return disciplinaIdDisciplina;
    }

    public void setDisciplinaIdDisciplina(Disciplina disciplinaIdDisciplina) {
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    public KategoriyaSlozhnosti getKategoriyaSlozhnostiIdKategoriyaSlozhnosti() {
        return kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    }

    public void setKategoriyaSlozhnostiIdKategoriyaSlozhnosti(KategoriyaSlozhnosti kategoriyaSlozhnostiIdKategoriyaSlozhnosti) {
        this.kategoriyaSlozhnostiIdKategoriyaSlozhnosti = kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    }

    public TipVoprosa getTipVoprosaIdTipVoprosa() {
        return tipVoprosaIdTipVoprosa;
    }

    public void setTipVoprosaIdTipVoprosa(TipVoprosa tipVoprosaIdTipVoprosa) {
        this.tipVoprosaIdTipVoprosa = tipVoprosaIdTipVoprosa;
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
        hash += (idVopros != null ? idVopros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vopros)) {
            return false;
        }
        Vopros other = (Vopros) object;
        if ((this.idVopros == null && other.idVopros != null) || (this.idVopros != null && !this.idVopros.equals(other.idVopros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vopros[ idVopros=" + idVopros + " ]";
    }

}
