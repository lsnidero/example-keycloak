# Application configuration

In order to run this application you need to modify the [keycloak.json](src/main/webapp/WEB-INF/keycloak.json) accordingly to the current SSO configuration.
In particular you need to know:

 - auth server url for the Keycloak installation; something in this format: `http(s)://keycloak.domain.com:port/auth`
 - name of the Keycloak Realm
 - Keycloak client name
 - Keycloak client secret

## Running the application

In order to run and test the application you should deploy the `demo-keycloak.war` file generated in the target folder by doing:

```bash
mvn package
```


This command fires up an application at the context `/demo-keycloak` which exposes the following links (though Java Servlets):

  - [/demo-keycloak](http://localhost:8080/demo-keycloak)
  - [/demo-keycloak/restricted](http://localhost:8080/demo-keycloak/restricted)
  - [/demo-keycloak/standard-role](http://localhost:8080/demo-keycloak/standard-role)
  - [/demo-keycloak/logout](http://localhost:8080/demo-keycloak/logout)

For further informations refer to the main [README](../README.md)

