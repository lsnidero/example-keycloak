# Demo Keycloak project

This project shows a simple login process toward the Keycloak SSO with OIDC protocol.
The application exposes the following endpoints:

 - `/` the home page, no restrictions
 - `/restricted` and `/logout` accessible only for authenticated users
 - `/standard-role` accessibile only for an authenticated user with a role named `standard-role`

The login form used is the Keycloaks's default one.

## Prerequisites

Here you can find a bill of materials for running this example.

### Keycloak SSO configuration

In order to make your first login you need this minimal configuration (from the Keycloak Web UI):

 - create a new Realm with the Add Realm button on the top left corner
 - create a new Keycloak client with the following configurations (_Configure_ > _Clients_):
   
     - Client Protocol with value  `openid-connect`
     - Valid Redirect URIs with value `http://localhost:8080/*`
     - Web Origins with value `*`
 - save the configuration
 - re-enter in the detail of the client just created and go to the _Credentials_ tab
 - copy the `Secret` in order to use it in the application configuration
 - go to the "Credentials" tab and create a new role named `standard-role`
 - go to _Manage_ > _Users_ > _Add user_ and fill the following fields:
   
     - Username
     - Email
     - First Name
     - Last Name
     - Check the "Email verified" checkbox
     - Save the user
 - re-enter in the user detail just created and go to the "Role Mappings" tab where you need to select, from the "Client Roles" drop down box, the `standard-role` defined for the Keycloak client created few steps ago.


**Please note:** This configuration is not suitable for a production environement!

### Application configuration

In order to run this application you need to know the following  informations:

 - auth server url for the keycloak installation; something with this format: `http(s)://keycloak.domain.com:port/auth`
 - name of the Keycloak Realm
 - Keycloak client name
 - Keycloak client secret

## Running the application

In order to run and test the application refer to these documents:

 - [Readme Spring Boot](./demo-keycloak-sb/README.md)
 - [Readme Java EE](./demo-keycloak-sb/README.md)


