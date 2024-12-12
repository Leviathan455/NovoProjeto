package br.csi.Animo.infra.security;

import br.csi.Animo.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AutenticacaoFilter<TokenServiceJWT> extends OncePerRequestFilter {
    private TokenServiceJWT tokenServiceJWT;
    private AutenticacaoService autenticacaoservice;

    public void Autenticacaofilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoservice) {
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoservice = autenticacaoservice;
    }

    public AutenticacaoFilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoservice) {
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoservice = autenticacaoservice;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException{
        System.out.println("Filtro para autenticação e autorização");
        String tokenJWT = recuperarToken(request);

        System.out.println("TokenJWT: "+tokenJWT);
        if (tokenJWT!= null){
            String subject = this.tokenServiceJWT.getSubject(tokenJWT);
            System.out.println("Login da req."+ subject);
            UserDetails userDetails = this.autenticacaoservice.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null){
            return token.replace("Bearer", "").trim();
        }
        return null;
    }
}
