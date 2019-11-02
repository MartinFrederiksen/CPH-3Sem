import React, {useState} from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import PersonList from "./PersonList"
import NewPerson from "./NewPerson"
import uuid from "uuid/v1";

function App() {
  const initialData = [
    {id: uuid(), name: "Kurt"},
    {id: uuid(), name: "Frank"},
    {id: uuid(), name: "Carsten"} 
  ]
  const [persons, setPersons] = useState(initialData);
  console.log(persons);
  const [newPerson, setNewPerson] = useState({id: "", name: ""});

  const addPerson = person => {
    if(person.id === "") {
      person.id = uuid();
      persons.push(person);
    } else {
      persons.find(p => p.id === person.id).name = person.name;
    }

    setPersons([...persons]);
    setNewPerson({id: "", name: ""});
  }

  const deletePerson = (id) => {
    setPersons(persons.filter( person => person.id !== id));
  }
  const editPerson = (id) => {
    const p = persons.find( person => person.id === id);
    setNewPerson({id: p.id, name: p.name});
  }



  return (
    <div className="container outer">
      <h2 style={{ textAlign: "center", marginBottom:25 }}>
        State Lift Demo
      </h2>
      <h4 style={{marginBottom:25 }}>
        Total Persons: {persons.length}
      </h4>
      <div className="row">
        <div className="col-6 allTodos">
        <PersonList persons = {persons} deletePerson = {deletePerson} editPerson = {editPerson}/>
        </div>
        <div className="col-5 new-todo">
        <NewPerson nextPerson={newPerson} addPerson={addPerson}/>
        </div>
      </div>
    </div>
  );
}

export default App;
