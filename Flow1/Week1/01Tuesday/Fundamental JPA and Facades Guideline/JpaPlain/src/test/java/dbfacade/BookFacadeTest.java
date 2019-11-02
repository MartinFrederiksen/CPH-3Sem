/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe
 */
public class BookFacadeTest {
    
    public BookFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBookFacade method, of class BookFacade.
     */
    @Test
    public void testGetBookFacade() {
        System.out.println("getBookFacade");
        EntityManagerFactory _emf = null;
        BookFacade expResult = null;
        BookFacade result = BookFacade.getBookFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBook method, of class BookFacade.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        String author = "";
        BookFacade instance = null;
        Book expResult = null;
        Book result = instance.addBook(author);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBook method, of class BookFacade.
     */
    @Test
    public void testFindBook() {
        System.out.println("findBook");
        int id = 0;
        BookFacade instance = null;
        Book expResult = null;
        Book result = instance.findBook(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllBooks method, of class BookFacade.
     */
    @Test
    public void testFindAllBooks() {
        System.out.println("findAllBooks");
        BookFacade instance = null;
        List<Book> expResult = null;
        List<Book> result = instance.findAllBooks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class BookFacade.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        BookFacade.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
