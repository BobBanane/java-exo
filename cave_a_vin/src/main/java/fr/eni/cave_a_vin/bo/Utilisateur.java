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
@Table(name = "CAV_USER")
public class Utilisateur {

    @Id
    @Column(length = 50, name="LOGIN")
    private String pseudo;

    @ToString.Exclude
    @Column(name="PASSWORD")
    private String password;

    @Column(length = 50, name="FIRST_NAME")
    private String prenom;

    @Column(length = 50, name="LAST_NAME")
    private String nom;

    @Column(length = 20, name="AUTHORITY")
    private String authority;

}
