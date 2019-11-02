/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerById(int id) {
        return new CustomerDTO(getEntityManager().find(BankCustomer.class, Long.valueOf(id)));
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        List<BankCustomer> customers = getEntityManager().createQuery("SELECT bc FROM BankCustomer bc WHERE bc.firstname = :name", BankCustomer.class).setParameter("name", name).getResultList();
        List<CustomerDTO> customerDTOs = new ArrayList();
        for (BankCustomer bc : customers) {
            customerDTOs.add(new CustomerDTO(bc));
        }
        return customerDTOs;
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();
        em.close();
        return cust;
    }

    public List<BankCustomer> getAll() {
        return getEntityManager().createQuery("SELECT bc FROM BankCustomer bc", BankCustomer.class).getResultList();
    }

}
