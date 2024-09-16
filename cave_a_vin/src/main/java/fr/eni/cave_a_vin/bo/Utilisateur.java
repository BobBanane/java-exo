package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"pseudo"})
@SuperBuilder

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    @Column(length = 50, name="PSEUDO")
    private String pseudo;

    @ToString.Exclude
    @Column(length = 50, name="PASSWORD")
    private String password;

    @Column(length = 50, name="FIRST_NAME")
    private String prenom;

    @Column(length = 50, name="LAST_NAME")
    private String nom;

}
