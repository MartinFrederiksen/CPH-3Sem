package test;

import entities.Address;
import entities.Customer;
import facade.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class FacadeTest {
    
    public static void main(String[] args) {
        CustomerFacade cm = new CustomerFacade(Persistence.createEntityManagerFactory("pu"));
        
        List<Address> addresses = new ArrayList();
        addresses.add(new Address("Skodsborgvej", "2850"));
        addresses.add(new Address("BallerupBitch","2750"));
             
        Customer c1 = new Customer("Martin", "Frederiksen");
        Customer c2 = new Customer("Andreas", "Vikke");
        Customer c3 = new Customer("testCust", "testCust");
        
        c1.setAddress(addresses);
        c2.setAddress(addresses);
        c3.setAddress(addresses);
        
        cm.addCustomer(c1);
        cm.addCustomer(c2);
        cm.addCustomer(c3);
        System.out.println(cm.getCustomers().size());
        System.out.println(cm.getCustomer(1).getFirstName());
        
        cm.deleteCustomer(1);
        c3.setLastName("editTest");
        cm.editCustomer(c3);
    }
}
