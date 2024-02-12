title: fintech_readme
---
## fintech

- swagger

you can see CRUD service which is related to Category Entity and Expense Entity and User Entity and also I add three Api for getting report by Expense

I config Authentication with JWT and therefore at first when you should Open the address [http://localhost:8080/fintech/swagger-ui/index.html#/]  in your browser and you should singup which cause to create user and get jwt token afterthat you shouls add token into header of services which you want to call

after successfully signup, existence user can use signin service and get token 
 
primary Service is SaveExpense, at first you should call signin and get token and afterthat create category and finaly you should save you expenses

purpose of application is calculation and getting report which related service there are in the ReportController

- docker

First,start [mvn clean package install] 

Second, added DockerFile in the  [src\main\compose\docker\Dockerfile]  and you should open your terminal and afterThat write [ docker build -t fintech . ]  command 
which cause creation application

and also i have added docker compose file in the [src\main\compose\docker-compose.yml]  directory and you can use [ docker-compose up ] command for pick up some services such as fintech application and mysql and monitoring tools(prometheus , grafana) and logg management with ELK 

you can see metrics in the [http://localhost:8080/fintech/actuator/prometheus] 