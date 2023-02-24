package mx.edu.utez.SIBLAB.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String session;
    private String tokenType = "Bearer";
    private String username;

    public LoginResponse(String accessToken, String username) {
        this.session = accessToken;
        this.username = username;
    }
}
