/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
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
public class CustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static CustomerFacade cf;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("puTest");
        cf = CustomerFacade.getCustomerFacade(emf);

        EntityManager em = emf.createEntityManager();
        List<Customer> customers = new ArrayList();
        customers.add(new Customer("Martin", "Frederiksen"));
        customers.add(new Customer("Andreas", "Vikke"));
        customers.add(new Customer("William", "Vikke"));
        customers.add(new Customer("Asger", "Sørensen"));

        try {
            for (Customer e : customers) {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Test of findById method, of class CustomerFacade.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Customer result = cf.findById(1L);
        assertNotNull(result);
        assertEquals("Martin", result.getFirstname());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        List<Customer> result = cf.findByLastName("Vikke");
        assertNotNull(result);
        assertEquals("Andreas", result.get(0).getFirstname());
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        int result = cf.getNumberOfCustomers();
        assertNotNull(result);
        assertEquals(4, result);
    }

    /**
     * Test of AllCustomers method, of class CustomerFacade.
     */
    @Test
    public void testAllCustomers() {
        System.out.println("AllCustomers");
        List<Customer> result = cf.AllCustomers();
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        Customer result = cf.addCustomer("Anders", "And");
        assertNotNull(result);

        EntityManager em = emf.createEntityManager();
        Customer created = em.find(Customer.class, 5L);
        assertNotNull(created);
        assertEquals(created.getId(), result.getId());

        try {
            em.getTransaction().begin();
            em.remove(em.find(Customer.class, 5L));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
