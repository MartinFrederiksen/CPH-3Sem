package facades;

import entities.Address;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(String fName, String lName, String phone, Address address) throws MissingInputException {
        EntityManager em = getEntityManager();
        if(fName == null || fName.isEmpty() || lName == null || lName.isEmpty())
            throw new MissingInputException("First Name and/or Last Name is missing");
        try {
            List<Address> add = em.createQuery("SELECT add FROM Address add WHERE add.street = :street AND add.zip = :zip AND add.city = :city")
                    .setParameter("street", address.getStreet())
                    .setParameter("zip", address.getZip())
                    .setParameter("city", address.getCity()).getResultList();
            Person p;
            if (add.size() > 0) {
                p = new Person(fName, lName, phone, add.get(0));
            } else {
                p = new Person(fName, lName, phone, address);
            }
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null)
                throw new PersonNotFoundException("Could not delete, provided id does not exist");
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                throw new PersonNotFoundException("No person with provided id found");
            }
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        return getEntityManager().createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Override
    public Person editPerson(Person p) throws PersonNotFoundException, MissingInputException {
        EntityManager em = getEntityManager();
        if(p.getFirstName() == null || p.getFirstName().isEmpty() || p.getLastName() == null || p.getLastName().isEmpty())
            throw new MissingInputException("First Name and/or Last Name is missing");
        try {
            em.find(Person.class, p.getId());
            if(p == null)
                throw new PersonNotFoundException("No person with provided id found");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
