## Week 3 - Exercises 

### Tuesday

[Callbacks and more](https://github.com/Krigermus/Week6/tree/master/01Tuesday/JavaScriptExercises2Day1) - solution completed!

### Wednesday

[1. ES6 classes and Single Page Applications - No fetch()](https://github.com/Krigermus/Week6/tree/master/02Wednesday/ES6%20classes%20and%20Single%20Page%20Applications%20without%20Netbeans/ES6%20classes%20and%20Single%20Page%20Applications%20without%20Netbeans) - solution completed!

[2. Small application to display a quote of the hour](https://github.com/Krigermus/Week6/tree/master/02Wednesday/Fetch%20and%20Promises/SmallApplicationToDisplayAQuoteOfTheHour) - solution completed!

[3. JS Event handling, HTML5 and inline SVG](https://github.com/Krigermus/Week6/tree/master/02Wednesday/JSEventhandlingHTML5AndInlineSVG/JSEventhandlingHTML5AndInlineSVG) - solution completed!

[4. Ajax with a full REST CRUD Endpoint and Error-handling](https://github.com/Krigermus/Week6/tree/master/02Wednesday/CreateASinglePageApplicationThatUsesOurCoolAPI/CreateASinglePageApplicationThatUsesOurCoolAPI) - solution completed! **Backend** - Given JsonServer.

### Thursday

[1. CORS with Java and Jax-rs](https://github.com/Krigermus/Week6/tree/master/03Thursday/SingleOriginPolicyAndCORSWithJavaAndJAX-RS) - solution completed!

[2. CORS with Java and Jax-rs for a “real” project](https://github.com/Krigermus/Week6/tree/master/03Thursday/RealProjectErrorHandlingRestAssured/TheFrontend) - solution completed!

[3. Deploy a SPA on separate Servers(Backend)](https://melif.dk/TheBackend/api/cors/all) - solution completed!

[3. Deploy a SPA on separate Servers(Frontend)](http://melif.surge.sh/) - solution completed!

### Friday

[Exam preparation exercise about AJAX, CORS and SVG](https://github.com/Krigermus/Week6/tree/master/04Friday/Svg/Svg) - solution completed! 

[Exam preparation exercise about AJAX, CORS and SVG(Proxy)](https://github.com/Krigermus/Week6/tree/master/04Friday/SvgProxy/SvgProxy) - solution completed! 
* **Deployed:** https://melif.dk/SvgProxy/proxy/country/dk
<br>

[Frontend exercise (last part of exercise from last week)](https://github.com/Krigermus/Week6/tree/master/04Friday/TheFrontend/TheFrontend) - solution completed! 
* **DeployedBackend:** https://melif.dk/ErrorHandlingRestAssured/api/person/all 
* **DeployedFrontend:** http://meliflifperson.surge.sh/

### Documentation
This api is located on the given Json server.

1)

GET all

Path: api/users/

Returns: [{"id": long, "age": int, "name": String, "gender": String, "email": String}, .....]

Error: {"status": 404, "msg": "No content found for this request"}


2)

GET single user

Path: api/users/xxx

Returns: {"id": long, "age": int, "name": String, "gender": String, "email": String}


3)

POST

Path: api/users/

Returns: {"id": long, "age": int, "name": String, "gender": String, "email": String}

Error: {"status": 400, "msg": "Age must be >= 3 and <= 99"}

Error: {"status": 400, "msg": "Name must include at least 2 characters"}

Error: {"status": 400, "msg": "Gender must contain 'male' or 'female'"}

Error: {"status": 400, "msg": "Not a valid email"}


4)

PUT

Path: api/users/xxx

Returns: {"age": int, "name": String, "gender": String, "email": String "id": long}

Error: {"status": 400, "msg": "Age must be >= 3 and <= 99"}

Error: {"status": 400, "msg": "Name must include at least 2 characters"}

Error: {"status": 400, "msg": "Gender must contain 'male' or 'female'"}

Error: {"status": 400, "msg": "Not a valid email"}


5)

DELETE

Path: api/users/xxx

Returns: {}

Error: {"status": 404, "msg": "No content found for this request"}