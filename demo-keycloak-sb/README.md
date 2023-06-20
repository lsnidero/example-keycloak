# Application configuration

In order to run this application you need to modify the [application.properties](src/main/resources/application.properties) accordingly to the current SSO configuration.
In particular you need to know:

 - auth server url for the Keycloak installation; something in this format: `http(s)://keycloak.domain.com:port/auth`
 - name of the Keycloak Realm
 - Keycloak client name
 - Keycloak client secret

## Running the application

In order to run and test the application you should run the following command:

```bash
mvn spring-boot:run
```

This command fires up an application which exposes the following links:

  - [/](http://localhost:8080/)
  - [/restricted](http://localhost:8080/restricted)
  - [/standard-role](http://localhost:8080/standard-role)
  - [/logout](http://localhost:8080/logout)

For further informations refer to the main [README](../README.md)

