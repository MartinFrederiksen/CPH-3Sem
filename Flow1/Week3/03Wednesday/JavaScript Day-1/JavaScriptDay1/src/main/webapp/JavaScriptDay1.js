//JavaScript functions and Callbacks
//1
//Function Declaration
//Observe: no return type, no type on parameters
function add(n1, n2) {
  return n1 + n2;
}

//Function Expression
var sub = function (n1, n2) {
  return n1 - n2
}

//Callback example
var cb = function (n1, n2, callback) {
  try {
    if (typeof n1 === "number" && typeof n2 === "number" && typeof callback === "function") {
      return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
    } else {
      throw new Error('Whoops!');
    }
  } catch (e) {
    console.log(e.name + ': ' + e.message);
  }
};

//2
console.log(add(1, 2))     // What will this print?
console.log(add)          // What will it print and what does add represent?
console.log(add(1, 2, 3)); // What will it print
console.log(add(1));      // What will it print     
console.log(cb(3, 3, add)); // What will it print
console.log(cb(4, 3, sub)); // What will it print
console.log(cb(3, 3, add())); // What will it print (and what was the problem)
console.log(cb(3, "hh", add));// What will it print

//4
function mul(n1, n2) {
  return n1 * n2;
}
console.log(cb(3, 3, mul));

//5
console.log(cb(3, 3, (a, b) => a / b));

//Getting comfortable with filter, map and forEach:
//1
var names = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
var namesOnThreeLetters = names.filter(x => x.length <= 3);
console.log(namesOnThreeLetters);

names.forEach(element => console.log(element));
namesOnThreeLetters.forEach(element => console.log(element));

//2
var uppercased = names.map(x => x.toUpperCase());
console.log(uppercased);

//3
var html = "<ul>" + names.map(x => "<li>" + x + "</li>").join('') + "</li></ul>";
console.log(html);

//4
var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

var carsFilterYear = cars.filter(x => x.year > 1999);
console.log(carsFilterYear);

var carsFilterVolvo = cars.filter(x => x.make == 'Volvo');
console.log(carsFilterVolvo);

var carsFilterPrice = cars.filter(x => x.price < 5000);
console.log(carsFilterPrice);

//4a
var html = cars.map(x => "INSERT INTO cars (id, year, make, model, price) VALUES (" + x.id + ', ' + x.year + ', ' + x.make + ', ' + x.model + ', ' + x.price + ')').join('; ');
console.log(html);

//Asynchronous Callbacks
//1
//Console.log will run before msgPrinter because msgPrinter got a delay we will see aaa.., ddd.., fff.., eee.., bbb...
//2
//I was right look at the delay and the knowledge of the script running from top to bottom.
var msgPrinter = function (msg, delay) {
  setTimeout(function () {
    console.log(msg);
  }, delay);
};
console.log("aaaaaaaaaa");
msgPrinter("bbbbbbbbbb", 2000);
console.log("dddddddddd");
msgPrinter("eeeeeeeeee", 1000);
console.log("ffffffffff");

//this and constructor functions
//1
function Person(name) {
  this.name = name;
  console.log("Name: " + this.name);
  setTimeout(function () {
    console.log("Hi  " + this.name);  //This is an anonymous function and that why we get the name undefined
  }, 2000);
}
//call it like this (do it, even if you know it’s silly ;-)
Person("Kurt Wonnegut");  //This calls the function
console.log("I'm global: " + name);  //This call the method Person and the name from the method Person i private so if you want the name logged to the console you need to make Person("Kurt Wonnegut") a variable and then log the variable e.g. p.name.

//2
var p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
console.log("I'm global: " + name);  //Same problem as before this returns undefined if you want name type p.name


//3
//Store a reference to the outer this
//Sets a globel variable var self = this
function Person(name) {
  this.name = name;
  var self = this;
  console.log("Name: " + this.name);
  setTimeout(function () {
    console.log("Hi  " + self.name);
  }, 2000);
}

//Using the bind(..) function
//Binds this to the function
function Person(name) {
  this.name = name;
  console.log("Name: " + this.name);
  setTimeout(function () {
    console.log("Hi  " + this.name);
  }.bind(this), 2000);
}

var p = new Person("New Kurt Wonnegut");  //Create an instance using the constructor function
console.log("I'm new global: " + name);

var greeter = function () {
  console.log(this.message);
};
var comp1 = { message: "Hello World" };
var comp2 = { message: "Hi" };

//Binds the massage into the greeter function with didn't had a massage from the start
var g1 = greeter.bind(comp1);//We can store a reference, with a specific “this” to use
var g2 = greeter.bind(comp2);//And here another “this”
setTimeout(g1, 2500);
setTimeout(g2, 2500);


//JavaScript Objects
//1
function MyPerson(name, birthday, hobby, email) {
  this.name = name;
  this.birthday = birthday;
  this.hobby = hobby;
  this.email = email;
}

var test = new MyPerson("testPerson", "11/1-19", "Coding", "test@test.test");

for (var prop in test) {
  console.log(prop, test[prop]);
}

delete test.birthday;

//birthday deleted
for (var prop in test) {
  console.log(prop, test[prop]);
}

test["birthday"] = "01/01-01";

//added new birthday
for (var prop in test) {
  console.log(prop, test[prop]);
}

//2
function NewPerson(firstName, lastName, age) {
  this.firstName = firstName;
  this.lastName = lastName;
  this.age = age;

  this.getDetails = function () {
    return "Firstname: " + this.firstName + " Lastname: " + this.lastName + " Age: " + this.age;
  };

}

var nP = new NewPerson('Johnny', 'Boy', 69);
console.log(nP.getDetails());

//Reusable Modules with Closures 
//2
function NewNewPerson() {
  var privateAge;
  var privateName;

  function setAge(age) {
    privateAge = age;
  };

  function setName(name) {
    privateName = name;
  };

  function details() {
    console.log("Name: " + privateName + " Age: " + privateAge);
  };

  function getDetails() {
    details();
  };

  return {
    setAge : setAge,
    setName : setName,
    getDetails : getDetails
    };
  };

var nNP = new NewNewPerson();
nNP.setAge(45);
nNP.setName("Hans");
nNP.getDetails();