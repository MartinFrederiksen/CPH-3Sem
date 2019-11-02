import 'bootstrap/dist/css/bootstrap.css'

window.onload = function () {
    const url = window.location.href;
    if (url == "http://localhost:3456/#all") {
        startFetch("http://localhost:3333/api/users");
    }

    if (url == "http://localhost:3456/#single") {
        document.getElementById("single").classList.add("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("userTable").innerHTML = createInput().outerHTML;
        document.getElementById("btn").onclick = function () {
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value);
        }
    }

    if (url == "http://localhost:3456/#add") {
        document.getElementById("add").classList.add("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("userTable").innerHTML = createInputGrp().outerHTML;
        document.getElementById("btnAdd").onclick = function () {
            const data = {
                age: document.getElementById("age").value,
                name: document.getElementById("name").value,
                gender: document.getElementById("gender").value,
                email: document.getElementById("email").value
            };
            startFetch("http://localhost:3333/api/users/", makeOptions("POST", data));
        };
    }

    if (url == "http://localhost:3456/#edit") {
        document.getElementById("edit").classList.add("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("userTable").innerHTML = createInputGrp(true).outerHTML;
        document.getElementById("btnAdd").onclick = function () {
            const data = {
                age: document.getElementById("age").value,
                name: document.getElementById("name").value,
                gender: document.getElementById("gender").value,
                email: document.getElementById("email").value
            };
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value, makeOptions("PUT", data));
        }
        
    }

    if (url == "http://localhost:3456/#delete") {
        document.getElementById("delete").classList.add("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("userTable").innerHTML = createInput(true).outerHTML;
        document.getElementById("btn").onclick = function () {
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value, makeOptions("DELETE"));
        }
    }


    document.getElementById("all").onclick = function () {
        document.getElementById("all").classList.add("active");
        document.getElementById("single").classList.remove("active");
        document.getElementById("add").classList.remove("active");
        document.getElementById("edit").classList.remove("active");
        document.getElementById("delete").classList.remove("active");

        //document.getElementById("userTable").innerHTML = createTable(arr).outerHTML;
        startFetch("http://localhost:3333/api/users");
    }

    document.getElementById("single").onclick = function () {
        document.getElementById("single").classList.add("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("add").classList.remove("active");
        document.getElementById("edit").classList.remove("active");
        document.getElementById("delete").classList.remove("active");

        document.getElementById("userTable").innerHTML = createInput().outerHTML;

        document.getElementById("btn").onclick = function () {
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value);
        }
    }

    document.getElementById("add").onclick = function () {
        document.getElementById("add").classList.add("active");
        document.getElementById("single").classList.remove("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("edit").classList.remove("active");
        document.getElementById("delete").classList.remove("active");

        document.getElementById("userTable").innerHTML = createInputGrp().outerHTML;
        document.getElementById("btnAdd").onclick = function () {
            const data = {
                age: document.getElementById("age").value,
                name: document.getElementById("name").value,
                gender: document.getElementById("gender").value,
                email: document.getElementById("email").value
            };
            startFetch("http://localhost:3333/api/users/", makeOptions("POST", data));
        };
    }

    document.getElementById("edit").onclick = function () {
        document.getElementById("edit").classList.add("active");
        document.getElementById("single").classList.remove("active");
        document.getElementById("add").classList.remove("active");
        document.getElementById("all").classList.remove("active");
        document.getElementById("delete").classList.remove("active");

        document.getElementById("userTable").innerHTML = createInputGrp(true).outerHTML;
        document.getElementById("btnAdd").onclick = function () {
            const data = {
                age: document.getElementById("age").value,
                name: document.getElementById("name").value,
                gender: document.getElementById("gender").value,
                email: document.getElementById("email").value
            };
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value, makeOptions("PUT", data));
        }

    }

    document.getElementById("delete").onclick = function () {
        document.getElementById("delete").classList.add("active");
        document.getElementById("single").classList.remove("active");
        document.getElementById("add").classList.remove("active");
        document.getElementById("edit").classList.remove("active");
        document.getElementById("all").classList.remove("active");

        document.getElementById("userTable").innerHTML = createInput(true).outerHTML;
        document.getElementById("btn").onclick = function () {
            startFetch("http://localhost:3333/api/users/" + document.getElementById("userId").value, makeOptions("DELETE"));
        }
    }
}

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

function startFetch(url, option) {
    fetch(url, option)
        .then(res => fetchWithErrorCheck(res))
        .then(data => document.getElementById("userTable").innerHTML = createTable(data).outerHTML)
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => document.getElementById("userTable").innerHTML = createTable(e).outerHTML);
            }
            else { console.log("Network error"); }
        });
}


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
}

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
}

function createInput(del) {
    var div = document.createElement("div");
    div.classList.add("input-group");
    div.classList.add("mb-3");

    var input = document.createElement("input");
    input.classList.add("form-control");
    input.setAttribute('type', 'number');
    input.setAttribute('value', "0");
    input.setAttribute('id', 'userId');

    var div2 = document.createElement("div");
    div2.classList.add("input-group-append");

    var button = document.createElement("button");
    button.classList.add("btn");
    button.classList.add("btn-outline-secondary");
    button.setAttribute("id", "btn")
    if(del)
        button.innerHTML = "Delete user";
    else 
        button.innerHTML = "Find user by id";

    div2.appendChild(button);
    div.appendChild(input);
    div.appendChild(div2);
    return div;
}

function createInputGrp(option) {
    //var form = document.createAttribute("form");
    //form.setAttribute('method',"post");
    //form.setAttribute('action',"submit.php");
    var div = document.createElement("div");
    div.classList.add("row");

    var div1 = document.createElement("div");
    div.classList.add("col");
    div.setAttribute("id", "col1");

    var inputAge = document.createElement("input");
    inputAge.classList.add("form-control");
    inputAge.setAttribute("type", "number");
    inputAge.placeholder = "Age";
    inputAge.setAttribute("id", "age");

    var inputName = document.createElement("input");
    inputName.classList.add("form-control");
    inputName.setAttribute("type", "text");
    inputName.placeholder = "Name";
    inputName.setAttribute("id", "name");

    var div2 = document.createElement("div");
    div1.classList.add("col");
    var inputGender = document.createElement("input");
    inputGender.classList.add("form-control");
    inputGender.setAttribute("type", "text");
    inputGender.placeholder = "Gender";
    inputGender.setAttribute("id", "gender");

    var inputEmail = document.createElement("input");
    inputEmail.classList.add("form-control");
    inputEmail.setAttribute("type", "text");
    inputEmail.placeholder = "Email";
    inputEmail.setAttribute("id", "email");

    var div3 = document.createElement("div");
    div3.classList.add("col");

    var button = document.createElement("button");
    button.classList.add("btn");
    button.classList.add("btn-outline-secondary");
    button.setAttribute("id", "btnAdd")

    if (option) {
        button.innerHTML = "Edit user";
        var inputId = document.createElement("input");
        inputId.classList.add("form-control");
        inputId.setAttribute("type", "number");
        inputId.placeholder = "UserId";
        inputId.setAttribute("id", "userId");
        div3.append(inputId)
    } else {
        button.innerHTML = "Add user";
    }

    div1.appendChild(inputAge);
    div1.appendChild(inputName);
    div2.appendChild(inputGender);
    div2.appendChild(inputEmail);
    div3.append(button)
    div.appendChild(div1);
    div.appendChild(div2);
    div.appendChild(div3);
    //form.appendChild(div);
    //form.appendChild(div1);
    return div;
}