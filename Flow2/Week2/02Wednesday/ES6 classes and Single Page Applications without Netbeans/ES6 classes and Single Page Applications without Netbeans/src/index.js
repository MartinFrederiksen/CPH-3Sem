import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

const allJokes = jokes.getJokes().map(joke => "<li>"+joke+"</li>");
document.getElementById("jokes").innerHTML = allJokes.join("");


document.getElementById("getJoke").onclick = function() {
    const singleJoke = jokes.getJokeById(document.getElementById("jokeId").value);
    document.getElementById("jokeText").innerHTML = singleJoke;
}


document.getElementById("addJoke").onclick = function() {
    jokes.addJoke(document.getElementById("addJokeId").value);
    const addedJoke = jokes.getJokeById(jokes.getJokes().length-1)
    document.getElementById("addJokeText").innerHTML = addedJoke;
}