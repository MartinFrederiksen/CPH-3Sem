import React, {useState, useEffect} from 'react';
import CountryTable from "./CountryTable";
import './App.css';

const App = ({factory}) => {
    const [labels, setLabels] = useState([]);
    const [country, setCountry] = useState([]);

    /*function getLabels() {
      countryFacade.getLabels().then(data => setLabels(data));
    } */

    useEffect(() => {
      factory.getLabels().then( l => setLabels(l));
      factory.getCountries().then( c => setCountry(c));
      const interval = setInterval(() => {
      factory.getLabels().then( l => setLabels(l));
      factory.getCountries().then( c => setCountry(c));
      }, 3000);
      return () => clearInterval(interval);
    }, []);

    return (
      <div>
        <div className="App-header">
          <h2>React, State, Fetch</h2>
        </div>
        <div className="App-intro">
          <p>Your initial task is to fetch data from the server (see exercise for how to start it),
           and create a table below, with these data</p>          
          <CountryTable labels={labels} country={country}/>
        </div>
      </div>
    );
};


export default App;
