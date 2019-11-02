package tests;

import Entities.Semester;
import Entities.Student;
import facades.Facade;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class EntityTester {
    public static void main(String[] args) {
        Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));
        //1.
        System.out.println(f.getAllStudents());
        
        //2.
        System.out.println(f.getAllStudentsByName("anders"));
        
        //3.
        //System.out.println(f.addStudent(new Student(9L), "John", "Johnny"));
        
        //4.
        System.out.println(f.assignStudentToSemester(9L, 1L));
        
        //5.
        //Forstår ikke helt denne opgave
        
        //6.
        System.out.println(f.getStudentByLastname("and"));
        
        //7.
        System.out.println(f.getStudentCount());
        
        //8.
        System.out.println(f.getStudentCountBySemName("CLcos-v14e"));
        
        //9.
        //f.addStudent(new Student(21L), "Test", "test");
        System.out.println(f.getStudentCountAllSem());
        
        //10
        //Kan ikke gennemskue den query der skal bruges her så jeg ikke bare gør det med java men med JPQL
        
        //11
        System.out.println(f.getStudentInfo());
        
        //12
        System.out.println(f.getStudentInfoById(9L));
    }

}
