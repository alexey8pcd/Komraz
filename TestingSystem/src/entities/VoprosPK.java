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
public class VoprosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_VOPROS")
    private int idVopros;
    @Basic(optional = false)
    @Column(name = "KATEGORIYA_SLOZHNOSTI_ID_ KATEGORIYA_SLOZHNOSTI")
    private int kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    @Basic(optional = false)
    @Column(name = "TIP_VOPROSA_ID_ TIP_VOPROSA")
    private int tipVoprosaIdTipVoprosa;
    @Basic(optional = false)
    @Column(name = "DISCIPLINA_ID_DISCIPLINA")
    private int disciplinaIdDisciplina;

    public VoprosPK() {
    }

    public VoprosPK(int idVopros, int kategoriyaSlozhnostiIdKategoriyaSlozhnosti, int tipVoprosaIdTipVoprosa, int disciplinaIdDisciplina) {
        this.idVopros = idVopros;
        this.kategoriyaSlozhnostiIdKategoriyaSlozhnosti = kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
        this.tipVoprosaIdTipVoprosa = tipVoprosaIdTipVoprosa;
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    public int getIdVopros() {
        return idVopros;
    }

    public void setIdVopros(int idVopros) {
        this.idVopros = idVopros;
    }

    public int getKategoriyaSlozhnostiIdKategoriyaSlozhnosti() {
        return kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    }

    public void setKategoriyaSlozhnostiIdKategoriyaSlozhnosti(int kategoriyaSlozhnostiIdKategoriyaSlozhnosti) {
        this.kategoriyaSlozhnostiIdKategoriyaSlozhnosti = kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
    }

    public int getTipVoprosaIdTipVoprosa() {
        return tipVoprosaIdTipVoprosa;
    }

    public void setTipVoprosaIdTipVoprosa(int tipVoprosaIdTipVoprosa) {
        this.tipVoprosaIdTipVoprosa = tipVoprosaIdTipVoprosa;
    }

    public int getDisciplinaIdDisciplina() {
        return disciplinaIdDisciplina;
    }

    public void setDisciplinaIdDisciplina(int disciplinaIdDisciplina) {
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVopros;
        hash += (int) kategoriyaSlozhnostiIdKategoriyaSlozhnosti;
        hash += (int) tipVoprosaIdTipVoprosa;
        hash += (int) disciplinaIdDisciplina;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoprosPK)) {
            return false;
        }
        VoprosPK other = (VoprosPK) object;
        if (this.idVopros != other.idVopros) {
            return false;
        }
        if (this.kategoriyaSlozhnostiIdKategoriyaSlozhnosti != other.kategoriyaSlozhnostiIdKategoriyaSlozhnosti) {
            return false;
        }
        if (this.tipVoprosaIdTipVoprosa != other.tipVoprosaIdTipVoprosa) {
            return false;
        }
        if (this.disciplinaIdDisciplina != other.disciplinaIdDisciplina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VoprosPK[ idVopros=" + idVopros + ", kategoriyaSlozhnostiIdKategoriyaSlozhnosti=" + kategoriyaSlozhnostiIdKategoriyaSlozhnosti + ", tipVoprosaIdTipVoprosa=" + tipVoprosaIdTipVoprosa + ", disciplinaIdDisciplina=" + disciplinaIdDisciplina + " ]";
    }

}
