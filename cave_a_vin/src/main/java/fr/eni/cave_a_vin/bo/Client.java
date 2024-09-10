package fr.eni.cave_a_vin.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}
