import React from "react";
import { join } from "path";

const CountryTable = (props) => {
  const { labels, country } = props
  
  function moreThenOne(array) {
    return array.length > 1 ? array[0] + " (+" + (array.length-1) + ")" : array;
  }

  return (
    <div>
      <p>Replace the thead section with a row generated from the Labels endpoint</p>
      <p>Replace the tbody section with rows generated from the countries endpoint</p>
      <table className="table">
        <thead>
          <tr>
            {labels.map((l,i)=> <th key={i}>{l}</th>)}
          </tr>
        </thead>
        <tbody>
          {country.map((c, i) => <tr key={i}>
          <td>{c.name}</td>
          <td>{c.capital}</td>
          <td>{c.region}</td>
          <td>{c.population}</td>
          <td>{c.area}</td>
          <td>{moreThenOne(c.timezones)}</td>
          <td>{moreThenOne(c.borders)}</td>
          <td>{moreThenOne(c.topLevelDomain)}</td>
          <td>{moreThenOne(c.currencies)}</td>
          <td>{moreThenOne(c.languages)}</td>
          </tr>)}
        </tbody>
      </table>
    </div>
  );
};
export default CountryTable;