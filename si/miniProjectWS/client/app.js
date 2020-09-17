const soap = require('soap');
const fetch = require('node-fetch')


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


async function start() {
    let response = await fetch("http://localhost:3000/countries")

    let country = await response.json().then(json => json.country);
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
    updateResponse.json().then(e => console.log(e));
}

start();