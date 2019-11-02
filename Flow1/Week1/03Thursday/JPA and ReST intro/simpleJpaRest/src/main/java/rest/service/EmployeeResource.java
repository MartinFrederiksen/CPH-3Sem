package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
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

@Path("employee")
public class EmployeeResource {

    private EmployeeFacade ef;

    public EmployeeResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        ef = EmployeeFacade.getEmployeeFacade(emf);
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
        List<Employee> employees = new ArrayList();
        employees.add(new Employee("Martin Frederiksen", "Hejvej 12", 123450));
        employees.add(new Employee("Andreas Vikke", "Bonbonland 12", 123450));
        employees.add(new Employee("William Huusfeldt", "Istedgade 30", 100));
        employees.add(new Employee("Asger Sørensen", "Klamydiastrup 88", 0));

        for (Employee e : employees) {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        }

        em.close();
        emf.close();

        return "Data added";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<Employee> employees = ef.getAllEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList();
        for(Employee e : employees) {
            employeesDTO.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(employeesDTO);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") Long id) {
        return new Gson().toJson(new EmployeeDTO(ef.getEmployeeById(id)));
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {
        List<Employee> employees = ef.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeesDTO = new ArrayList();
        for(Employee e : employees) {
            employeesDTO.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(employeesDTO);
    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getByName(@PathParam("name") String name) {
        List<Employee> employees = ef.getEmployeesByName(name);
        List<EmployeeDTO> employeesDTO = new ArrayList();
        for(Employee e : employees) {
            employeesDTO.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(employeesDTO);
    }
}
