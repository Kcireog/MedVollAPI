package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //desactivar la etiqueta, para generar un nuevo toquen sin la rstricción de expiración que asignamos (2hrs)
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Obtener el token del header
        var token = request.getHeader("Authorization");
        if(token==null || token.isEmpty()){
            throw new RuntimeException("El token enviado no es valido...");
        }
        token= token.replace("Bearer ", "");
        System.out.println(token);

        //verificar que el subject(login) es un user logueado en el sistema
        System.out.println(tokenService.getSubject(token)); // Este usuario tiene sesion?

        //utilizar la cadena de filtro, para pasar al sguiente filtro el handling de la request
        filterChain.doFilter(request, response);//que el fltro llame al siguiente, para que llegue al controller
    }
}
