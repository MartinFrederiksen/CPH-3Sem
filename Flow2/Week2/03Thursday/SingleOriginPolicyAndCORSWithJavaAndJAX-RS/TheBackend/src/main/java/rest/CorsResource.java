package rest;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Joe
 */
class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

@Path("cors")
public class CorsResource {

    public static Map<Integer, Person> persons = new HashMap();
    public Gson gson = new Gson();

    public CorsResource() {
        if (persons.isEmpty()) {
            persons.put(0, new Person("Peter pan"));
            persons.put(1, new Person("Peter plys"));
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        return Response.ok(gson.toJson(persons.values())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSinglePerson(@PathParam("id") int id) {
        return Response.ok(gson.toJson(persons.get(id))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSinglePerson(String person) {
        Person p = gson.fromJson(person, Person.class);
        persons.put(persons.size(), p);
        return Response.ok(gson.toJson(p)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editSinglePerson(@PathParam("id") int id, String person) {
        persons.get(id).name = gson.fromJson(person, Person.class).name;
        return Response.ok(gson.toJson(persons.get(id))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeSinglePerson(@PathParam("id") int id) {
        persons.remove(id);
        return Response.ok("{\"msg\" : \"Succesfully removed Person with id " + id + "\"}").build();
    }
}
