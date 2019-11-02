import React, { useState, useEffect } from "react";
import './App.css';

export default function SearchResults() {
  const [joke, setJoke] = useState({ value: "" });
  const [chuckSelector, setChuckSelector] = useState(false);

  const [dadJoke, setDadJoke] = useState({ joke: "" });

  useEffect(() => {
    function fetchChuckNorris() {
      fetch('https://api.chucknorris.io/jokes/random')
      .then(res=>res.json())
      .then(joke =>  {setJoke(joke)})  
      .catch(err => console.log("Ups Chuck Error"+err))
    }
    fetchChuckNorris();

    const interval = setInterval(() => {
      fetch('https://icanhazdadjoke.com/', {
        headers: new Headers({
          "Accept" : "application/json"
        })
      })
      .then(res=>res.json())
      .then(dadJoke=>{setDadJoke(dadJoke)})
      .catch(err=>console.log("Dad Error"+err))
    }, 10000)
    return () => clearInterval(interval);
  }, [chuckSelector]);

  
  return (
    <div>
      <p>Chuck Joke: {joke.value} </p>
      <button onClick={()=>setChuckSelector(!chuckSelector)}>Requires accompanying parent or adult guardian</button>
    
      <p>Dad Joke: {dadJoke.joke}</p>
    </div>
  );
}