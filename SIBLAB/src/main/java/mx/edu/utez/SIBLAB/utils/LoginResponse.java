package mx.edu.utez.SIBLAB.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String tokenType = "Bearer";
    private String name;
    private String username;
    private String role;
    private String surname;

    public LoginResponse(Long id, String username, String role, String name, String surname) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.name = name;
        this.surname= surname;
    }
}
