const RabbitFacade = require('./rabbitFacade')



function start() {
    const rb = new RabbitFacade();

    rb.subscribe("bankResponse", (res) => {
        console.log(res);
    })
}

function getOffers(type, body) {
    const rb = new RabbitFacade();
    rb.send(type, body);
}


module.exports = {getOffers, start};