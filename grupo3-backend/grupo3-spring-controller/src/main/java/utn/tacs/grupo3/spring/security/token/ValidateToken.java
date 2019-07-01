package utn.tacs.grupo3.spring.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

public class ValidateToken {
    // Método para validar el token enviado por el cliente
    public Authentication getAuthentication(HttpServletRequest request) {
        String url = request.getRequestURI();

        String token = validateAccessToResources(request, url);
        String user = getUserNameFromToken(token);


        return user != null ?
                new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                null;
    }


    private String validateAccessToResources(HttpServletRequest request, String url) {
        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");
        // Divido el endpoint en recursos
        String[] recurso = url.split("/");

        switch (recurso[1]) {
            case "users":
                String userName = getUserNameFromToken(token);
                // el recurso[2] es: /{user-id}
                if (!recurso[2].equals(userName)) {
                	if (!recurso[2].equals("me")) {
                		token = null;						
					}
                }

                break;

            case "administrator":
                String rol = getRolFromToken(token);
                if (!rol.equals("ADMIN")) {
                    token = null;
                }
                break;

            default:
                break;

        }
        return token;
    }

    public String getUserNameFromToken(String token) {
        if (token != null) {
            return Jwts.parser()
                    .setSigningKey("P@tit0")
                    .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

        }
        return null;
    }

    public String getRolFromToken(String token) {
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey("P@tit0")
                    .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                    .getBody();
            return claims.get("ROL").toString();
        }
        return null;
    }

}
