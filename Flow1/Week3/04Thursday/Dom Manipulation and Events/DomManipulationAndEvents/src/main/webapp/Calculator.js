window.onload = function () {


var divs = document.getElementById("buttons").children;
for(i = 0; i < divs.length; i++) {
    if(divs[i].innerHTML != "=") {
        divs[i].addEventListener("click", display);
    }
}


function display(){
    var sym = document.getElementById("display").innerHTML.slice(-1);
    if(this.innerHTML == '*' || this.innerHTML == '+' || this.innerHTML == '/' || this.innerHTML == '-') {
        if(sym !== '*' && sym !== '+' && sym !== '-' && sym !== '/') {    
            document.getElementById("display").innerHTML += this.innerHTML;
        }
    } else {
        document.getElementById("display").innerHTML += this.innerHTML;
    }
}


function calc(){
    document.getElementById("display").innerHTML = eval(document.getElementById("display").innerHTML);
}
document.getElementById("calculate").onclick = calc;
}