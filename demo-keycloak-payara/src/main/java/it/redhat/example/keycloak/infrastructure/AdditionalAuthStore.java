package it.redhat.example.keycloak.infrastructure;

import java.util.Collections;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import it.redhat.example.keycloak.domain.RolesEJb;

@ApplicationScoped
public class AdditionalAuthStore implements IdentityStore {

    @Inject
    private RolesEJb roles;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return roles.lookupRoles(validationResult.getCallerPrincipal().getName());
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return Collections.singleton(ValidationType.PROVIDE_GROUPS);
    }

}
