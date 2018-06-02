# Xml File upload

#### Tasks
Application to process XML File and upload the data into database table and display in the application. XML file should contain multiple entries of student information with attributes - StudentName, DOB, TotalMarks. Display the appropriate validation errors

#### Prerequisite for this project:
1. Java JDK 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Maven  (https://maven.apache.org/install.html)
3. Heroku Account for Deployment. (https://www.heroku.com/)
4. student.xml file for test. Download here (https://github.com/hlaingwintunn/xmlupload/blob/master/src/main/resources/static/data/student.xml)

#### Please go to below link for Application Demo
[Demo](https://xml-upload.herokuapp.com/)

#### How to run project locally
```sh
$ git clone https://github.com/hlaingwintunn/xmlupload.git
$ cd xmlupload/
$ mvn clean package
$ java -jar target/*.jar

- Application can access with 
   http://localhost:8080/
- *Application run on port 8080.Make sure its not in use!*
```

#### Technologies Used
```sh
Java 8
Springboot
Spring Data JPA
Spring RESTful
Spring MVC
Thymeleaf
JQuery DataTables
H2 inmemory Database
TestNG (Testcase)
Packaging (Jar)
Heroku (Deployment)
```

#### Database Web Condole
The application's H2 database web console.
- `http://localhost:8080/console/`
```
 JDBC URL: `jdbc:h2:mem:fmdb`
 User Name: `sa`
 Password:
 ```
