###In a Controlled Component React state is made the "Single source of truth", so how:
⋅⋅⋅ Do we ensure that input controls like text, textarea or select always presents the value found in the components state?
- Det gør vi ved at have value={element.variable} i vores input tag.

⋅⋅⋅ Do we ensure that a controls state, always matches the value found in an input control?
- Ja for vi laver en onChange event handler som sætter vores value på parent elementet hver gang vores input ændre sig.

###What is the purpose of doing event.preventDefault() in an event handler?
- Så vi ikke sætter vores "spinning donut" igang. Ved default når vi submitter så henter vi vores side fra serveren igen og det ønsker vi ikke når vi laver en singlepage app.

###What would be the effect of NOT doing event.preventDefault in a submit handler
- Effekten vil være at vi reloader siden igen og kalder vores server.

###Why don't we want to submit the traditional way, in a single page application?
- Så vil det teknisk set ikke være en singlepage app hvis vi kontakter serveren hver gang vi trykker på submit.

###Explain in words what it takes to implement the "Controlled Component" pattern for a form
- Vi skal bruge en useState som er vores single source of truth til vores givne information ex et person object.
- Vi skal bruge en event handler som altid holder vores state opdateret med de user inputs der bliver givet.