package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Vopros.findByIdVopros", query = "SELECT v FROM Vopros v WHERE v.voprosPK.idVopros = :idVopros"),
    @NamedQuery(name = "Vopros.findByNazvanie", query = "SELECT v FROM Vopros v WHERE v.nazvanie = :nazvanie"),
    @NamedQuery(name = "Vopros.findByFormulirovka", query = "SELECT v FROM Vopros v WHERE v.formulirovka = :formulirovka"),
    @NamedQuery(name = "Vopros.findByBall", query = "SELECT v FROM Vopros v WHERE v.ball = :ball"),
    @NamedQuery(name = "Vopros.findByKategoriyaSlozhnostiIdKategoriyaSlozhnosti", query = "SELECT v FROM Vopros v WHERE v.voprosPK.kategoriyaSlozhnostiIdKategoriyaSlozhnosti = :kategoriyaSlozhnostiIdKategoriyaSlozhnosti"),
    @NamedQuery(name = "Vopros.findByTipVoprosaIdTipVoprosa", query = "SELECT v FROM Vopros v WHERE v.voprosPK.tipVoprosaIdTipVoprosa = :tipVoprosaIdTipVoprosa"),
    @NamedQuery(name = "Vopros.findByDisciplinaIdDisciplina", query = "SELECT v FROM Vopros v WHERE v.voprosPK.disciplinaIdDisciplina = :disciplinaIdDisciplina")})
public class Vopros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VoprosPK voprosPK;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @Column(name = "FORMULIROVKA")
    private String formulirovka;
    @Column(name = "BALL")
    private Integer ball;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vopros")
    private List<VoprosLatex> voprosLatexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vopros")
    private List<VoprosPeretaskivanieKartinok> voprosPeretaskivanieKartinokList;
    @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Disciplina disciplina;
    @JoinColumn(name = "KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI", referencedColumnName = "ID_ KATEGORIYA_SLOZHNOSTI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private KategoriyaSlozhnosti kategoriyaSlozhnosti;
    @JoinColumn(name = "TIP_VOPROSA_ID_ TIP_VOPROSA", referencedColumnName = "ID_ TIP_VOPROSA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipVoprosa tipVoprosa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vopros")
    private List<VoprosSoedinenieLiniyami> voprosSoedinenieLiniyamiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vopros")
    private List<TestVopros> testVoprosList;

    public Vopros() {
    }

    public Vopros(VoprosPK voprosPK) {
        this.voprosPK = voprosPK;
    }

    public Vopros(int idVopros, int kategoriyaSlozhnostiIdKategoriyaSlozhnosti, int tipVoprosaIdTipVoprosa, int disciplinaIdDisciplina) {
        this.voprosPK = new VoprosPK(idVopros, kategoriyaSlozhnostiIdKategoriyaSlozhnosti, tipVoprosaIdTipVoprosa, disciplinaIdDisciplina);
    }

    public VoprosPK getVoprosPK() {
        return voprosPK;
    }

    public void setVoprosPK(VoprosPK voprosPK) {
        this.voprosPK = voprosPK;
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
    public List<VoprosPeretaskivanieKartinok> getVoprosPeretaskivanieKartinokList() {
        return voprosPeretaskivanieKartinokList;
    }

    public void setVoprosPeretaskivanieKartinokList(List<VoprosPeretaskivanieKartinok> voprosPeretaskivanieKartinokList) {
        this.voprosPeretaskivanieKartinokList = voprosPeretaskivanieKartinokList;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public KategoriyaSlozhnosti getKategoriyaSlozhnosti() {
        return kategoriyaSlozhnosti;
    }

    public void setKategoriyaSlozhnosti(KategoriyaSlozhnosti kategoriyaSlozhnosti) {
        this.kategoriyaSlozhnosti = kategoriyaSlozhnosti;
    }

    public TipVoprosa getTipVoprosa() {
        return tipVoprosa;
    }

    public void setTipVoprosa(TipVoprosa tipVoprosa) {
        this.tipVoprosa = tipVoprosa;
    }

    @XmlTransient
    public List<VoprosSoedinenieLiniyami> getVoprosSoedinenieLiniyamiList() {
        return voprosSoedinenieLiniyamiList;
    }

    public void setVoprosSoedinenieLiniyamiList(List<VoprosSoedinenieLiniyami> voprosSoedinenieLiniyamiList) {
        this.voprosSoedinenieLiniyamiList = voprosSoedinenieLiniyamiList;
    }

    @XmlTransient
    public List<TestVopros> getTestVoprosList() {
        return testVoprosList;
    }

    public void setTestVoprosList(List<TestVopros> testVoprosList) {
        this.testVoprosList = testVoprosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voprosPK != null ? voprosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vopros)) {
            return false;
        }
        Vopros other = (Vopros) object;
        if ((this.voprosPK == null && other.voprosPK != null) || (this.voprosPK != null && !this.voprosPK.equals(other.voprosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vopros[ voprosPK=" + voprosPK + " ]";
    }

}
