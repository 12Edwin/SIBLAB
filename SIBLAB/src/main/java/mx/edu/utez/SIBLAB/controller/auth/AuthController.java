package mx.edu.utez.SIBLAB.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-siblab")
@CrossOrigin(origins = {"*"})
public class AuthController {

    private ObjectMapper objectMapper;

    @GetMapping("/session/")
    public ResponseEntity<?> checkSession(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // la sesión está activa
            return ResponseEntity.ok("La sesión está activa");
        } else {
            // la sesión no está activa
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La sesión no está activa");
        }
    }
}
