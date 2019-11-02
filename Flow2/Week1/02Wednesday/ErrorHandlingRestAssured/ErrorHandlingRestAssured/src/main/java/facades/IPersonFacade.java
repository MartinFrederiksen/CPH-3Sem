package facades;

import entities.Address;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.List;

public interface IPersonFacade {

    public Person addPerson(String fName, String lName, String phone, Address address) throws MissingInputException;

    public Person deletePerson(int id) throws PersonNotFoundException;

    public Person getPerson(int id) throws PersonNotFoundException;

    public List<Person> getAllPersons();

    public Person editPerson(Person p) throws MissingInputException, PersonNotFoundException;
}
