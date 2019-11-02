package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/Person",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        String[] endpoints = {"/{id}(getPerson)", "/all(getAllPersons)", "/{id}(deletePerson)", "/ (addPerson)", "/{id}(editPerson)"};
        return "{\"api\":\"person\", \"endpoints\":" + GSON.toJson(endpoints) + "}";
    }

    @Path("setup")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String setupDatabase() {
        EntityManager em = EMF.createEntityManager();
        List<Person> persons = new ArrayList();
        Address address = new Address("Hejvej", "0000", "Compland");
        persons.add(new Person("Martin", "Frederiksen", "12345678", address));
        persons.add(new Person("Andreas", "Vikke", "12345679", address));
        persons.add(new Person("Asger", "Sørensen", "97654321", address));
        persons.add(new Person("William", "Huusfeldt", "87654321", address));
        address.setPersons(persons);

        em.getTransaction().begin();
        for (Person p : persons) {
            em.persist(p);
        }

        em.getTransaction().commit();

        return "{\"status\":\"completed\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersons() {
        return GSON.toJson(new PersonsDTO(FACADE.getAllPersons()));
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPerson(@PathParam("id") int id) throws Throwable {
        return Response.ok(GSON.toJson(new PersonDTO(FACADE.getPerson(id)))).build();

    }

    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePerson(@PathParam("id") int id) throws Throwable {
        FACADE.deletePerson(id);
        return Response.ok("{\"status\" : \"person with id= " + id + " was removed\"}").build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String person) throws Throwable {
        PersonDTO pDTO = GSON.fromJson(person, PersonDTO.class);
        Person p;
        p = FACADE.addPerson(pDTO.getfName(), pDTO.getlName(), pDTO.getPhone(), pDTO.getAddress());
        System.out.println(new PersonDTO(p));
        return Response.ok(GSON.toJson(new PersonDTO(p))).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String person, @PathParam("id") int id) throws Throwable {
        PersonDTO pDTO = (GSON.fromJson(person, PersonDTO.class));
        Person p = FACADE.getPerson(id);
        p.setFirstName(pDTO.getfName());
        p.setLastName(pDTO.getlName());
        p.setPhone(pDTO.getPhone());
        Person updated;
        updated = FACADE.editPerson(p);
        return Response.ok(GSON.toJson(new PersonDTO(updated))).build();

    }
}
