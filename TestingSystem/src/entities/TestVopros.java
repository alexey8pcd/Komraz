package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "test-vopros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestVopros.findAll", query = "SELECT t FROM TestVopros t"),
    @NamedQuery(name = "TestVopros.findByIdTestVopros", query = "SELECT t FROM TestVopros t WHERE t.testVoprosPK.idTestVopros = :idTestVopros"),
    @NamedQuery(name = "TestVopros.findByTestIdTest", query = "SELECT t FROM TestVopros t WHERE t.testVoprosPK.testIdTest = :testIdTest"),
    @NamedQuery(name = "TestVopros.findByTestStatusTestaIdStatusTesta", query = "SELECT t FROM TestVopros t WHERE t.testVoprosPK.testStatusTestaIdStatusTesta = :testStatusTestaIdStatusTesta"),
    @NamedQuery(name = "TestVopros.findByVoprosIdVopros", query = "SELECT t FROM TestVopros t WHERE t.testVoprosPK.voprosIdVopros = :voprosIdVopros")})
public class TestVopros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TestVoprosPK testVoprosPK;
    @JoinColumns({
        @JoinColumn(name = "TEST_ID_TEST", referencedColumnName = "ID_TEST", insertable = false, updatable = false),
        @JoinColumn(name = "TEST_STATUS_TESTA_ID_ STATUS_TESTA", referencedColumnName = "STATUS_TESTA_ID_ STATUS_TESTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Test test;
    @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vopros vopros;

    public TestVopros() {
    }

    public TestVopros(TestVoprosPK testVoprosPK) {
        this.testVoprosPK = testVoprosPK;
    }

    public TestVopros(int idTestVopros, int testIdTest, int testStatusTestaIdStatusTesta, int voprosIdVopros) {
        this.testVoprosPK = new TestVoprosPK(idTestVopros, testIdTest, testStatusTestaIdStatusTesta, voprosIdVopros);
    }

    public TestVoprosPK getTestVoprosPK() {
        return testVoprosPK;
    }

    public void setTestVoprosPK(TestVoprosPK testVoprosPK) {
        this.testVoprosPK = testVoprosPK;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Vopros getVopros() {
        return vopros;
    }

    public void setVopros(Vopros vopros) {
        this.vopros = vopros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testVoprosPK != null ? testVoprosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestVopros)) {
            return false;
        }
        TestVopros other = (TestVopros) object;
        if ((this.testVoprosPK == null && other.testVoprosPK != null) || (this.testVoprosPK != null && !this.testVoprosPK.equals(other.testVoprosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TestVopros[ testVoprosPK=" + testVoprosPK + " ]";
    }

}
