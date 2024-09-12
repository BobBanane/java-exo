package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"pseudo"})
@Builder

@Entity
@Table(name="CAV_CLIENT")
public class Client {

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

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

}
