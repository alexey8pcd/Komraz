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
@Table(name = "test_vopros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestVopros.findAll", query = "SELECT t FROM TestVopros t"),
    @NamedQuery(name = "TestVopros.findByIdTestVopros", query = "SELECT t FROM TestVopros t WHERE t.idTestVopros = :idTestVopros")})
public class TestVopros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TEST_VOPROS")
    private Integer idTestVopros;
    @JoinColumn(name = "TEST_ID_TEST", referencedColumnName = "ID_TEST")
    @ManyToOne(optional = false)
    private Test testIdTest;
    @JoinColumn(name = "VOPROS_ID_VOPROS", referencedColumnName = "ID_VOPROS")
    @ManyToOne(optional = false)
    private Vopros voprosIdVopros;

    public TestVopros() {
    }

    public TestVopros(Integer idTestVopros) {
        this.idTestVopros = idTestVopros;
    }

    public Integer getIdTestVopros() {
        return idTestVopros;
    }

    public void setIdTestVopros(Integer idTestVopros) {
        this.idTestVopros = idTestVopros;
    }

    public Test getTestIdTest() {
        return testIdTest;
    }

    public void setTestIdTest(Test testIdTest) {
        this.testIdTest = testIdTest;
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
        hash += (idTestVopros != null ? idTestVopros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestVopros)) {
            return false;
        }
        TestVopros other = (TestVopros) object;
        if ((this.idTestVopros == null && other.idTestVopros != null) || (this.idTestVopros != null && !this.idTestVopros.equals(other.idTestVopros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TestVopros[ idTestVopros=" + idTestVopros + " ]";
    }

}
