//Exercise Javascript Array
//a
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
console.log(boys);
console.log(girls);

//b
//Yes concat mutate an existing array
var all = boys.concat(girls);
console.log(all);

//c
var allStringC = all.join();
var allStringH = all.join('-');
console.log(allStringC);
console.log(allStringH);

//d
all.push('Lone', 'Gitte')
console.log(all);

//e
all.unshift('Hans', 'Kurt');
console.log(all);

//f
all.shift();
console.log(all);

//g
all.pop();
console.log(all);

//h
all.splice(3, 1);
all.splice(3, 1,);
console.log(all);

//i
all.reverse();
console.log(all);

//j
all.sort();
console.log(all);

//k
all.sort((a, b) => a.toLowerCase().localeCompare(b.toLowerCase()));
console.log(all);

//l
var upperCaseArr = all.map(x => x.toUpperCase());
console.log(upperCaseArr);

//m
var iOrLArray = all.filter(x => x.startsWith('I') || x.startsWith('L') );
console.log(iOrLArray);