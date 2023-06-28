package it.redhat.example.keycloak.application;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fish.payara.security.openid.api.OpenIdConstant;
import fish.payara.security.openid.api.OpenIdContext;

public class CallbackServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CallbackServlet.class.getName());

    @Inject
    private OpenIdContext context;

    @Inject
    private SecurityContext security;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("###############################################")
                .append("\n")
                .append("Token: " + context.getAccessToken())
                .append("\n")
                .append("Json Claims: " + context.getClaimsJson())
                .append("\n");
        Principal securityPrincipal = security.getCallerPrincipal();
        if (securityPrincipal != null) {
            sb.append("security.getCallerPrincipal().getName(): " + securityPrincipal.getName());
        }
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            sb.append("userPrincipal.getName()" + userPrincipal.getName());
        }
        sb.append("###############################################");
        LOG.info(sb.toString());

        response.sendRedirect(request.getSession().getAttribute(OpenIdConstant.ORIGINAL_REQUEST).toString());
    }

}