import 'bootstrap/dist/css/bootstrap.css'

//Cant use my API from TheBackend with this SPA-client. This is because its not same-origin and we need CORS (Cross-origin resource sharing)

window.onload = function () {
    var url = "https://melif.dk/TheBackend/api/cors/";
    //document.getElementById("table").innerHTML = "Hello World!";
    document.getElementById("getAll").onclick = function() {
        startFetch(url + "all");
    }
    document.getElementById("findPerson").onclick = function() {
        startFetch(url + document.getElementById("personId").value);
    }

    //document.getElementById("tableAll").hidden = true;

    document.getElementById("addPerson").onclick = function() {
        const data = {
            name: document.getElementById("personName").value,
        };
        startFetch(url, makeOptions("POST", data));
    }

    document.getElementById("deletePerson").onclick = function() {
        startFetch(url + document.getElementById("personId").value, makeOptions("DELETE"));
    }

    document.getElementById("editPerson").onclick = function() {
        const data = {
            name: document.getElementById("personName").value,
        };
        startFetch(url + document.getElementById("personId").value, makeOptions("PUT", data));
    }
}

function startFetch(url, option) {
    fetch(url, option)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            document.getElementById("tableAll").innerHTML = createTable(data).outerHTML;
        });
};

function makeOptions(http_method, body) {
    var options = {
        method: http_method,
        headers: {
            "Content-type": "application/json",
            "Accept": "application/json"
        }
    }
    if (body) {
        options.body = JSON.stringify(body);
    }
    return options;
};

function createTable(array) {
    if (!Array.isArray(array))
        array = [array];

    var table = document.createElement("table");
    table.classList.add("table");
    table.classList.add("table-light");
    var tHead = table.createTHead();
    tHead.classList.add("table-dark");

    var tRow = tHead.insertRow(0);
    Object.keys(array[0]).map(function (key, index) {
        tRow.insertCell(index).innerHTML = key;
    });

    var tbody = table.createTBody();
    array.map(function (object, index) {
        var tBRow = tbody.insertRow(index);
        Object.keys(object).map(function (key, index) {
            tBRow.insertCell(index).innerHTML = object[key];
        });
    });
    return table;
};

