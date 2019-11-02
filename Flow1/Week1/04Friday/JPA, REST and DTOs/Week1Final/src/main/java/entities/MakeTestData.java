/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class MakeTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("Martin", "Frederiksen", "12345678", 500, 1, "Info"));
            em.persist(new BankCustomer("Andreas", "Vikke", "22345678", 50000, 2, "Info"));
            em.persist(new BankCustomer("William", "Huusfeldt", "32345678", 100, 1, "Info"));
            em.persist(new BankCustomer("Asger", "Sørensen", "42345678", 0, 100, "Info"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
