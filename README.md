This is basic backend service to control an oven. Every oven contains 4 hotplates. 
For each hotplate can be setted power level.
Application started on port 8080. 

There are some commands, that can be executed through curl:
-   Create new oven (Example: curl --location --request POST 'localhost:8080/ovens')
-   Get information about all ovens (Example: curl --location --request GET 'localhost:8080/ovens')
-   Get information about concrete oven (Example: curl --location --request GET 'localhost:8080/ovens/1') 
-   Get information about concrete hotplate on oven (Example: curl --location --request GET 'localhost:8080/ovens/1/1')
-   Delete oven (Example: curl --location --request DELETE 'localhost:8080/ovens/1')
-   Set power level for hotplate (Example: curl PUT 'localhost:8080/ovens/1/1/2')
-   Turn off oven, setted power level to 0 for every hotplate (Example: curl PUT 'localhost:8080/ovens/1')