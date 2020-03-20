# ClientServer

Java Client-Server application which transfer objects of clients to server and after that to Postgres DB. 

### Technologies
- Java
- PostrgreSQL

### How to run:
```sh
Firstly to launch class Main.java in Server part. Than to launch class Main.java in Client part
```

### Database description:
Connection is established through JDBC. Postgre database initialized with 3 empty tables
#### Account:
| ID | USERNAME |
| -----------| ------ |
| 1 | Mike |

#### Bill:
| ID | ACCOUNT_ID | BILL |
| -----------| ------ | ------ |
| 1 | 1 | 1000 | 

#### Payment:
| ID | PAYMENT | ACCOUNT_ID |
| -----------| ------ | ------ |
| 1 | 100 | 1 | 


### Usage example:
Interaction between server and client is conducted through the console:

Server side:
```
---INFO---
Start program

---INFO---
Server is deployed

---INFO---
Connection with DataBase is established.
Database ClientServer has 3 tables (Account, Bill, Payment)
Waiting for client connection...
```

Client side:
```
---INFO---
Start program
---INFO---
Client is connected to server 
 
Insert 1 - to create Account, 2 - to make Payment, 3 - to exit program
1
Insert id: 
1
Insert name: 
Mike
Insert bill: 
1000
---INFO---
Account sent to server
```

Server side:
```
---INFO---
Client sent message:
New Account: id = 1, name = Mike, bill = 1000

Server wrote instance in database (1, Mike, 1000)
```

Client side:
```
---INFO---
Server sent message.
Server got instance of Account: New Account: id = 1, name = Mike, bill = 1000

---INFO---
Сlient is connected to server 
 
Insert 1 - to create Account, 2 - to make Payment, 3 - to exit program
2
Insert id: 
1
Insert payment: 
100
---INFO---
Payment sent to server
```

Server side:
```
---INFO---
Client sent message:
New Payment: ClientId = 1, payment = 100
 
Payment completed successfully
Current info: Client with id = 1 has Bill = 900
```

Client side:
```
---INFO---
Server sent message:
Server got instance of Payment:
New Payment: ClientId = 1, payment = 100
 
Payment completed successfully
Current info: Client with id = 1 has Bill = 900
```
