package it.redhat.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

@WebServlet("/standard-role")
public class StandardRoleServlet extends HttpServlet {

    private static final String htmlTemplate = new StringBuilder()
            .append("<!DOCTYPE html>")
            .append("<html>")
            .append("<head>")
            .append("  <title>Keycloak example</title>")
            .append("</head>")
            .append("  <body>")
            .append("  ${body}")
            .append("  </body>")
            .append("</html>").toString();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        KeycloakSecurityContext ctx = (KeycloakSecurityContext) req
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = ctx.getToken();
        
        if (!req.isUserInRole("standard-role")){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for the authenticated user " + accessToken.getPreferredUsername() + " in this section. It does not have the standard-role role");
            return;
        }


        StringBuilder body = new StringBuilder(
                "<h1>Welcome to standard page.</h1> This user has a <strong>standard-role</strong> <br/><strong>")
                .append(accessToken.getFamilyName())
                .append("</strong> <strong>")
                .append(accessToken.getName())
                .append("</strong>:(<strong>")
                .append(accessToken.getPreferredUsername())
                .append("</strong>) <br/>successfully logged in. <br/><a href=\"/demo-keycloak\">Return to Home page</a>");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(htmlTemplate.replace("${body}", body));
    }

}
