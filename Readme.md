# Getting Started

### Run Locally

Set credentials in the shell before starting the app. These credentials are used
for HTTP Basic authentication and are not stored in the repository.

PowerShell:

```powershell
$env:KANBAN_APP_USERNAME = "demo"
$env:KANBAN_APP_PASSWORD = "choose-a-local-password"
& "$PWD\mvnw.cmd" spring-boot:run
```

Bash:

```bash
export KANBAN_APP_USERNAME=demo
export KANBAN_APP_PASSWORD=choose-a-local-password
./mvnw spring-boot:run
```

Send the same credentials with each API request, for example:

```bash
curl -u "$KANBAN_APP_USERNAME:$KANBAN_APP_PASSWORD" http://localhost:8080/api/v1/boards
```

All API routes require the configured account. This is a single-user local demo;
anyone with these credentials can access all boards. HTTP Basic must only be used
over HTTPS outside localhost. CSRF is disabled for this stateless API, so do not
host it publicly or use browser-managed Basic credentials in a production app.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.1/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.1/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
