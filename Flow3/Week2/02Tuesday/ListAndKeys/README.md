### What is the purpose of the key value, which must be given to individual rows in a react-list
- React's virtuelle dom kan ikke skelne imellem om et element er blevet slettet og et nyt element er blevet tilføjet eller om der er tale om at et element er blevet ændret. så derfor bruger vi keys til at hjælpe den virtuelle dom med at forstå en ændring i et element.

### It's recommended to use a unique value from your data if available (like an ID). How do you get a unique value in a map callback, for data without a unique id?
- Her kan du bruge index som en unique key da ingen elementer i et array kan have samme index.

### What is the difference(s) between state and props?
- State er en datasruktur der bliver instantieret med en defualt værdi når den mountes.
- Props(properties) er komponent argumenter som bliver sendt ned oppe fra ex. data og callbacks

### For which scenarios would you use props, and for which would you use state?
- Brug props når du vil give en komponent en variabl.
- Brug state når du vil have en variabel der kan ændre sig dynamisk.

### Where is the only place you can set state directly as in:  this.state = {name: "Peter"};
- I useState(her kan den sættes direkte);

### How must you set state all other places?
- const [getter, setter(Denne setter bruges alle andre steder)] = useState(initialValue);