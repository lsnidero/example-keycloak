package it.redhat.example.keycloak.application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fish.payara.security.openid.api.OpenIdContext;

/**
 *
 * @author Mariusz Batyra
 */
public class SecuredServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SecuredServlet.class.getName());

    @Inject
    private OpenIdContext context;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.log(Level.INFO, "Access to secured servlet granted!");

        println(response, "Access to secured servlet granted");
        println(response, "remote user  " + request.getRemoteUser());

        context.getAccessToken()
                .getJwtClaims()
                .getIssuer()
                .ifPresent(i -> println(response, "Token issuer : " + i));
        context.getClaims()
                .getPreferredUsername()
                .ifPresent(u -> println(response, "Username :" + u));
    }

    private void println(HttpServletResponse response, String value) {
        try {
            response.getWriter().println(value);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error writing response", e);
        }

    }

}