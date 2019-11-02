package test;

import entities.Address;
import entities.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class EntityTester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Martin", "Frederiksen");
        Customer c2 = new Customer("Andreas", "Vikke");
        
        List<Address> addresses = new ArrayList();
        addresses.add(new Address("Skodsborgvej", "2850"));
        addresses.add(new Address("BallerupBitch","2750"));
        
        c1.setAddress(addresses);
        c2.setAddress(addresses);

        Persistence.generateSchema("pu", null);
        
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
            
            System.out.println(c1.getId());
            System.out.println(c2.getId());

        } finally {
            em.close();
        }
    }

}