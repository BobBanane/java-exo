package fr.eni.demowebservice.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "pseudo")
public class AuthenticationRequest {
    private String pseudo;
    private String password;

}