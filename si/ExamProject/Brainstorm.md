# Brainstorm
By Nikolai Perlt, Jesper Rusbjerg & Michael Due Pedersen

[Google Docs](https://docs.google.com/document/d/1NzzidEb_CEyP0DWycolHPa2sPdBabaubv7AZZqTCvdA/edit#)

## Emner der skal inkluderes

- RPC
  - Remote procedure call (calls function remote)

- REST
  - Its REST 

- SOAP
  -  Kinda like rest but able to call remote procedures and sets a more strict format in form of a WSDL file 

- BPMN / UML

- SOA (System-Oriented Architecture)
  - Pretty much microservices 
    - A service has four properties according to one of many definitions of SOA
        1) It logically represents a business activity with a specified outcome.
        2) It is self-contained.
        3) It is a black box for its consumers, meaning the consumer does not have to be aware of the service's inner workings.
        4) It may consist of other underlying services. 


- ESB (Enterprise Service Bus)
  - https://www.youtube.com/watch?v=VHzWswQNtgk&ab_channel=LoiLiangYang
  - A layer between all your microservices 

- EIP (Enterprise Integration Patterns)
  - https://datsoftlyngby.github.io/soft2020fall/resources/74159037-Session7EIP.pdf
  - Seems to be ways to create enterprise level integration 

- JAX-WS
  - Its a way to create and consume soap web services

- MOM (message oriented middleware)
  - Enables services to subscribe or send message within a certain subject
    - RabbitMQ
    - Kafka

- Zookeeper 
  - Monitors services.
  - It is able to restart services if the go down.
  - Is able to set up more instances of a service if needed.
- XML / JSON
  - A format to send data

## Idéer
- Momondo for banker

## System arkitektur - Microservices
"Momondo"
- Login (admin)
- Logging
- Statistik (Involvere hentning af data fra logging)
- Proxy
- Frontend
- Valuta omregner
- Mail/Phone service - Redirect to "Flyselvskaberne"
- SWAPI

"Flyselvskaberne"
- PerltBank
- JesperBank
- DueBank

