# Mini project BPMN

By Jesper, Nikolai and Michael Due

XML Handin can be found in the bankTransfer.bpmn file

We have a created a BPMN flow for our money transaction operation

Succes Flow shortly explained:
We have integration between our Customer, Login service, Bank service, Logging service

- A user will firstly need to verify himself when logging into the bank
- The customer then is able to select an account and the funds he wishes to transfer
- Bank service must verify the amount of funds the customer wishes to transfer
- Customer then must select destination account
- Bank service must verify destination account
- Money is transfered

Errors which can occour:
- Customer enters wrong login details x3 -> Customer gets temp banned & gets logged in our logging service
- Insufficient funds -> Customer is able to try again
- Destination account does not exist -> Customer is able to try again

Visual handin:
![Visual handin](diagram.svg)
