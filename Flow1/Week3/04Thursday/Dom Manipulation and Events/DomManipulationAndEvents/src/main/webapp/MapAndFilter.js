window.onload = function () {

    var cars = [
        { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
        { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
        { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
        { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
        { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
    ];

    var html = "<thead><tr><th>Id</th><th>Year</th><th>Make</th><th>Model</th><th>Price</th></tr></thead><tbody>" + cars.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.make + "</td><td>" + x.model + "</td><td>" + x.price + "</td></tr>").join('') + "</tbody>";
    document.getElementById("cars").innerHTML += html;


    function priceLessThan(){
        var carPrice = cars.filter(x => x.price < document.getElementById("price").value);
        html = "<thead><tr><th>Id</th><th>Year</th><th>Make</th><th>Model</th><th>Price</th></tr></thead><tbody>" + carPrice.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.make + "</td><td>" + x.model + "</td><td>" + x.price + "</td></tr>").join('') + "</tbody>";
        console.log(html)
        document.getElementById("cars").innerHTML = html;
    }
    document.getElementById("priceLessThan").onclick = priceLessThan;

}