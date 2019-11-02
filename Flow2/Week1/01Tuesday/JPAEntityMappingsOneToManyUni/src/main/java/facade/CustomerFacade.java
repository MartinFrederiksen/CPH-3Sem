/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Joe
 */
public class CustomerFacade {
    private EntityManagerFactory emf;

    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public Customer getCustomer(int id) {
        return getEntityManager().find(Customer.class, id);
    }
    public List<Customer> getCustomers() {
        return getEntityManager().createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }
    public Customer addCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }
    public Customer deleteCustomer(int id) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Customer cm = em.find(Customer.class, id);
            em.remove(cm);
            em.getTransaction().commit();
            return cm;
        }finally {
            em.close();
        }
    }
    public Customer editCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }
    
}
