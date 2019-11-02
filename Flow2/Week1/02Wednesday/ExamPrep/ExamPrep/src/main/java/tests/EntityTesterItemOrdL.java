package tests;

import entities.Customer;
import entities.ItemType;
import entities.OrderEnt;
import entities.OrderLine;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class EntityTesterItemOrdL {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        ItemType it1 = new ItemType("Øl", "Dette er den bedste drik", 1500);
        ItemType it2 = new ItemType("Whisky", "2cl", 3500);

        OrderLine ol1 = new OrderLine(6);
        OrderLine ol2 = new OrderLine(1);
        ol1.setItemType(it1);
        ol2.setItemType(it2);

        em.getTransaction().begin();
        em.persist(ol1);
        em.persist(ol2);
        em.getTransaction().commit();
    }

}
