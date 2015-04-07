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
@Table(name = "prepodavatel-disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrepodavatelDisciplina.findAll", query = "SELECT p FROM PrepodavatelDisciplina p"),
    @NamedQuery(name = "PrepodavatelDisciplina.findByIdPrepodavatelDisciplina", query = "SELECT p FROM PrepodavatelDisciplina p WHERE p.prepodavatelDisciplinaPK.idPrepodavatelDisciplina = :idPrepodavatelDisciplina"),
    @NamedQuery(name = "PrepodavatelDisciplina.findByPrepodavatelIdPrepodavatel", query = "SELECT p FROM PrepodavatelDisciplina p WHERE p.prepodavatelDisciplinaPK.prepodavatelIdPrepodavatel = :prepodavatelIdPrepodavatel"),
    @NamedQuery(name = "PrepodavatelDisciplina.findByDisciplinaIdDisciplina", query = "SELECT p FROM PrepodavatelDisciplina p WHERE p.prepodavatelDisciplinaPK.disciplinaIdDisciplina = :disciplinaIdDisciplina")})
public class PrepodavatelDisciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrepodavatelDisciplinaPK prepodavatelDisciplinaPK;
    @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Disciplina disciplina;
    @JoinColumn(name = "PREPODAVATEL_ID_PREPODAVATEL", referencedColumnName = "ID_PREPODAVATEL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prepodavatel prepodavatel;

    public PrepodavatelDisciplina() {
    }

    public PrepodavatelDisciplina(PrepodavatelDisciplinaPK prepodavatelDisciplinaPK) {
        this.prepodavatelDisciplinaPK = prepodavatelDisciplinaPK;
    }

    public PrepodavatelDisciplina(int idPrepodavatelDisciplina, int prepodavatelIdPrepodavatel, int disciplinaIdDisciplina) {
        this.prepodavatelDisciplinaPK = new PrepodavatelDisciplinaPK(idPrepodavatelDisciplina, prepodavatelIdPrepodavatel, disciplinaIdDisciplina);
    }

    public PrepodavatelDisciplinaPK getPrepodavatelDisciplinaPK() {
        return prepodavatelDisciplinaPK;
    }

    public void setPrepodavatelDisciplinaPK(PrepodavatelDisciplinaPK prepodavatelDisciplinaPK) {
        this.prepodavatelDisciplinaPK = prepodavatelDisciplinaPK;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Prepodavatel getPrepodavatel() {
        return prepodavatel;
    }

    public void setPrepodavatel(Prepodavatel prepodavatel) {
        this.prepodavatel = prepodavatel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prepodavatelDisciplinaPK != null ? prepodavatelDisciplinaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrepodavatelDisciplina)) {
            return false;
        }
        PrepodavatelDisciplina other = (PrepodavatelDisciplina) object;
        if ((this.prepodavatelDisciplinaPK == null && other.prepodavatelDisciplinaPK != null) || (this.prepodavatelDisciplinaPK != null && !this.prepodavatelDisciplinaPK.equals(other.prepodavatelDisciplinaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrepodavatelDisciplina[ prepodavatelDisciplinaPK=" + prepodavatelDisciplinaPK + " ]";
    }

}
