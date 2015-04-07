package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Solovenko
 */
@Entity
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.testPK.idTest = :idTest"),
    @NamedQuery(name = "Test.findByNazvanie", query = "SELECT t FROM Test t WHERE t.nazvanie = :nazvanie"),
    @NamedQuery(name = "Test.findByVremyaProhozhdeniya", query = "SELECT t FROM Test t WHERE t.vremyaProhozhdeniya = :vremyaProhozhdeniya"),
    @NamedQuery(name = "Test.findByStatusTestaIdStatusTesta", query = "SELECT t FROM Test t WHERE t.testPK.statusTestaIdStatusTesta = :statusTestaIdStatusTesta")})
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TestPK testPK;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @Column(name = "VREMYA_PROHOZHDENIYA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremyaProhozhdeniya;
    @JoinColumn(name = "STATUS_TESTA_ID_ STATUS_TESTA", referencedColumnName = "ID_ STATUS_TESTA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StatusTesta statusTesta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<StudentTest> studentTestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<TestVopros> testVoprosList;

    public Test() {
    }

    public Test(TestPK testPK) {
        this.testPK = testPK;
    }

    public Test(int idTest, int statusTestaIdStatusTesta) {
        this.testPK = new TestPK(idTest, statusTestaIdStatusTesta);
    }

    public TestPK getTestPK() {
        return testPK;
    }

    public void setTestPK(TestPK testPK) {
        this.testPK = testPK;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public Date getVremyaProhozhdeniya() {
        return vremyaProhozhdeniya;
    }

    public void setVremyaProhozhdeniya(Date vremyaProhozhdeniya) {
        this.vremyaProhozhdeniya = vremyaProhozhdeniya;
    }

    public StatusTesta getStatusTesta() {
        return statusTesta;
    }

    public void setStatusTesta(StatusTesta statusTesta) {
        this.statusTesta = statusTesta;
    }

    @XmlTransient
    public List<StudentTest> getStudentTestList() {
        return studentTestList;
    }

    public void setStudentTestList(List<StudentTest> studentTestList) {
        this.studentTestList = studentTestList;
    }

    @XmlTransient
    public List<TestVopros> getTestVoprosList() {
        return testVoprosList;
    }

    public void setTestVoprosList(List<TestVopros> testVoprosList) {
        this.testVoprosList = testVoprosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testPK != null ? testPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testPK == null && other.testPK != null) || (this.testPK != null && !this.testPK.equals(other.testPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Test[ testPK=" + testPK + " ]";
    }

}
