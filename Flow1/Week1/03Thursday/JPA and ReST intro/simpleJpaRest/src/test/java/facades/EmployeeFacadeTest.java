/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe
 */
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade ef;

    public EmployeeFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("puTest");
        ef = EmployeeFacade.getEmployeeFacade(emf);

        EntityManager em = emf.createEntityManager();
        List<Employee> employees = new ArrayList();
        employees.add(new Employee("Martin Frederiksen", "Hejvej 12", 123450));
        employees.add(new Employee("Andreas Vikke", "Bonbonland 12", 123450));
        employees.add(new Employee("William Huusfeldt", "Istedgade 30", 100));
        employees.add(new Employee("Asger Sørensen", "Klamydiastrup 88", 0));

        try {
            for (Employee e : employees) {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Test of getEmployeeById method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        Employee result = ef.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("Martin Frederiksen", result.getName());
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {
        System.out.println("getEmployeesByName");
        List<Employee> result = ef.getEmployeesByName("Andreas Vikke");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        List<Employee> result = ef.getAllEmployees();
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        List<Employee> result = ef.getEmployeesWithHighestSalary();
        assertNotNull(result);
        for (Employee e : result) {
            assertEquals(123450, e.getSalary());
            System.out.println(e.getName());
        }
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testCreateEmployee() {
        System.out.println("createEmployee");
        Employee result = ef.createEmployee("Test", "Testehaven 17", 1337);
        assertNotNull(result);

        EntityManager em1 = emf.createEntityManager();
        Employee created = em1.find(Employee.class, 5L);
        assertNotNull(created);
        assertEquals(created.getName(), result.getName());

        try {
            em1.getTransaction().begin();
            em1.remove(em1.find(Employee.class, 5L));
            em1.getTransaction().commit();
        } finally {
            em1.close();
        }
    }
}
