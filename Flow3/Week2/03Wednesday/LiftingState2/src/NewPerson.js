import React, { useState, useEffect } from "react";

function NewPerson(props) {
  const [newPerson, setNewPerson] = useState(props.nextPerson);

  const savePerson = event => {
    if (newPerson.name === "") return;
    props.addPerson(newPerson);
    event.preventDefault();
  }

  useEffect(() => setNewPerson({ ...props.nextPerson }), [props.nextPerson]);

  
  const onChange = event => {
    newPerson.name = event.target.value;
    setNewPerson({ ...newPerson });
  }

  const title = newPerson.id === "" ? "Add person" : "Edit Person";
  const btn = newPerson.id === "" ? "Add" : "Edit";
  return (
    <div>
      <h4>{title}</h4>
      <form>
        <input value={newPerson.name} onChange={onChange} />
        <br /><br />
        <button onClick={savePerson} className="btn btn-info">{btn}</button>
      </form>
      {newPerson.id !== "" && <p>Editing person name</p>}
    </div>
  );
}
export default NewPerson;