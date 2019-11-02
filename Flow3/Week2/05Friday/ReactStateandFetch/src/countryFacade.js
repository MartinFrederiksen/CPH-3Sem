//Add imports here

//The two methods below, are the utility-methods introduced here (use them if you like):
//https://docs.google.com/document/d/1hF9P65v_AJKCjol_gFkm3oZ1eVTuOKc15V6pcb3iFa8/edit?usp=sharing
function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

const countryFacade = () => {

  const getLabels = () => {
    //TODO: Get Labels from server
    return fetch("http://localhost:3333/labels").then(handleHttpErrors);
  }

  const getCountries = () => {
    //TODO: Get Countries from server
    return fetch("http://localhost:3333/countries").then(handleHttpErrors);
  }
  return {
    getLabels,
    getCountries
  }
}

export default  countryFacade();