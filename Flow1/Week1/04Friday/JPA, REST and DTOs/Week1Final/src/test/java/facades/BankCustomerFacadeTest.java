/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import static facades.BankCustomerFacade.getBankCustomerFacade;
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
public class BankCustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static BankCustomerFacade bcf;

    public BankCustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("puTest");
        bcf = getBankCustomerFacade(emf);
        EntityManager em = emf.createEntityManager();

        List<BankCustomer> BankCustomers = new ArrayList();
        BankCustomers.add(new BankCustomer("Martin", "Frederiksen", "12345678", 500, 1, "Info"));
        BankCustomers.add(new BankCustomer("Andreas", "Vikke", "22345678", 50000, 2, "Info"));
        BankCustomers.add(new BankCustomer("William", "Huusfeldt", "32345678", 100, 1, "Info"));
        BankCustomers.add(new BankCustomer("Asger", "Sørensen", "42345678", 0, 100, "Info"));

        try {
            for (BankCustomer be : BankCustomers) {
                em.getTransaction().begin();
                em.persist(be);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }

    }

    /**
     * Test of getCustomerById method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomerById() {
        System.out.println("getCustomerById");
        CustomerDTO result = bcf.getCustomerById(1);
        assertNotNull(bcf);
        assertEquals("Martin Frederiksen", result.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        List<CustomerDTO> result = bcf.getCustomerByName("Andreas");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of addCustomer method, of class BankCustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer result = bcf.addCustomer(new BankCustomer("Hr", "Test", "133337", 3, 0, "TestVinder"));
        assertNotNull(result);
        assertEquals(5, result.getId().intValue());

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(BankCustomer.class, 5L));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getAll method, of class BankCustomerFacade.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<BankCustomer> result = bcf.getAll();
        assertNotNull(bcf);
        assertEquals(4, result.size());
    }

}
