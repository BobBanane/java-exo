package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME", length = 100)
    private String nom;
    @Column(name = "SPARKLING")
    private boolean petillant;
    @Column(name = "VINTAGE", length = 100)
    private String millesime;
    @Column(name = "QUANTITY")
    private int quantite;
    @Column(name = "PRICE")
    private float prix;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "REGION_ID")
    private Region region;

}
