package facades;

import entities.Address;
import utils.EMF_Creator;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    static List<Person> persons = new ArrayList();

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/Person_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);

        persons.add(new Person("Martin", "Frederiksen", "12345678", new Address("Hejvej", "0000", "Compland")));
        persons.add(new Person("Andreas", "Vikke", "87654321", new Address("Hejvej", "0001", "Compland")));
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            for (Person p : persons) {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetPerson() throws PersonNotFoundException {
        Person result = facade.getPerson(persons.get(0).getId());
        assertNotNull(result);
        assertEquals("Martin", result.getFirstName());
    }

    @Test
    public void testGetAllPersons() {
        List<Person> result = facade.getAllPersons();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testAddPerson() throws Throwable {
        Person result = facade.addPerson("test", "test", "test", new Address("Hejvej", "0000", "Compland"));
        assertNotNull(result);
        assertEquals("test", result.getFirstName());

    }

    @Test
    public void testDeletePerson() throws PersonNotFoundException {
        Person result = facade.deletePerson(persons.get(1).getId());
        assertNotNull(result);
        assertEquals("Andreas", result.getFirstName());
    }

    @Test
    public void testEditPerson() throws Throwable {
        EntityManager em = emf.createEntityManager();
        
        Person test = em.find(Person.class, persons.get(0).getId());
        test.setFirstName("Edited");
        
        Person p = facade.editPerson(test);
        test = em.find(Person.class, p.getId());
        
        assertNotNull(test);
        assertEquals(test, p);
    }
    
    @Test
    public void testEditPersonError() throws Throwable {
        EntityManager em = emf.createEntityManager();
        
        Person test = em.find(Person.class, persons.get(0).getId());
        test.setFirstName("Edited");
        
        Person p = facade.editPerson(test);
        test = em.find(Person.class, p.getId());
        
        assertNotNull(test);
        assertEquals(test, p);
    }
}
