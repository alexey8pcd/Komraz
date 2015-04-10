package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.idTest = :idTest"),
    @NamedQuery(name = "Test.findByNazvanie", query = "SELECT t FROM Test t WHERE t.nazvanie = :nazvanie"),
    @NamedQuery(name = "Test.findByVremyaProhozhdeniya", query = "SELECT t FROM Test t WHERE t.vremyaProhozhdeniya = :vremyaProhozhdeniya")})
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TEST")
    private Integer idTest;
    @Column(name = "NAZVANIE")
    private String nazvanie;
    @Column(name = "VREMYA_PROHOZHDENIYA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremyaProhozhdeniya;
    @JoinColumn(name = "STATUS_TESTA_ID_STATUS_TESTA", referencedColumnName = "ID_STATUS_TESTA")
    @ManyToOne(optional = false)
    private StatusTesta statusTestaIdStatusTesta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testIdTest")
    private List<TestVopros> testVoprosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testIdTest")
    private List<StudentTest> studentTestList;

    public Test() {
    }

    public Test(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
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

    public StatusTesta getStatusTestaIdStatusTesta() {
        return statusTestaIdStatusTesta;
    }

    public void setStatusTestaIdStatusTesta(StatusTesta statusTestaIdStatusTesta) {
        this.statusTestaIdStatusTesta = statusTestaIdStatusTesta;
    }

    @XmlTransient
    public List<TestVopros> getTestVoprosList() {
        return testVoprosList;
    }

    public void setTestVoprosList(List<TestVopros> testVoprosList) {
        this.testVoprosList = testVoprosList;
    }

    @XmlTransient
    public List<StudentTest> getStudentTestList() {
        return studentTestList;
    }

    public void setStudentTestList(List<StudentTest> studentTestList) {
        this.studentTestList = studentTestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Test[ idTest=" + idTest + " ]";
    }

}
