const RabbitFacade = require("./rabbitFacade")
const Proxy = require("./Proxy");
const BankJesper = require("./bankJesper");
const BankPerlt = require("./bankPerlt");
const BankDue = require("./bankDue");
const Client =  require('./client')


function main() {
    const jb = new BankJesper();
    const pb = new BankPerlt();
    const db = new BankDue();

    const client = new Client();

    jb.start();
    db.start();
    pb.start();
    
    Proxy.start();
    
    client.requestOffer();
}


main();