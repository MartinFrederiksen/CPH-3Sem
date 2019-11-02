import React from 'react';
import './App.css';
import p, {males, females} from "./file2";

//Opg 2, 3, 4
const person = p;
const {firstName, email} = person;

//Opg 5, 6
const combined = [...males, ...females]
const combinedAdded = [...males, firstName, "Helle", ...females, "Tina"]

//Opg 7
const personV2 = {...person, friends: combined, phone: 123456};

function App() {
    console.log(person)
    console.log(personV2)
    console.log(combined);
    console.log(combinedAdded);
    return(
    <p>{firstName}, {email}</p>
    );
    
};

export default App;