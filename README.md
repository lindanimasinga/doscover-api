# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


### API Live Demo

To see a live demo for this api please visit the swagger docs i have provided on this link

http://assessment-env-1.eba-jsk3jput.us-east-1.elasticbeanstalk.com/swagger-ui.html

This API is being used by an angular web application I have build and hosted on S3 on this URL

https://curiousoft-bucket.s3.amazonaws.com/index.html


### Live demo hosting info
DicoverIt API hosted as a java rest API behind the AWS API gateway

To learn more about aws services i have used please visit
https://aws.amazon.com/elasticbeanstalk/
https://aws.amazon.com/s3/
https://aws.amazon.com/api-gateway/

### Running The API Locally

Please ensure you have the following setup
- Java 8 or higher
- Tomcat 8.5
- Postman

please download a file named descoverit.war from this repository and you will deploy this file inside your running tomcat.

once deployed, Your API should be running on this address. http://localhost:8080/doscoverit


### Testing with postman
Please download a file called swagger.yml and import it into postman to be able to test all the endpoints

Thanks,

