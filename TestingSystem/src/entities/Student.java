package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Solovenko
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByIdStudent", query = "SELECT s FROM Student s WHERE s.studentPK.idStudent = :idStudent"),
    @NamedQuery(name = "Student.findByFio", query = "SELECT s FROM Student s WHERE s.fio = :fio"),
    @NamedQuery(name = "Student.findByLogin", query = "SELECT s FROM Student s WHERE s.login = :login"),
    @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password"),
    @NamedQuery(name = "Student.findByGruppaIdGruppa", query = "SELECT s FROM Student s WHERE s.studentPK.gruppaIdGruppa = :gruppaIdGruppa")})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentPK studentPK;
    @Column(name = "FIO")
    private String fio;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "GRUPPA_ID_GRUPPA", referencedColumnName = "ID_GRUPPA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Gruppa gruppa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<StudentTest> studentTestList;

    public Student() {
    }

    public Student(StudentPK studentPK) {
        this.studentPK = studentPK;
    }

    public Student(int idStudent, int gruppaIdGruppa) {
        this.studentPK = new StudentPK(idStudent, gruppaIdGruppa);
    }

    public StudentPK getStudentPK() {
        return studentPK;
    }

    public void setStudentPK(StudentPK studentPK) {
        this.studentPK = studentPK;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gruppa getGruppa() {
        return gruppa;
    }

    public void setGruppa(Gruppa gruppa) {
        this.gruppa = gruppa;
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
        hash += (studentPK != null ? studentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentPK == null && other.studentPK != null) || (this.studentPK != null && !this.studentPK.equals(other.studentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Student[ studentPK=" + studentPK + " ]";
    }

}
