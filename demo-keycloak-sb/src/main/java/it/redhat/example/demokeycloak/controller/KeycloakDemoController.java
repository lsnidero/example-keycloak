package it.redhat.example.demokeycloak.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class KeycloakDemoController {

    private static final String htmlTemplate = """
            <!DOCTYPE html>
                <html>
                    <head>
                        <title>Keycloak example</title>
                    </head>

                <body>
                    ${body}
                </body>

                </html>
            """;

    @GetMapping("/")
    public String root() {

        StringBuilder body = new StringBuilder("<h1>Welcome to home screen</h1> Here a list of available links")
        .append("<ul>")
        .append("<li><a href=\"/standard-role\">User with Standard role</a>: You can access to this section only if you have a <strong>standard-role</strong></li>")
        .append("<li><a href=\"/restricted\">Authenticated User</a>: You can access to this section only if you are <i>simply</i> autehnticated</li>")
        .append("<li><a href=\"/logout\">Logout</a>: Click here to logout</li>")
        .append("</ul>");

        return htmlTemplate.replace("${body}", body);
    }

    @GetMapping("/standard-role")
    @RolesAllowed("standard-role")
    public String withStandardRole(Principal principal) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = token.getAccount().getKeycloakSecurityContext().getToken();

        StringBuilder body = new StringBuilder("<h1>Welcome to standard page.</h1> This user has a <strong>standard-role</strong> <br/><strong>")
                .append(accessToken.getFamilyName())
                .append("</strong> <strong>")
                .append(accessToken.getName())
                .append("</b >:(<strong>")
                .append(accessToken.getPreferredUsername())
                .append("</strong>) <br/>successfully logged in. <br/><a href=\"/\">Return to Home page</a>");

        return htmlTemplate.replace("${body}", body);
    }

    @GetMapping("/restricted")
    public String withAuthentication(Principal principal) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = token.getAccount().getKeycloakSecurityContext().getToken();

        StringBuilder body = new StringBuilder("<h1>Welcome to restricted page.</h1> This user is <i>only</i> authenticated.<br/><strong>")
                .append(accessToken.getFamilyName())
                .append("</strong> <strong>")
                .append(accessToken.getName())
                .append("</b >:(<strong>")
                .append(accessToken.getPreferredUsername())
                .append("</strong>) <br/>successfully logged in. <br/><a href=\"/\"> Return to Home page</a>");

        return htmlTemplate.replace("${body}", body);
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {
        request.logout();

        return new ModelAndView("redirect:/");
    }
}
