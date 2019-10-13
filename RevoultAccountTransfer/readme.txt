The application uses and inmemory database and its loads predefined accounts numbers 1-10 in the com.revoult.InMemoryAccountDao.
All Accounts are loaded with an initial balance of 100.0
The JUnit test case are in com.revoult.AccountTransferTest and com.revoult.AccountTransferServiceTest which tests api and the REST status response


For application to be used as a standalone application.Run it as an application with com.revoult.server.RestServer as the main class.
The Http server runs on port 8080.

Example of URL Patterns:
1.Transfer from AccountNo:1 to AccountNo:2 for an amount of 10
http://localhost:8080/account/transfer/1/2/10

2.Check Balance of Account-1
http://localhost:8080/account/balance/1

