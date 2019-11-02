import React, { useState, useEffect } from 'react';
import './App.css';

function DisplayTime() {
  const[time, setTime] = useState(new Date().toLocaleTimeString('en-US'));

  useEffect(() => {
    const interval = setInterval(() => {
      setTime(new Date().toLocaleTimeString('en-US'))
    }, 1000);
    return () => clearInterval(interval);
  }, []);
  return <p>Time is: {time}</p>
}

function App() {
  return (
    <div>
      <DisplayTime />
    </div>
  );
}

export default App;