package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("BankCustomer")
public class BankCustomerResource {

    private BankCustomerFacade bcf;

    public BankCustomerResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        bcf = BankCustomerFacade.getBankCustomerFacade(emf);
    }

    @GET
    @Path("/databaseData")
    public String databaseData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        /*em.getTransaction().begin();
        em.persist(new Employee("Martin Frederiksen", "Hejvej 12", 123450));
        em.persist(new Employee("Andreas Vikke", "Bonbonland 12", 123450));
        em.persist(new Employee("William Huusfeldt", "Istedgade 30", 100));
        em.persist(new Employee("Asger Sørensen", "Klamydiastrup 88", 0));
        em.getTransaction().commit();
        em.close();
        emf.close();*/
        List<BankCustomer> bankcustomers = new ArrayList();
        bankcustomers.add(new BankCustomer("Martin", "Frederiksen", "12345678", 500, 1, "Info"));
        bankcustomers.add(new BankCustomer("Andreas", "Vikke", "22345678", 50000, 2, "Info"));
        bankcustomers.add(new BankCustomer("William", "Huusfeldt", "32345678", 100, 1, "Info"));
        bankcustomers.add(new BankCustomer("Asger", "Sørensen", "42345678", 0, 100, "Info"));

        for (BankCustomer bc : bankcustomers) {
            em.getTransaction().begin();
            em.persist(bc);
            em.getTransaction().commit();
        }

        em.close();
        emf.close();

        return "Data added";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") int id) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(bcf.getCustomerById(id));
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(bcf.getAll());
    }

}
