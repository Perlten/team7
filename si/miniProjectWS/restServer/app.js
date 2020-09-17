const express = require("express");
const countries = require("./countries")

const bodyParser = require('body-parser');


const app = express();
app.use(bodyParser.json());

const PORT = 3000;

app.get("/countries", (req, res) => {
    var country = countries[Math.floor(Math.random() * countries.length)];
    res.json({ country: country.code });
})


app.put("/updateCountry", (req, res) => {
    let countryMeta = req.body;
    let code = countryMeta.code;

    let country = countries.find(e => e.code === code);
    country.captital = countryMeta.capital;
    country.currency = countryMeta.currency;
    res.json(country);
})

app.listen(PORT, () => {
    console.log("Server started on port " + PORT)
})