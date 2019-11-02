import 'bootstrap/dist/css/bootstrap.css'
/*
document.getElementById("north").onclick = function() {
    document.getElementById("svgOut").innerHTML = "North!";
}

document.getElementById("south").onclick = function() {
    document.getElementById("svgOut").innerHTML = "South!";
}

document.getElementById("east").onclick = function() {
    document.getElementById("svgOut").innerHTML = "East!";
}

document.getElementById("west").onclick = function() {
    document.getElementById("svgOut").innerHTML = "West!";
}
*/
document.getElementById("fourHearts").addEventListener("click", (e) => {
    output(document.getElementById(e.target.id).parentElement.id)
});

function output(direction) {
    document.getElementById("svgOut").innerHTML = direction;
}
