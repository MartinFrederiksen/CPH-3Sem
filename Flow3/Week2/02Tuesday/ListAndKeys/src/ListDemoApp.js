import './App.css';
import React, { useState, useEffect } from "react";

function ListItem(props) {
  // Correct! There is no need to specify the key here:
  return <li>{props.value}</li>;
}

function RowItem(props) {
  // Correct! There is no need to specify the key here:
  return <tr><td>{props.value}</td></tr>;
}

function NumberTable({ numbers }) {
  const rowItems = numbers.map(n => <RowItem key={n} value={n} />);
  return rowItems;
}

function NumberList({ numbers }) {
  console.log("--NUMBERS -->", numbers)
  // Correct! Key should be specified inside the array.
  const listItems = numbers.map(n => <ListItem key={n} value={n} />);
  return listItems;
}
function ListDemo(props) {
  return (
    <div>
      <h2>All numbers passed in via props</h2>
      <NumberList numbers={props.numbers} />
      <table>
        <thead><tr><th>Numbers</th></tr></thead>
        <tbody><NumberTable numbers={props.numbers} /></tbody>
      </table>
    </div>
  );
}



export default function App() {
  var [numbers, setNumbers] = useState([1, 2, 3, 4]);
  var [seconds, setSeconds] = useState(4);

  
  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds(seconds++);
      numbers.push(seconds);
      //setNumbers([...numbers, seconds]]);
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  return <ListDemo numbers={numbers} />;
}
