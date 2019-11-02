import 'bootstrap/dist/css/bootstrap.css'

var url = "https://melif.dk/ErrorHandlingRestAssured/api/person/";
window.onload = function () {
    startFetch(url + "all");
    document.getElementById("reload").onclick = function () {
        startFetch(url + "all");

    }

    document.getElementById("addPersonModal").onclick = function () {
        var data = {
            fName: document.getElementById("fName").value,
            lName: document.getElementById("lName").value,
            phone: document.getElementById("phone").value,
            address: {
                street: document.getElementById("street").value,
                zip: document.getElementById("zip").value,
                city: document.getElementById("city").value
            }
        }
        startFetch(url, makeOptions("POST", data))
    }
    document.getElementById("editPerson").onclick = function() {
        var data = {
            fName: document.getElementById("editFName").value,
            lName: document.getElementById("editLName").value,
            phone: document.getElementById("editPhone").value,
            address: {
                street: document.getElementById("editStreet").value,
                zip: document.getElementById("editZip").value,
                city: document.getElementById("editCity").value
            }
        }
        startFetch(url + document.getElementById("editId").value, makeOptions("PUT", data), false)
    }

}

function clickHandler(e) {
    if (e.target.id != null) {
        if (e.target.classList.contains("btndelete")) {
            startFetch(url + e.target.id, makeOptions("DELETE"));
        } else {
            startFetch(url + e.target.id, makeOptions("GET"), true);

        }
    }
}

function startFetch(newUrl, option, edit) {
    fetch(newUrl, option)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            if (data.id != null) {
                $('#personModal').modal('hide');
                $('#editPersonModal').modal('hide');
                startFetch(url + "all");
            }
            if (data.status != null) {
                startFetch(url + "all");
            }
            if (edit) {
                document.getElementById("editId").value = data.id;
                document.getElementById("editFName").value = data.fName;
                document.getElementById("editLName").value = data.lName;
                document.getElementById("editPhone").value = data.phone;
                document.getElementById("editStreet").value = data.address.street;
                document.getElementById("editZip").value = data.address.zip;
                document.getElementById("editCity").value = data.address.city;
            }
            else {
                document.getElementById("tableAll").innerHTML = "";
                document.getElementById("tableAll").appendChild(createTable(data.all));
            }
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
    tRow.insertCell();
    Object.keys(array[0]).map(function (key, index) {
        tRow.insertCell(index).innerHTML = key;
    });

    var tBody = table.createTBody();
    tBody.addEventListener("click", (e) => { clickHandler(e) });
    array.map(function (object, index) {
        var tBRow = tBody.insertRow(index);
        tBRow.insertCell().innerHTML = "<a href='#' class='btndelete' id=" + object["id"] + ">delete</a> / <a href='#' class='btnedit' id=" + object["id"] + " data-toggle=modal data-target=#editPersonModal>edit</a>"
        Object.keys(object).map(function (key, index) {
            if (typeof object[key] === "object") {
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