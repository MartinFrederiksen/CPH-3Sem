/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbfacade.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class EntityTested {
    private static CustomerFacade cf;
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        cf = CustomerFacade.getCustomerFacade(emf);
        
        System.out.println(cf.findById(1L).getFirstname());
        //cf.addCustomer("Test", "Test");
        
        //List<Customer> customers = cf.AllCustomers();
        List<Customer> customers = cf.findByLastName("Frederiksen");
        for(Customer c : customers) {
            System.out.println(c.getFirstname());
        }
        
        System.out.println(cf.getNumberOfCustomers());
        
        
        /*
        Customer c1 = new Customer("Martin", "Frederiksen");
        Customer c2 = new Customer("Martin1", "Frederiksen1");
        
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
        */
        
        
    }
}
