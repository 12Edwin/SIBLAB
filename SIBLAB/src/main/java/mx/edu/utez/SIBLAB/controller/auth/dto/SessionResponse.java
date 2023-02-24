package mx.edu.utez.SIBLAB.controller.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SessionResponse {
    private String message;
    private Boolean session;

}
