package utn.tacs.grupo3.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import utn.tacs.grupo3.spring.converter.JsonResponseConverter;
import utn.tacs.grupo3.spring.security.token.ValidateToken;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {


        Authentication authentication = new ValidateToken().getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication == null && !(((HttpServletRequest) request).getRequestURI().equals("/sign-up"))) {
            setUnauthorizedResponse((HttpServletResponse) response);
            return;
        }
                	
        filterChain.doFilter(request,response);
    }

    public void setUnauthorizedResponse(HttpServletResponse response) {
        new JsonResponseConverter().convert(response,HttpServletResponse.SC_FORBIDDEN,HttpStatus.FORBIDDEN,"you are not allowed to access this resource","");
    }
}