window.onload = function () {

    function getUser() {
        let url = "https://jsonplaceholder.typicode.com/users/" + document.getElementById("userId").value;
        startFetch(url, "single");
    }

    function getAll() {
        let url = "https://jsonplaceholder.typicode.com/users/";
        startFetch(url, "all");
    }

    document.getElementById("getUser").onclick = getUser;
    document.getElementById("getAll").onclick = getAll;



    function startFetch(url, type) {
        switch (type) {
            case "single":
                fetch(url)
                    .then(res => res.json()) //in flow1, just do it
                    .then(data => {
                        // Inside this callback, and only here, the response data is available
                        console.log("data", data);
                        /* data now contains the response, converted to JavaScript
                           Observe the output from the log-output above
                           Now, just build your DOM changes using the data*/
                        document.getElementById("data").innerHTML =
                            "Name: " + data.name +
                            "<br>Phone: " + data.phone +
                            "<br>" +
                            "<br><b>Address</b>" +
                            "<br>Street: " + data.address.street +
                            "<br>City: " + data.address.city +
                            "<br>Zip: " + data.address.zip +
                            "<br>Geo (lat, lng): " + data.address.geo.lat + ", " + data.address.geo.lng;
                    })
                break;
            case "all":
                fetch(url)
                    .then(res => res.json()) //in flow1, just do it
                    .then(data => {
                        // Inside this callback, and only here, the response data is available
                        console.log("data", data);
                        /* data now contains the response, converted to JavaScript
                           Observe the output from the log-output above
                           Now, just build your DOM changes using the data*/
                        document.getElementById("data").innerHTML =
                            "<table><thead><tr><th>Name</th><th>Phone</th></tr></thead><tbody>" + 
                            data.map(x => "<tr><td>" + x.name + "</td><td>" + x.phone +"</td></tr>").join('');
                            + "</tbody></table>";
                        })
                        break;
                    }
    }
    }
