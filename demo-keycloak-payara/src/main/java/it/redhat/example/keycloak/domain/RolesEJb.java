package it.redhat.example.keycloak.domain;

import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;

@Stateless
public class RolesEJb {

    private static final Logger LOG = Logger.getLogger(RolesEJb.class.getName());

    /**
     * Method to mock Roles lookup
     * @param username usually the Principal provided by the SSO
     * @return Set of role names
     */
    public Set<String> lookupRoles(String username) {

        LOG.info("Assing custom roles to " + username);
        return Set.of("users", "customer", "executive", "standard-role");
    }
}
