## Team 7 SI miniproject
### Made by Jesper Rusnjerg, Michael Due, Nikolai Perlt
----------

## Summary
In this project you will be able to make requests with either country name or country code, and retrieve information about currency and capital city, using two web services. The first is a third party SOAP service that we use to get the currency and captical imformation. The second service is our own using node and express, this service can give or verify a country code, or country name. 
Included in the program is a simple TUI interface that enables to to interact with the system.

## Program flow
* Call one of three functions.
   - updateRandomCountry()     
   - updateCountryBasedOnName(countryName: string)     
   - updateCountryBasedOnCode(countryCode: string)
* Calls rest and verifies and retrives country.
* Client makes two soap calls one to fetch currency and one to fetch capital city.
* Calls rest service and updates country with city and currency, for future requests.

## Installations instruction
* Tecnical requirements
  - node
  - NPM    
* Server (restServer)
  - npm install 
  - node app.js 
* Client (client)
  - npm install 
  - node app.js
