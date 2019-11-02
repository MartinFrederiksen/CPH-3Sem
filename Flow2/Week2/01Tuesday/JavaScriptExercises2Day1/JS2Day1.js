//#region 1) Using existing functions that takes a callback as an argument
var arr = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
console.log(arr.filter(x => x.includes("a")));
console.log(arr.map(x => x.split("").reverse().join("")));
//#endregion

//#region 2) Implement user defined functions that take callbacks as an argument
//a
function myFilter(array, callback) {
    var newArray = [];
    array.forEach(element => {
        if (callback(element)) {
            newArray.push(element);
        }
    });
    return newArray;
}
console.log(myFilter(arr, x => x.includes("a")));

//b
function myMap(array, callback) {
    var newArray = [];
    array.forEach(element => {
        newArray.push(callback(element));
    });
    return newArray;
}
console.log(myMap(arr, x => x.split("").reverse().join("")));
//#endregion

//#region 3) Using the Prototype property to add new functionality to existing objects
//a
var names = ["Lars", "Peter", "Jan", "Bo"];
Array.prototype.myFilter = function (callback) {
    var newArray = [];
    this.forEach(element => {
        if (callback(element)) {
            newArray.push(element);
        }
    });
    return newArray;
}
console.log(names.myFilter(x => x.includes("a")));

//b
Array.prototype.myMap = function (callback) {
    var newArray = [];
    this.forEach(element => {
        newArray.push(callback(element));
    });
    return newArray;
}
console.log(names.myMap(x => x.split("").reverse().join("")));
//#endregion

//#region 4) Getting really comfortable with filter and map
//a
var numbers = [1, 3, 5, 10, 11];
var newNumbers = numbers.map(function (x, y) {
    if (y < numbers.length - 1) {
        return x + numbers[y + 1];
    }
    return x;
})
console.log(newNumbers);
//above code on oneline
//console.log(numbers.map((n, i) => n + (i == numbers.length - 1 ? 0 : numbers[i+1])));

//b
//var str = "<nav>\n" + names.map(x => "  <a href=\"\">" + x + "</a>").join("\n") + "\n</nav>";
function getNav(arr) {
    return "<nav>\n" + arr.map(x => "  <a href=\"\">" + x + "</a>").join("\n") + "\n</nav>";
}
console.log(getNav(arr));

//c
var names = [{ name: "Lars", phone: "1234567" }, { name: "Peter", phone: "675843" }, { name: "Jan", phone: "98547" }, { name: "Bo", phone: "79345" }];
//var str = "<table>\n  <thead>\n    <tr><th>Names</th><th>Phone</th></tr>\n  </thead>\n" + names.map( x => "    <tr><td>"+x.name+"</td><td>"+x.phone+"</td></tr>").join("\n") + "\n</table>";
//console.log(str);
function createTable(array) {
    var table = document.createElement("table");
    table.classList.add("table");
    table.classList.add("table-dark");
    var tHead = table.createTHead();
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

//d look at index.html for the html part
/*window.onload = function () {
    document.getElementById("names").innerHTML = createTable(names).outerHTML;
    document.getElementById("nav").innerHTML = getNav(arr);
    console.log(createTable(names).outerHTML);

    //e look at index.html for the html part
    document.getElementById("btn").onclick = function(){
        document.getElementById("names").innerHTML = createTable(names.myFilter(x => x.name.includes("a"))).outerHTML;
        document.getElementById("nav").innerHTML = getNav(arr.myFilter(x => x.includes("a")));
    };
};*/
//#endregion

//#region Reduce
//a
var all = ["Lars", "Peter", "Jan", "Bo"];
console.log(all.join(",") + all.join(" ") + all.join("#"));

//b
var numbers = [2, 3, 67, 33]; 
console.log(numbers.reduce((sum, num) => sum + num, 0));

//c
var members = [
    {name : "Peter", age: 18},
    {name : "Jan", age: 35},
    {name : "Janne", age: 25},
    {name : "Martin", age: 22}]
console.log(members.reduce((sum, {age}) => sum + age, 0)/members.length);

//d
var votes = [ "Clinton","Trump","Clinton","Clinton","Trump","Trump","Trump","None"];
var votesCount = votes.reduce((acc, vote) => {
    if(acc[vote] == null)
        acc[vote] = 1;
    else
        acc[vote]++;
    return acc;
},{});
console.log(votesCount);
//#endregion

//#region Hoisting
//Function declarations are completely hoisted
//Example 1)
stringHoisting();
console.log(someString);
var someString = "Im empty";
function stringHoisting(){
    someString = "Im not empty anymore";
}

//Example 2)
//Var declarations are also hoisted, but not assignments made with them
testNumHoisted = 5; // Assign 5 to x
console.log(testNumHoisted);
var testNumHoisted; // Declare x 

var testNumHoistedUndefined; // Declare x 
console.log(testNumHoistedUndefined);
testNumHoistedUndefined = 5; // Assign 5 to x


//What hoisting is
/*
Hoisting is JavaScript's default behavior of moving all declarations to the top of the current scope (to the top of the current script or the current function). 
*/

//A design rule we could follow, now we know about hoisting
/*
Declare Your Variables At the Top !
Hoisting is (to many developers) an unknown or overlooked behavior of JavaScript.
If a developer doesn't understand hoisting, programs may contain bugs (errors).
To avoid bugs, always declare all variables at the beginning of every scope.
Since this is how JavaScript interprets the code, it is always a good rule.
*/

//What is the difference between the keyword var and the ES6 keyword let?
/*
Variables and constants declared with let are not hoisted.
Var is function scoped and let is block scoped.
Calling var before definition will return undefined.
Calling let before definition will give error.
*/
//#endregion

//#region this in JavaScript
//Example 1)
function printProp() {
    return this.prop;
 }
 console.log(printProp.bind({prop: "Test"})());
 //Example 2)
 function bar() {
    console.log(Object.prototype.toString.call(this));
 }
 bar.call(2);
 bar.call("Hello World!")
 //Example 3)
 var person = {
    fullName: function() {
      return this.firstName + " " + this.lastName;
    }
  }
  var person1 = {
    firstName: "Mary",
    lastName: "Doe"
  }
  console.log(person.fullName.apply(person1));
//How this in JavaScript differ from this in Java
/*
this in Java refers to its own class like when we use this.someFieldName. 
this in Javascript refers to function your running at the moment.
*/

//The purpose of the methods call(), apply() and bind()
/*
The bind() method creates a new function that, when called, has its this keyword set to the provided value, with a given sequence of arguments preceding any provided when the new function is called.
Call can be used to invoke (call) a method with an owner object as an argument (parameter).
The call() method takes arguments separately. The apply() method takes arguments as an array.
*/
//#endregion

//#region Reusable Modules with Closures 
//1
var add = (function () {
    var counter = 0;
    return function () {counter += 1; return counter}
  })();  
add();
add();
add();
console.log(add());

//2
var person = (function() {
    var pAge;
    var pName;

    /*
    function setName(age){
        pAge = age;
    }
    function setName(name){
        pName = name;
    }*/
    
    return {
        setName: function(name) {
            //setName(name);
            pName = name;
        },
        setAge: function(age) {
            //setAge(age);
            pAge = age;
        },
        getInfo: function() {
            return pName + ", " + pAge;
        }
    };
})();

var p = person;
p.setName("Peter")
p.setAge(45)
console.log(p.getInfo());
//#endregion