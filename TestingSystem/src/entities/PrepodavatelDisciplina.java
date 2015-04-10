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
@Table(name = "prepodavatel_disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrepodavatelDisciplina.findAll", 
            query = "SELECT p FROM PrepodavatelDisciplina p"),
    @NamedQuery(name = "PrepodavatelDisciplina.findByIdPrepodavatelDisciplina", 
            query = "SELECT p FROM PrepodavatelDisciplina p "
                    + "WHERE p.idPrepodavatelDisciplina = :idPrepodavatelDisciplina")})
public class PrepodavatelDisciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PREPODAVATEL_DISCIPLINA")
    private Integer idPrepodavatelDisciplina;
    @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA", 
            referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne(optional = false)
    private Disciplina disciplinaIdDisciplina;
    @JoinColumn(name = "PREPODAVATEL_ID_PREPODAVATEL", 
            referencedColumnName = "ID_PREPODAVATEL")
    @ManyToOne(optional = false)
    private Prepodavatel prepodavatelIdPrepodavatel;

    public PrepodavatelDisciplina() {
    }

    public PrepodavatelDisciplina(Integer idPrepodavatelDisciplina) {
        this.idPrepodavatelDisciplina = idPrepodavatelDisciplina;
    }

    public Integer getIdPrepodavatelDisciplina() {
        return idPrepodavatelDisciplina;
    }

    public void setIdPrepodavatelDisciplina(Integer idPrepodavatelDisciplina) {
        this.idPrepodavatelDisciplina = idPrepodavatelDisciplina;
    }

    public Disciplina getDisciplinaIdDisciplina() {
        return disciplinaIdDisciplina;
    }

    public void setDisciplinaIdDisciplina(Disciplina disciplinaIdDisciplina) {
        this.disciplinaIdDisciplina = disciplinaIdDisciplina;
    }

    public Prepodavatel getPrepodavatelIdPrepodavatel() {
        return prepodavatelIdPrepodavatel;
    }

    public void setPrepodavatelIdPrepodavatel(Prepodavatel prepodavatelIdPrepodavatel) {
        this.prepodavatelIdPrepodavatel = prepodavatelIdPrepodavatel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrepodavatelDisciplina != null ? idPrepodavatelDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrepodavatelDisciplina)) {
            return false;
        }
        PrepodavatelDisciplina other = (PrepodavatelDisciplina) object;
        if ((this.idPrepodavatelDisciplina == null 
                && other.idPrepodavatelDisciplina != null) 
                || (this.idPrepodavatelDisciplina != null 
                && !this.idPrepodavatelDisciplina.equals(
                        other.idPrepodavatelDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrepodavatelDisciplina[ idPrepodavatelDisciplina=" 
                + idPrepodavatelDisciplina + " ]";
    }

}
