package facades;

import entities.Customer;
import entities.ItemType;
import entities.OrderEnt;
import entities.OrderLine;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public Customer createCustomer(Customer c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    public Customer getCustomer(long id) {
        return getEntityManager().find(Customer.class, id);
    }

    public List<Customer> getCustomers() {
        return getEntityManager().createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public ItemType createItemType(ItemType it) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
            return it;
        } finally {
            em.close();
        }
    }
    
    public ItemType getItemType(long id){
        return getEntityManager().find(ItemType.class, id);
    }
    
    public OrderEnt createOrder(OrderEnt o, Customer c){
        EntityManager em = getEntityManager();
        o.setCustomer(c);
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }
    
    public OrderEnt createOrderLine(ItemType it, OrderLine ol, OrderEnt o){
        EntityManager em = getEntityManager();
        ol.setItemType(it);
        ol.setOrderEnt(o);
        List<OrderLine> orderlines = new ArrayList();
        orderlines.add(ol);
        o.setOrderLines(orderlines);
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }
    
    public List<OrderEnt> getAllOrdersForCust(long id){
        return getEntityManager().createQuery("SELECT o FROM OrderEnt o WHERE o.customer.id = :id", OrderEnt.class).setParameter("id", id).getResultList();
    }
    
    public int getTotalPriceOfOrder(long id){
        int price = 0;
        List<OrderLine> orderLines = getEntityManager().createQuery("SELECT ol FROM OrderLine ol WHERE ol.orderEnt.orderId = :id", OrderLine.class).setParameter("id", id).getResultList();
        for(OrderLine ol : orderLines) {
            price += (ol.getItemType().getPrice() * ol.getQuantity());
        }
        return price/100;
    }
}
