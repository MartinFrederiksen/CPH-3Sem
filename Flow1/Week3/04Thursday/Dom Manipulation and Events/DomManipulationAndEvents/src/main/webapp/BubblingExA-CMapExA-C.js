window.onload = function(){

function divClick(){
    document.getElementById("divTxt").innerText  = "Hi from " + this.id;
}

var div = document.getElementById("div4");
div.onclick = divClick;

var div = document.getElementById("div5");
div.onclick = divClick;


var divs = document.getElementById("outer").children;
for(i = 0; i < divs.length; i++) {
    divs[i].addEventListener("click", divClick);
}


var names = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
var html = "<ul>" + names.map(x => "<li>" + x + "</li>").join('') + "</li></ul>";
document.getElementById("newOuter").innerHTML = html;

function addName(){
    names.push(document.getElementById("name").value);
    html = "<ul>" + names.map(x => "<li>" + x + "</li>").join('') + "</li></ul>";
    document.getElementById("newOuter").innerHTML = html;
}

function removeFirst(){
    names.shift();
    html = "<ul>" + names.map(x => "<li>" + x + "</li>").join('') + "</li></ul>";
    document.getElementById("newOuter").innerHTML = html;
}
function removeLast(){
    names.pop();
    html = "<ul>" + names.map(x => "<li>" + x + "</li>").join('') + "</li></ul>";
    document.getElementById("newOuter").innerHTML = html;
}


document.getElementById("addName").onclick = addName;
document.getElementById("removeFirst").onclick = removeFirst;
document.getElementById("removeLast").onclick = removeLast;
}
