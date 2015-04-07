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
public class StudentTestPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_STUDENT-TEST")
    private int idStudentTest;
    @Basic(optional = false)
    @Column(name = "STUDENT_ID_STUDENT")
    private int studentIdStudent;
    @Basic(optional = false)
    @Column(name = "STUDENT_GRUPPA_ID_GRUPPA")
    private int studentGruppaIdGruppa;
    @Basic(optional = false)
    @Column(name = "TEST_ID_TEST")
    private int testIdTest;

    public StudentTestPK() {
    }

    public StudentTestPK(int idStudentTest, int studentIdStudent, int studentGruppaIdGruppa, int testIdTest) {
        this.idStudentTest = idStudentTest;
        this.studentIdStudent = studentIdStudent;
        this.studentGruppaIdGruppa = studentGruppaIdGruppa;
        this.testIdTest = testIdTest;
    }

    public int getIdStudentTest() {
        return idStudentTest;
    }

    public void setIdStudentTest(int idStudentTest) {
        this.idStudentTest = idStudentTest;
    }

    public int getStudentIdStudent() {
        return studentIdStudent;
    }

    public void setStudentIdStudent(int studentIdStudent) {
        this.studentIdStudent = studentIdStudent;
    }

    public int getStudentGruppaIdGruppa() {
        return studentGruppaIdGruppa;
    }

    public void setStudentGruppaIdGruppa(int studentGruppaIdGruppa) {
        this.studentGruppaIdGruppa = studentGruppaIdGruppa;
    }

    public int getTestIdTest() {
        return testIdTest;
    }

    public void setTestIdTest(int testIdTest) {
        this.testIdTest = testIdTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idStudentTest;
        hash += (int) studentIdStudent;
        hash += (int) studentGruppaIdGruppa;
        hash += (int) testIdTest;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentTestPK)) {
            return false;
        }
        StudentTestPK other = (StudentTestPK) object;
        if (this.idStudentTest != other.idStudentTest) {
            return false;
        }
        if (this.studentIdStudent != other.studentIdStudent) {
            return false;
        }
        if (this.studentGruppaIdGruppa != other.studentGruppaIdGruppa) {
            return false;
        }
        if (this.testIdTest != other.testIdTest) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentTestPK[ idStudentTest=" + idStudentTest + ", studentIdStudent=" + studentIdStudent + ", studentGruppaIdGruppa=" + studentGruppaIdGruppa + ", testIdTest=" + testIdTest + " ]";
    }

}
