Creează proiectul cu Spring Initializr
Name: magazin-examen
Language: Java
Type: Maven
Group: com.examen
Artifact: magazin
Vendor: Oracle OpenJDK sau Temurin, versiunea 17 sau 21

Apasă Next.

Acum adăugăm dependențele:
Spring Web (în secțiunea Web)
Thymeleaf (în secțiunea Template Engines)
Spring Data JPA (în secțiunea SQL)
H2 Database (în secțiunea SQL)

pom.xml :
    <dependencies>
        <!-- Spring MVC Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Thymeleaf (template engine) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- H2 - baza de date in-memory -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- ModelMapper - pentru DTO -->
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
        </dependency>
    </dependencies>

main/resouces/application.properties
# Baza de date H2 in-memory
spring.datasource.url=jdbc:h2:mem:magazindb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.sql.init.mode=always

# Consola H2 (optional, util pentru debug)
spring.h2.console.enabled=true

# Port aplicatie
server.port=8080

    


