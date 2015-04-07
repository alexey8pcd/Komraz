package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "student-test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTest.findAll", query = "SELECT s FROM StudentTest s"),
    @NamedQuery(name = "StudentTest.findByIdStudentTest", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.idStudentTest = :idStudentTest"),
    @NamedQuery(name = "StudentTest.findByDataProhozhdeniya", query = "SELECT s FROM StudentTest s WHERE s.dataProhozhdeniya = :dataProhozhdeniya"),
    @NamedQuery(name = "StudentTest.findByProcentBallov", query = "SELECT s FROM StudentTest s WHERE s.procentBallov = :procentBallov"),
    @NamedQuery(name = "StudentTest.findByStudentIdStudent", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.studentIdStudent = :studentIdStudent"),
    @NamedQuery(name = "StudentTest.findByStudentGruppaIdGruppa", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.studentGruppaIdGruppa = :studentGruppaIdGruppa"),
    @NamedQuery(name = "StudentTest.findByTestIdTest", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.testIdTest = :testIdTest")})
public class StudentTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentTestPK studentTestPK;
    @Column(name = "DATA_PROHOZHDENIYA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataProhozhdeniya;
    @Column(name = "PROCENT_BALLOV")
    private Integer procentBallov;
    @JoinColumns({
        @JoinColumn(name = "STUDENT_ID_STUDENT", referencedColumnName = "ID_STUDENT", insertable = false, updatable = false),
        @JoinColumn(name = "STUDENT_GRUPPA_ID_GRUPPA", referencedColumnName = "GRUPPA_ID_GRUPPA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "TEST_ID_TEST", referencedColumnName = "ID_TEST", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Test test;

    public StudentTest() {
    }

    public StudentTest(StudentTestPK studentTestPK) {
        this.studentTestPK = studentTestPK;
    }

    public StudentTest(int idStudentTest, int studentIdStudent, int studentGruppaIdGruppa, int testIdTest) {
        this.studentTestPK = new StudentTestPK(idStudentTest, studentIdStudent, studentGruppaIdGruppa, testIdTest);
    }

    public StudentTestPK getStudentTestPK() {
        return studentTestPK;
    }

    public void setStudentTestPK(StudentTestPK studentTestPK) {
        this.studentTestPK = studentTestPK;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentTestPK != null ? studentTestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentTest)) {
            return false;
        }
        StudentTest other = (StudentTest) object;
        if ((this.studentTestPK == null && other.studentTestPK != null) || (this.studentTestPK != null && !this.studentTestPK.equals(other.studentTestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentTest[ studentTestPK=" + studentTestPK + " ]";
    }

}
