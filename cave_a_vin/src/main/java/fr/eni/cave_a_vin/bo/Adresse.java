package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "CAV_ADDRESS")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADREESE_ID", nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "STREET", length = 100, nullable = false)
    private String rue;

    @NonNull
    @Column(name = "POSTAL_CODE", length = 100, nullable = false)
    private String codePostal;

    @NonNull
    @Column(name = "CITY", length = 100, nullable = false)
    private String ville;

}
