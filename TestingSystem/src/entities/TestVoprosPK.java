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
public class TestVoprosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_TEST-VOPROS")
    private int idTestVopros;
    @Basic(optional = false)
    @Column(name = "TEST_ID_TEST")
    private int testIdTest;
    @Basic(optional = false)
    @Column(name = "TEST_STATUS_TESTA_ID_ STATUS_TESTA")
    private int testStatusTestaIdStatusTesta;
    @Basic(optional = false)
    @Column(name = "VOPROS_ID_VOPROS")
    private int voprosIdVopros;

    public TestVoprosPK() {
    }

    public TestVoprosPK(int idTestVopros, int testIdTest, int testStatusTestaIdStatusTesta, int voprosIdVopros) {
        this.idTestVopros = idTestVopros;
        this.testIdTest = testIdTest;
        this.testStatusTestaIdStatusTesta = testStatusTestaIdStatusTesta;
        this.voprosIdVopros = voprosIdVopros;
    }

    public int getIdTestVopros() {
        return idTestVopros;
    }

    public void setIdTestVopros(int idTestVopros) {
        this.idTestVopros = idTestVopros;
    }

    public int getTestIdTest() {
        return testIdTest;
    }

    public void setTestIdTest(int testIdTest) {
        this.testIdTest = testIdTest;
    }

    public int getTestStatusTestaIdStatusTesta() {
        return testStatusTestaIdStatusTesta;
    }

    public void setTestStatusTestaIdStatusTesta(int testStatusTestaIdStatusTesta) {
        this.testStatusTestaIdStatusTesta = testStatusTestaIdStatusTesta;
    }

    public int getVoprosIdVopros() {
        return voprosIdVopros;
    }

    public void setVoprosIdVopros(int voprosIdVopros) {
        this.voprosIdVopros = voprosIdVopros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTestVopros;
        hash += (int) testIdTest;
        hash += (int) testStatusTestaIdStatusTesta;
        hash += (int) voprosIdVopros;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestVoprosPK)) {
            return false;
        }
        TestVoprosPK other = (TestVoprosPK) object;
        if (this.idTestVopros != other.idTestVopros) {
            return false;
        }
        if (this.testIdTest != other.testIdTest) {
            return false;
        }
        if (this.testStatusTestaIdStatusTesta != other.testStatusTestaIdStatusTesta) {
            return false;
        }
        if (this.voprosIdVopros != other.voprosIdVopros) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TestVoprosPK[ idTestVopros=" + idTestVopros + ", testIdTest=" + testIdTest + ", testStatusTestaIdStatusTesta=" + testStatusTestaIdStatusTesta + ", voprosIdVopros=" + voprosIdVopros + " ]";
    }

}
