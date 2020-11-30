var amqp = require('amqplib/callback_api');

class RabbitFacade {



    subscribe(subject, callback) {
        amqp.connect('amqp://localhost', function (error0, connection) {
            if (error0) {
                throw error0;
            }
            connection.createChannel(function (error1, channel) {
                if (error1) {
                    throw error1;
                }
                var exchange = subject;

                channel.assertExchange(exchange, 'fanout', {
                    durable: false
                });

                channel.assertQueue('', {
                    exclusive: true
                }, function (error2, q) {
                    if (error2) {
                        throw error2;
                    }
                    channel.bindQueue(q.queue, exchange, '');

                    channel.consume(q.queue, function (msg) {
                        if (msg.content) {
                            callback(JSON.parse(msg.content.toString()));
                        }
                    }, {
                        noAck: true
                    });
                });
            });
        });
    }


    send(subject, body) {
        amqp.connect('amqp://localhost', function (error0, connection) {
            if (error0) {
                throw error0;
            }
            connection.createChannel(function (error1, channel) {
                if (error1) {
                    throw error1;
                }
                var exchange = subject;
                var msg = body;

                channel.assertExchange(exchange, 'fanout', {
                    durable: false
                });
                channel.publish(exchange, '', Buffer.from(JSON.stringify(msg)));
                // console.log(" [x] Sent %s", msg);
            });

            // setTimeout(function () {
            //     connection.close();
            //     process.exit(0);
            // }, 500);
        });
    }
}

module.exports = RabbitFacade;