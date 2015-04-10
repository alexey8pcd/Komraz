package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Solovenko
 */
@Entity
@Table(name = "student_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTest.findAll", query = "SELECT s FROM StudentTest s"),
    @NamedQuery(name = "StudentTest.findByIdStudentTest", query = "SELECT s FROM StudentTest s WHERE s.idStudentTest = :idStudentTest"),
    @NamedQuery(name = "StudentTest.findByDataProhozhdeniya", query = "SELECT s FROM StudentTest s WHERE s.dataProhozhdeniya = :dataProhozhdeniya"),
    @NamedQuery(name = "StudentTest.findByProcentBallov", query = "SELECT s FROM StudentTest s WHERE s.procentBallov = :procentBallov")})
public class StudentTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_STUDENT_TEST")
    private Integer idStudentTest;
    @Column(name = "DATA_PROHOZHDENIYA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataProhozhdeniya;
    @Column(name = "PROCENT_BALLOV")
    private Integer procentBallov;
    @JoinColumn(name = "STUDENT_ID_STUDENT", referencedColumnName = "ID_STUDENT")
    @ManyToOne(optional = false)
    private Student studentIdStudent;
    @JoinColumn(name = "TEST_ID_TEST", referencedColumnName = "ID_TEST")
    @ManyToOne(optional = false)
    private Test testIdTest;

    public StudentTest() {
    }

    public StudentTest(Integer idStudentTest) {
        this.idStudentTest = idStudentTest;
    }

    public Integer getIdStudentTest() {
        return idStudentTest;
    }

    public void setIdStudentTest(Integer idStudentTest) {
        this.idStudentTest = idStudentTest;
    }

    public Date getDataProhozhdeniya() {
        return dataProhozhdeniya;
    }

    public void setDataProhozhdeniya(Date dataProhozhdeniya) {
        this.dataProhozhdeniya = dataProhozhdeniya;
    }

    public Integer getProcentBallov() {
        return procentBallov;
    }

    public void setProcentBallov(Integer procentBallov) {
        this.procentBallov = procentBallov;
    }

    public Student getStudentIdStudent() {
        return studentIdStudent;
    }

    public void setStudentIdStudent(Student studentIdStudent) {
        this.studentIdStudent = studentIdStudent;
    }

    public Test getTestIdTest() {
        return testIdTest;
    }

    public void setTestIdTest(Test testIdTest) {
        this.testIdTest = testIdTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudentTest != null ? idStudentTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentTest)) {
            return false;
        }
        StudentTest other = (StudentTest) object;
        if ((this.idStudentTest == null && other.idStudentTest != null) || (this.idStudentTest != null && !this.idStudentTest.equals(other.idStudentTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentTest[ idStudentTest=" + idStudentTest + " ]";
    }

}
