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
public class StudentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_STUDENT")
    private int idStudent;
    @Basic(optional = false)
    @Column(name = "GRUPPA_ID_GRUPPA")
    private int gruppaIdGruppa;

    public StudentPK() {
    }

    public StudentPK(int idStudent, int gruppaIdGruppa) {
        this.idStudent = idStudent;
        this.gruppaIdGruppa = gruppaIdGruppa;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getGruppaIdGruppa() {
        return gruppaIdGruppa;
    }

    public void setGruppaIdGruppa(int gruppaIdGruppa) {
        this.gruppaIdGruppa = gruppaIdGruppa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idStudent;
        hash += (int) gruppaIdGruppa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentPK)) {
            return false;
        }
        StudentPK other = (StudentPK) object;
        if (this.idStudent != other.idStudent) {
            return false;
        }
        if (this.gruppaIdGruppa != other.gruppaIdGruppa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentPK[ idStudent=" + idStudent + ", gruppaIdGruppa=" + gruppaIdGruppa + " ]";
    }

}
