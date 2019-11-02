/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class CustomerTest {
    public static void main(String[] args) {
        Customer c = new Customer("Martin", "Frederiksen");
        c.addHobby("WoW");
        c.addHobby("CSGO");
        c.addHobby("Coding");
        
        c.addPhone("88888888", "Peter!");
        c.addPhone("12345678", "This is not a test number!");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        System.out.println(c.getHobbies());
        System.out.println(c.getPhoneDescription("12345678"));
    }
}
