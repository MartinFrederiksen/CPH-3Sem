import React from 'react';
import './App.css';
import PropTypes from 'prop-types';
import { names } from './file2';

function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

Welcome.propTypes = {
  name: PropTypes.string.isRequired
};

Welcome.defaultProps = {
  name: "unknown"
};

function WelcomePerson(props) {
  const { firstName, lastName, email } = props.person;
  return <p>firstName: {firstName}, lastName: {lastName}, email: {email}</p>;
}

WelcomePerson.propTypes = {
  person: PropTypes.element.isRequired && PropTypes.shape({
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired
  })
};

function App() {
  return (
    <div>
      <Welcome name="Sara" />
      <Welcome />
      <Welcome name={45} />
      {names.map((x, i) => <WelcomePerson key={i} person={x} />)}
    </div>
  );
}

export default App;