package mx.edu.utez.SIBLAB.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Obtener los detalles del usuario autenticado
        String username = authentication.getName();
        // Generar el token de acceso
        String accessToken = "Logged";

        // Crear una instancia de LoginResponse con los datos obtenidos
        LoginResponse loginResponse = new LoginResponse(accessToken, username);

        // Convertir la instancia de LoginResponse a un objeto JSON
        String loginResponseJson = objectMapper.writeValueAsString(loginResponse);

        // Configurar la respuesta HTTP con el objeto JSON de LoginResponse
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(loginResponseJson);
    }
}
