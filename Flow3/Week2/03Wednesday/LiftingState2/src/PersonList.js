import React from 'react';
import Table from 'react-bootstrap/Table'

const PersonList = ({ persons, deletePerson, editPerson }) => {
  return (
    <React.Fragment>
      <h4>All Person</h4>
      <Table bordered hover size="sm">
        <thead>
          <tr>
            <td>Id</td>
            <td>Name</td>
          </tr>
        </thead>
        <tbody>
          {persons.map(p => (
            <tr key={p.id}>
              <td>{p.id}</td>
              <td>{p.name}</td>
              <td>
                <a href="#/" onClick= {()=>deletePerson(p.id)}>delete</a> 
                  </td>
                  <td>
                <a href="#/" onClick= {()=>editPerson(p.id)}>edit</a>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </React.Fragment>
  );
};
export default PersonList;