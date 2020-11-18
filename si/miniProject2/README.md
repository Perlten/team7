# Assignment 2: Banking Operations
## Jesper Rusbjerg, Michael Due, Nikolai Perlt


## Technologies 

- NodeJS
- RabbitMQ

## Tasks
1) makes a loan requests to multiple banks;
2)  collects non-binding bank quotes from (simulated) bank applications; 
3) compares the quotes and selects one, based on its own financial status and criteria

 ## Program flow

Make sure RabbitMQ is running on localhost

Run main.js file

Three banks will start listening for bank requests on the following channels

- mortgage
- student
- quick

The client is able to request loans from the diffrent banks by entering customer name, amount to borrow, type of loan(student/mortgage/quick)

When the form is filled and sent, the banks all retrieve and process the request whereafter they return the interest rate for the given loan to the client and print out the results.