package tests;

import entities.Customer;
import entities.OrderEnt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class EntityTesterCusOrd {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Martin", "Martin@cph.dk");
        Customer c2 = new Customer("Andreas", "Andreas@cph.dk");
        
        List<OrderEnt> orders = new ArrayList();
        
        OrderEnt o1 = new OrderEnt();
        OrderEnt o2 = new OrderEnt();
        o1.setCustomer(c1);
        o2.setCustomer(c1);
        
        orders.add(o1);
        orders.add(o2);
        
        c1.setOrderEnts(orders);
        
        em.getTransaction().begin();
        em.persist(c1);
        em.getTransaction().commit();
        
    }
    
}
