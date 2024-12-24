package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Inico del filtro");
        //Obtener token de los headers el token viene con la palabra Bearer se debe reemplazar
        var authHeader = request.getHeader("Authorization");

        System.out.println("OBTENIENDO FILTER");
        System.out.println(authHeader);
        //if (token == "" || token== null ){
         //   throw new RuntimeException("El token no viene");
       // }
        if(authHeader != null){
            System.out.println("validamos que token no es NULL");
            var token  = authHeader.replace("Bearer ", "");
            //System.out.println(token);
            //System.out.println(tokenService.getSubject(token));
            var nombreUsuario = tokenService.getSubject(token); //extraeos el username
           // System.out.println(nombreUsuario);
            if( nombreUsuario !=null){
                System.out.println("token valido");
                var usuario = usuarioRepository.findByLogin(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());//FORzamos el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }


        }
        filterChain.doFilter(request,response);



    }


}