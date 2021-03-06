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
@Table(name = "vopros_latex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoprosLatex.findAll", query = "SELECT v FROM VoprosLatex v"),
    @NamedQuery(name = "VoprosLatex.findByIdVoprosLatex", query = "SELECT v FROM VoprosLatex v WHERE v.idVoprosLatex = :idVoprosLatex"),
    @NamedQuery(name = "VoprosLatex.findByLatexZapis", query = "SELECT v FROM VoprosLatex v WHERE v.latexZapis = :latexZapis")})
public class VoprosLatex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VOPROS_LATEX")
    private Integer idVoprosLatex;
    @Column(name = "LATEX_ZAPIS")
    private String latexZapis;
    @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS")
    @ManyToOne(optional = false)
    private Vopros voprosIdVopros;

    public VoprosLatex() {
    }

    public VoprosLatex(Integer idVoprosLatex) {
        this.idVoprosLatex = idVoprosLatex;
    }

    public Integer getIdVoprosLatex() {
        return idVoprosLatex;
    }

    public void setIdVoprosLatex(Integer idVoprosLatex) {
        this.idVoprosLatex = idVoprosLatex;
    }

    public String getLatexZapis() {
        return latexZapis;
    }

    public void setLatexZapis(String latexZapis) {
        this.latexZapis = latexZapis;
    }

    public Vopros getVoprosIdVopros() {
        return voprosIdVopros;
    }

    public void setVoprosIdVopros(Vopros voprosIdVopros) {
        this.voprosIdVopros = voprosIdVopros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoprosLatex != null ? idVoprosLatex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosLatex)) {
            return false;
        }
        VoprosLatex other = (VoprosLatex) object;
        if ((this.idVoprosLatex == null && other.idVoprosLatex != null) || (this.idVoprosLatex != null && !this.idVoprosLatex.equals(other.idVoprosLatex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosLatex[ idVoprosLatex=" + idVoprosLatex + " ]";
    }

}
