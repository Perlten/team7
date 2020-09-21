const soap = require('soap');
const fetch = require('node-fetch')

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
    updateResponse.json().then(e => console.log(e));

}

async function updateRandomCountry() {
    let country = await fetch("http://localhost:3000/countries")
    updateCountryWithCurrencyAndMajorCity(country);
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
        updateCountryWithCurrencyAndMajorCity(countryRes);

    } catch (e) {
        throw e
    }
}


updateRandomCountry();
// updateCountryBasedOnName("Angola");
