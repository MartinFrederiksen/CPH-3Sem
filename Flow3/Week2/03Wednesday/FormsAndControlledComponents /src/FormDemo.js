import React, {useState} from "react";
import './App.css';

const NameForm = () => {
  const [name, setName] = useState("");
  
  function handleChange(event) {
    const target = event.target;
    const value = target.value;
    setName(value);
  }
  
  function handleSubmit(event) {
    event.preventDefault();
    window.alert(name);
  }
 
  return (
    <div>
      <form onSubmit={handleSubmit} >
        <label>
          Name:
          <input type="text" value={name} onChange={handleChange}/>
        </label>
        <input type="submit" value="Submit" />
      </form>
      {name}
    </div>
  );
};
 
export default function FormDemo() {
  return (
    <div style={{marginTop:25}}>
      <NameForm />
    </div>
  );
}