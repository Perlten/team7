const soap = require('soap');
const fetch = require('node-fetch')
const readline = require("readline");

function queryParamsMaker(params) {

    let query = Object.keys(params)
        .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
        .join('&');

    return query
}

function getCapital(countryCode) {
    var url = 'http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL';
    return new Promise((resolve, reject) => {
        var args = { sCountryISOCode: countryCode };
        soap.createClient(url, function (err, client) {
            client.CapitalCity(args, function (err, result) {
                resolve(result.CapitalCityResult);
            });
        });
    })
}


function getCurrency(countryCode) {
    var url = 'http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL';
    return new Promise((resolve, reject) => {
        var args = { sCountryISOCode: countryCode };
        soap.createClient(url, function (err, client) {
            client.CountryCurrency(args, function (err, result) {
                resolve(result.CountryCurrencyResult.sName);
            });
        });
    })
}

async function updateCountryWithCurrencyAndMajorCity(countryInput) {

    let country = await countryInput.json()
        .then(json => json.country);

    let capital = await getCapital(country);
    let currency = await getCurrency(country);

    let options = {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({ code: country, capital, currency })
    };

    let updateResponse = await fetch("http://localhost:3000/updateCountry", options);
    let res = await updateResponse.json()
    return res
}

async function updateRandomCountry() {
    let country = await fetch("http://localhost:3000/countries")
    let res = await updateCountryWithCurrencyAndMajorCity(country);
    return res
}

async function updateCountryBasedOnName(country) {
    try {
        let params = {
            "countryName": country
        };
        let url = 'http://localhost:3000/country?' + queryParamsMaker(params);

        let countryRes = await fetch(url)

        if (countryRes.status != 200) {
            let json = await countryRes.json();
            throw new Error(json.error)
        }
        let res = await updateCountryWithCurrencyAndMajorCity(countryRes);
        return res;
    } catch (e) {
        throw e
    }
}
async function updateCountryBasedOnCode(countryCode) {
    try {
        let params = {
            "countryCode": countryCode
        };
        let url = 'http://localhost:3000/countryCode?' + queryParamsMaker(params);

        let countryRes = await fetch(url)

        if (countryRes.status != 200) {
            let json = await countryRes.json();
            throw new Error(json.error)
        }
        let res = await updateCountryWithCurrencyAndMajorCity(countryRes);
        return res;
    } catch (e) {
        throw e
    }
}



async function runProgram() {
    console.log("Welcome to the program")
    while (true) {
        try {

            console.log("------------------------------");
            console.log("Press 0 to quit")
            console.log("Press 1 to update random country")
            console.log("Press 2 to update country by country code")
            console.log("Press 3 to update country by country name")
            console.log("------------------------------");
            let userInput = await getInput("");

            if (userInput === "1") {
                await updateRandomCountry();
            } else if (userInput === "2") {
                let countryCode = await getInput("Country code: ")
                let response = await updateCountryBasedOnCode(countryCode);
                console.log(response);
            } else if (userInput === "3") {
                let countryName = await getInput("Country name: ")
                let response = await updateCountryBasedOnName(countryName);
                console.log(response);
            } else if (userInput === "0") {
                break;
            } else {
                continue
            }

        } catch (e) {
            console.log(e.message);
        }
    }
}

async function getInput(question) {
    return new Promise((resolve, reject) => {
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        rl.question(question, (input) => {
            resolve(input)
            rl.close();
        });
    })
}

runProgram();