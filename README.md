# amex
Before Starting, install mysql in the system and update the application.yml with username and password (I have deleted the password for security reasons)
Step to run the application:
1. Through maven, run the following commands:
    mvn clean install
    mvn spring-boot:run
2. Directly through eclipse or intellij : run the application from app icon, it will be available in localhost:9000
3. running through command line:
   inside the target folder , there will be 0.0.1 snapshot of the application, go there and run :
   java -jar appName.jar (if jdk is instaled seperately without inbuilt from sts or eclipse)

I am using Java 17 for this application, adjust the maven version accordingly if you need to upgrade the code

Curls

Get:
curl --location 'http://localhost:9000/amex/employee/dd4f902e-d3aa-450b-80e0-9087a4da7c4a/details'  

where dd4f902e-d3aa-450b-80e0-9087a4da7c4a is employee ID created
try with wrong ID and you willl get error response

Post:
curl --location 'http://localhost:9000/amex/employee/create' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Asif Ahmed",
"email": "asif.ahmed@gmail.com"
}
'

curl --location --request PUT 'http://localhost:9000/amex/employee/update' \
--header 'Content-Type: application/json' \
--data-raw '{
"id":"dd4f902e-d3aa-450b-80e0-9087a4da7c4a",
"name": "Asif Ahmed",
"email": "asif@gmail.com"
}
'

curl --location --request DELETE 'http://localhost:9000/amex/employee/dd4f902e-d3aa-450b-80e0-9087a4da7c4a/delete'
