import 'bootstrap/dist/css/bootstrap.css'

//Cant use my API from TheBackend with this SPA-client. This is because its not same-origin and we need CORS (Cross-origin resource sharing)

window.onload = function () {
    var url = "https://melif.dk/ErrorHandlingRestAssured/api/person/";
    //document.getElementById("table").innerHTML = "Hello World!";
    document.getElementById("getAll").onclick = function () {
        startFetch(url + "all", makeOptions("GET"), true);
    }
    document.getElementById("findPerson").onclick = function () {
        startFetch(url + document.getElementById("personId").value);
    }


    document.getElementById("addPerson").onclick = function () {
        const data = {
            fName: document.getElementById("fName").value,
            lName: document.getElementById("lName").value,
            phone: document.getElementById("phone").value,
            address: {
                street: document.getElementById("street").value,
                zip: document.getElementById("zip").value,
                city: document.getElementById("city").value,
            }
        };
        startFetch(url, makeOptions("POST", data));
    }

    document.getElementById("deletePerson").onclick = function () {
        startFetch(url + document.getElementById("personId").value, makeOptions("DELETE"));
    }

    document.getElementById("editPerson").onclick = function () {
        const data = {
            fName: document.getElementById("fName").value,
            lName: document.getElementById("lName").value,
            phone: document.getElementById("phone").value,
            address: {
                street: document.getElementById("street").value,
                zip: document.getElementById("zip").value,
                city: document.getElementById("city").value,
            }
        };
        startFetch(url + document.getElementById("personId").value, makeOptions("PUT", data));
    }
}

function startFetch(url, option, all) {
    fetch(url, option)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            if (all)
                document.getElementById("tableAll").innerHTML = createTable(data.all).outerHTML;
            else
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
            if (typeof object[key] === 'object') {
                var objectS = [];
                Object.keys(object[key]).map(k => {
                    if (k != "id")
                        objectS.push(object[key][k])
                });
                tBRow.insertCell(index).innerHTML = objectS.join(", ");
            }
            else
                tBRow.insertCell(index).innerHTML = object[key];
        });
    });
    return table;
};

