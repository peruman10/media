# SpringBoot Data Redis Example

This project is an example of SpringBoot Data Redis.<br>
Springboot, Redis, Java8

# Technologies

* [Java 8](http://java.oracle.com)
* [gradle 4](https://gradle.org/)
* [spring-boot 2](http://projects.spring.io/spring-boot/)

# Redis library
**build.gradle**

```groovy
    compile('org.springframework.boot:spring-boot-starter-data-redis')
    compile 'org.springframework.data:spring-data-redis'
    compile group: 'redis.clients', name: 'jedis', version: '2.9.0'
    compile group: 'it.ozimov', name: 'embedded-redis', version: '0.7.1'
```

# How to run
You can test by using Swagger or Postman.

**Swagger**
```groovy
    http://localhost:8081/swagger-ui.html
```

# How to run test
The repository contains some test cases.

    ./gradlew test
