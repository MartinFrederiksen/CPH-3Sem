function click(){
    document.getElementById("div1").style.backgroundColor = 'green';
    document.getElementById("div2").style.backgroundColor = 'yellow';
    document.getElementById("div3").style.backgroundColor = 'red';
}
var btn = document.getElementById("btn");
btn.onclick = click;