# Miniproject 4
### Jesper Rusbjerg, Michael Due, Nikolai Perlt

In this project we have worked with 4 different microservices. Theses we have create individual docker images and made a yaml file to enable deployment with kubernetes.

## Microservices Discovery Server (MDS)
    This service allows other services to connect to transfer data trough this component with Eureka. It is written i java, and is exposed on docker with port 8761 and kubernetes on port 30004

## Car Catalog Microservice (CCM)
    This service responsibility is to manage car with the properties 
        - brand
        - year
        - km
    we use h2 sql database to save and manage the cars.
    We expose the data with REST. On docker this is done on port 8090 and kubernetes on port 30001

## Car Gateway Microservice (CGM)
    This service allows you til filter the cars coming from the CCM service. The only filter so far is a filter done by year. The service run on port 8080 on docker and 30002 on kubernetes.

## User Review Microservice
    This service saves user and users rating on car brands, it is exposed trough rest and runs on port 8070 on docker and 30003 on kubernetes.


## Docker 
    Each microservices has a prebuilt docker image
        - Microservices Discovery Server = perlt/mds  
        - Car Catalog Microservice = perlt/ccm  
        - Car Gateway Microservice = perlt/cgm  
        - User Review Microservice = perlt/user-review  


## Kubernetes
    Run kubectl apply -f cluster/deploy.yaml
    this wil spawn all the microservices and allow you to access them on port 
        - 30001 (CCM)
        - 30002 (CGM)
        - 30003 (user review)
        - 30004 (MDS)
