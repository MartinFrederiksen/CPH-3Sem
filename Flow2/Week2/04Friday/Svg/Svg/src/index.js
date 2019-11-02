import 'bootstrap/dist/css/bootstrap.css'

var url = "https://melif.dk/SvgProxy/proxy/country/";
var lastTarget;

document.getElementById("svg2").onclick = function(e) {
    if(e.target.id == "Large masses of water" || e.target.id.substring(0, 2) == "sv"){
        return;
    }
    startFetch(url + e.target.id.substring(0, 2));

    if(lastTarget != null){
        lastTarget.style.fill = "#c0c0c0";
    }
    lastTarget = e.target;
    e.target.style.fill = "red";
}




function startFetch(url) {
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
                document.getElementById("info").innerHTML =
                "<ul>" +
                "<li>Country: " + data[0].name + "</li>" +
                "<li>Population: " + data[0].population + "</li>" +
                "<li>Area: " + data[0].area + "</li>" +
                "<li>Borders: " + data[0].borders + "</li>" +
                "</ul>";
        });
};

/*
The Document Object Model (DOM) is a cross-platform and language-independent interface that treats an XML or HTML document as a tree structure wherein each node is an object representing a part of the document.
When an event happens on an element, it first runs the handlers on it, then on its parent, then all the way up on other ancestors.(https://javascript.info/bubbling-and-capturing)
I dont know?
The basic idea of AJAX is to exchange small amounts of data between client and server. This results in the webpage dont need to be reloaded every time a user makes a request. The purpose of this is so increase the webpage interactivity, speed and userfriendlyness
Ajax (Asynchronous JavaScript and XML) is a technique on the client-side used to create asynchronous Web applications. With Ajax, Web applications can send data to, and retrieve data from, a server asynchronously (in the background) through JavaScript without interfering with the display and behavior of the existing page. Data can be retrieved using the XMLHttpRequest object.
*/


/*
Access-Control-Allow-Headers: Accept, X-Requested-With
Access-Control-Allow-Methods: GET
Access-Control-Allow-Origin: *
Server: cloudflare -- own origin server?
*/