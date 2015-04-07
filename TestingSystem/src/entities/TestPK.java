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
public class TestPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_TEST")
    private int idTest;
    @Basic(optional = false)
    @Column(name = "STATUS_TESTA_ID_ STATUS_TESTA")
    private int statusTestaIdStatusTesta;

    public TestPK() {
    }

    public TestPK(int idTest, int statusTestaIdStatusTesta) {
        this.idTest = idTest;
        this.statusTestaIdStatusTesta = statusTestaIdStatusTesta;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getStatusTestaIdStatusTesta() {
        return statusTestaIdStatusTesta;
    }

    public void setStatusTestaIdStatusTesta(int statusTestaIdStatusTesta) {
        this.statusTestaIdStatusTesta = statusTestaIdStatusTesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTest;
        hash += (int) statusTestaIdStatusTesta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestPK)) {
            return false;
        }
        TestPK other = (TestPK) object;
        if (this.idTest != other.idTest) {
            return false;
        }
        if (this.statusTestaIdStatusTesta != other.statusTestaIdStatusTesta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TestPK[ idTest=" + idTest + ", statusTestaIdStatusTesta=" + statusTestaIdStatusTesta + " ]";
    }

}
