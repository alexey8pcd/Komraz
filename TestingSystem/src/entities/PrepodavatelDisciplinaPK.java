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
public class PrepodavatelDisciplinaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PREPODAVATEL-DISCIPLINA")
    private int idPrepodavatelDisciplina;
    @Basic(optional = false)
    @Column(name = "PREPODAVATEL_ID_PREPODAVATEL")
    private int prepodavatelIdPrepodavatel;
    @Basic(optional = false)
    @Column(name = "DISCIPLINA_ID_DISCIPLINA")
    private int disciplinaIdDisciplina;

    public PrepodavatelDisciplinaPK() {
    }

    public PrepodavatelDisciplinaPK(int idPrepodavatelDisciplina, int prepodavatelIdPrepodavatel, int disciplinaIdDisciplina) {
        this.idPrepodavatelDisciplina = idPrepodavatelDisciplina;
        this.prepodavatelIdPrepodavatel = prepodavatelIdPrepodavatel;
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    public int getIdPrepodavatelDisciplina() {
        return idPrepodavatelDisciplina;
    }

    public void setIdPrepodavatelDisciplina(int idPrepodavatelDisciplina) {
        this.idPrepodavatelDisciplina = idPrepodavatelDisciplina;
    }

    public int getPrepodavatelIdPrepodavatel() {
        return prepodavatelIdPrepodavatel;
    }

    public void setPrepodavatelIdPrepodavatel(int prepodavatelIdPrepodavatel) {
        this.prepodavatelIdPrepodavatel = prepodavatelIdPrepodavatel;
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
        hash += (int) idPrepodavatelDisciplina;
        hash += (int) prepodavatelIdPrepodavatel;
        hash += (int) disciplinaIdDisciplina;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrepodavatelDisciplinaPK)) {
            return false;
        }
        PrepodavatelDisciplinaPK other = (PrepodavatelDisciplinaPK) object;
        if (this.idPrepodavatelDisciplina != other.idPrepodavatelDisciplina) {
            return false;
        }
        if (this.prepodavatelIdPrepodavatel != other.prepodavatelIdPrepodavatel) {
            return false;
        }
        if (this.disciplinaIdDisciplina != other.disciplinaIdDisciplina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrepodavatelDisciplinaPK[ idPrepodavatelDisciplina=" + idPrepodavatelDisciplina + ", prepodavatelIdPrepodavatel=" + prepodavatelIdPrepodavatel + ", disciplinaIdDisciplina=" + disciplinaIdDisciplina + " ]";
    }

}
