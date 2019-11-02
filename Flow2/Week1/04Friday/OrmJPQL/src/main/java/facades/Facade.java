package facades;

import Entities.Semester;
import Entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mappers.StudentInfo;

/**
 *
 * @author Joe
 */
public class Facade {

    private static EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Student> getAllStudents() {
        return getEntityManager().createNamedQuery("Student.findAll").getResultList();
    }

    public List<Student> getAllStudentsByName(String firstname) {
        return getEntityManager().createNamedQuery("Student.findByFirstname").setParameter("firstname", firstname).getResultList();
    }

    public Student addStudent(Student s, String firstname, String lastname) {
        EntityManager em = getEntityManager();
        s.setFirstname(firstname);
        s.setLastname(lastname);
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public Student assignStudentToSemester(long studentId, long semesterId) {
        EntityManager em = getEntityManager();
        Student s = em.find(Student.class, studentId);
        s.setSemester(em.find(Semester.class, semesterId));
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public List<Student> getStudentByLastname(String lastname) {
        return getEntityManager().createNamedQuery("Student.findByLastname", Student.class).setParameter("lastname", lastname).getResultList();
    }
    
    public long getStudentCount(){
        return getEntityManager().createNamedQuery("Student.getCount", long.class).getSingleResult();
    }
    
    public long getStudentCountBySemName(String semesterName) {
        return getEntityManager().createNamedQuery("Student.getCountBySemName", long.class).setParameter("name", semesterName).getSingleResult();
    }
    
    public long getStudentCountAllSem() {
        return getEntityManager().createNamedQuery("Student.getCountAllSem", long.class).getSingleResult();
    }
    
    public List<StudentInfo> getStudentInfo() {
        return getEntityManager().createNamedQuery("Student.getStudentInfo", StudentInfo.class).getResultList();
    }
    
    public StudentInfo getStudentInfoById(long id) {
        return getEntityManager().createNamedQuery("Student.getStudentInfoById", StudentInfo.class).setParameter("id", id).getSingleResult();
    }
}
