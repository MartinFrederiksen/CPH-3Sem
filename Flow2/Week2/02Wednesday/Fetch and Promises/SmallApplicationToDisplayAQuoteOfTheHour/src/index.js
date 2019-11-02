import 'bootstrap/dist/css/bootstrap.css'

document.getElementById("btn").onclick = function () {
    setInterval(function(){ startFetch("https://studypoints.info/jokes/api/jokes/period/hour"); }, 3600000);
}

function startFetch(url) {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            document.getElementById("div").innerHTML = "<q>" + data.joke + "</q>";
        });
};

//Explain why, what you did above, is even possible, when we know the Same Origin Policy governs when/where AJAX-request can go.
/*
https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Origin#Syntax
For requests without credentials, the literal value "*" can be specified, as a wildcard; 
the value tells browsers to allow requesting code from any origin to access the resource. 
Attempting to use the wildcard with credentials will result in an error.
*/