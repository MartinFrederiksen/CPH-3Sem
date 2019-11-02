package tests;

import entities.Customer;
import entities.ItemType;
import entities.OrderEnt;
import entities.OrderLine;
import facades.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade(Persistence.createEntityManagerFactory("pu"));

        Customer c1 = new Customer("Martin", "Martin@cph.dk");
        Customer c2 = new Customer("Andreas", "Andreas@cph.dk");
        facade.createCustomer(c1);
        facade.createCustomer(c2);
        System.out.println(facade.getCustomer(c1.getId()).getName());
        System.out.println(facade.getCustomers());
        
        ItemType it1 = new ItemType("Øl", "33cl", 1500);
        ItemType it2 = new ItemType("Shot", "2cl", 3000);
        facade.createItemType(it1);
        facade.createItemType(it2);
        System.out.println(facade.getItemType(it1.getId()).getName());
        
        OrderEnt o1 = new OrderEnt();
        OrderEnt o2 = new OrderEnt();
        facade.createOrder(o1, c1);
        facade.createOrder(o2, c1);
        
        OrderLine ol1 = new OrderLine(6);
        OrderLine ol2 = new OrderLine(1);
        OrderLine ol3 = new OrderLine(10);
        facade.createOrderLine(it1, ol1, o1);
        facade.createOrderLine(it1, ol2, o1);
        facade.createOrderLine(it2, ol3, o1);
        
        List<OrderEnt> orders = facade.getAllOrdersForCust(c1.getId());
        for(OrderEnt o : orders) {
            System.out.println(o.getCustomer());
        }
        
        System.out.println(facade.getTotalPriceOfOrder(o1.getOrderId()) + "kr.");
    }
}
