package rest;

import com.google.gson.Gson;
import dto.PersonDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonResourceTest {

    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    //private static final String TEST_DB = "jdbc:mysql://localhost:3307/Person_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        //NOT Required if you use the version of EMF_Creator.createEntityManagerFactory used above        
        //System.setProperty("IS_TEST", TEST_DB);
        //We are using the database on the virtual Vagrant image, so username password are the same for all dev-databases
        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            p1 = new Person("Martin", "Frederiksen", "12345678", new Address("Hejvej", "0000", "Compland"));
            p2 = new Person("Andreas", "Vikke", "87654321", new Address("Hejvej", "0001", "Compland"));
            p3 = new Person("", "", "87654321", new Address("Hejvej", "0001", "Compland"));
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void testGetPersons() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("all.size()", equalTo(2));
    }

    @Test
    public void testGetPerson() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/" + p1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(p1.getFirstName()));
    }
    
    @Test
    @Disabled
    public void testGetPersonError500() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/" + p4.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR_500.getStatusCode());
    }
    
    @Test
    public void testGetPersonError() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }

    @Test
    public void testAddPerson() throws Exception {
        given()
                .contentType("application/json")
                .accept("application/json")
                .body(new Gson().toJson(new PersonDTO(p1)))
                .post("/person").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(p1.getFirstName()));
    }
    
    @Test
    public void testAddPersonError() throws Exception {
        given()
                .contentType("application/json")
                .accept("application/json")
                .body(new Gson().toJson(new PersonDTO(p3)))
                .post("/person").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }
    
    

    @Test
    public void testDeletePerson() throws Exception {
        given()
                .contentType("application/json")
                .delete("/person/" + p2.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("status", equalTo("person with id= " + p2.getId() + " was removed"));
    }
    
    @Test
    public void testDeletePersonError() throws Exception {
        given()
                .contentType("application/json")
                .delete("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());           
    }

    @Test
    public void testEditPerson() throws Exception {
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO(p1)))
                .put("/person/" + p1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", equalTo(p1.getId()));
    }
    
    @Test
    public void testEditPersonError() throws Exception {
        given()
                .contentType("application/json")
                .put("/person/0").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }
    
    @Test
    public void testEditPersonErrorMissing() throws Exception {
        given()
                .contentType("application/json")
                .body(new Gson().toJson(new PersonDTO(p3)))
                .put("/person/" + p3.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }
}
