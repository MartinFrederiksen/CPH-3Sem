### What is meant by the react term “Lifting State Up”?
- Normalt bliver data altid sendt nedad til vores komponenter og aldrig op. Altså fra parent til child men aldrig omvendt. Hvis vi så bruger "lifting state up" så sender vi en callback nedad fra parent til child sådan at child kan ændre state vha. callback og derved ændre komponenterne opad.

### Where do you lift it up to?
- Der hvor callbacken kommer fra. Forstil dig en kæde af P->C(P)->C(P)->C(P=parent C=child). Altså et parent komponent der er child for et ovenstående parant komponent. På den måde kan callback flyde hele vejen ned fra øverste parent til nederste child og flyde hele vejen tilbage igen igennem de mellemliggende childs.

### Which way does React recommend data to flow: From sibling to sibling, from bottom to top or from top to bottom?
- Fra top til bund vha props. Skal vi den anden vej skal vi bruge callback men det er ikke den anbefalet måde at gøre det på.

### Lifting state up, involves a great deal of boilerplate code, what’s the benefit we get from doing “things” like this?
- Vi kan have en single source of truth i vores parent element.