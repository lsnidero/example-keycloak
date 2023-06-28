# Application configuration

In order to run this application you need to modify the  values of the `@OpenIdAuthenticationDefinition` annotation on [SecurityBean.java](src/main/java/it/redhat/example/keycloak/infrastructure/SecurityBean.java) accordingly to the current SSO configuration.

In particular you need to know:

 - auth server url for the Keycloak installation; something in this format: `http(s)://keycloak.domain.com:port/auth`
 - name of the Keycloak Realm
 - Keycloak client name
 - Keycloak client secret

The connection between the SSO and the Java EE Security subsystem is done by configuring the [@OpenIdAuthenticationDefinition annotation](https://docs.payara.fish/enterprise/docs/Technical%20Documentation/Public%20API/OpenID%20Connect%20Support.html).

The custom group binding is overridden by the [AdditionalAuthStore.java](src/main/java/it/redhat/example/keycloak/infrastructure/AdditionalAuthStore.java) java class.


## Running the application

In order to compile the application you should run the following command:

```bash
./mvnw clean package
```

This command fires up an application which exposes the following links:

  - [/demo-keycloak-payara/](http://localhost:8080/demo-keycloak-payara)
  - [/demo-keycloak-payara/unsecured](http://localhost:8080/demo-keycloak-payara/unsecured)
  - [/demo-keycloak-payara/restricted](http://localhost:8080/demo-keycloak-payara/restricted)
  - [/demo-keycloak-payara/standard-role](http://localhost:8080/demo-keycloak-payara/standard-role)
  - [/demo-keycloak-payara/logout](http://localhost:8080/demo-keycloak-payara/logout)
  

For further information refer to the main [README](../README.md)

