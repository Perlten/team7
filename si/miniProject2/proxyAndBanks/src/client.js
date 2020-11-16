const { getOffers } = require("./Proxy")

const readline = require('readline');


const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});



class Client {
    requestOffer() {
        let type = "quick"
        let amount = 40;
        let customer = "Anders";

        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        rl.question("What type of loan would you like", (res) => {
            type = res;
            rl.question("How much would you like to loan", (res) => {
                amount = res;
                rl.question("What is your name", (res) => {
                    customer = res;
                    rl.close();
                    getOffers(type, { amount, customer });
                });
            });
        });
    }
}

module.exports = Client;