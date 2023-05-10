# Bank Challenge

## How to run it

```bash
# Locally
$ ./gradlew bootRun

```

`bank_api.postman_collection.json` to import use case
examples.

## Diagrams

### Architecture

![Architecture diagram](files/diagrams/ArquitectureDiagram.png)

### Postgres Diagram

![Postgres diagram](files/diagrams/PostgresDiagram.png)

### Dbeaver diagram

![Dbeaver diagram](files/diagrams/DiagramDbeaver.png)

# Scheduler

this scheduler will be running periodically and will also generate random status for transactions processing, then
it will change the status in database according its final status 

![Scheduler](./files/demo/Scheduler.png)

### Random Status

![RandomStatus](./files/demo/RandomStatus.png)

### Swagger Integration

`http://localhost:8082/swagger-ui/index.html` to see

![Swagger](./files/SwaggerIntegration.png)

## Examples

### Successful Creation Recipient Bank Account

![Successful Creation Recipient](files/demo/SuccessCreationRecipientBankAccount.png)

### Get User Recipient Bank Account

![Get User Recipient Bank Account](files/demo/GetUserRecipientBank.png)

### Success Payment Transaction

![Success Transaction](files/demo/SuccessTransaction.png)


### Successful Get Payments

![Successful Get Payments](files/demo/SuccessGetPayments.png)

### Errors

![Error Logic Business](files/demo/LogicBusiness.png)

![Bad Request](files/demo/BadRequest.png)

![Internal Server](files/demo/InternalServerEror.png)

![Not Found Exception](files/demo/NotFoundException.png)

