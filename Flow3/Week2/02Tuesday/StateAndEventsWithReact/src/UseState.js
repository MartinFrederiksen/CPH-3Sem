import React, { useState, useEffect } from 'react';
import './App.css';

function ClickFunction(props) {
  const { initial, value } = props;
  const [count, setCount] = useState(parseInt(localStorage.getItem('count')) || initial);

  useEffect(() => {
    localStorage.setItem("count", count);
  });

  return <div>
    <p>{count}</p>
    <button onClick={() => setCount(count + value)}>
      Add
      </button>
    <button onClick={() => setCount(count - value)}>
      Subtract
      </button>
  </div>;
}

export default function App() {
  return <ClickFunction initial={5} value={10} />
}