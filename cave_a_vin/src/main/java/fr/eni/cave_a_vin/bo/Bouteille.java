package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank( message = "{bouteille.nom.notblank}")
    @Size(max = 100, message = "{bouteille.nom.size}")
    private String nom;

    @Column(name = "SPARKLING")
    private boolean petillant;

    @Column(name = "VINTAGE", length = 100)
    @Size(max = 100, message = "{bouteille.millesime.notblank}")
    private String millesime;

    @Column(name = "QUANTITY")

    private int quantite;
    @Column(name = "PRICE")
    private float prix;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "COLOR_ID")
    @NotNull
    private Couleur couleur;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "REGION_ID")
    @NotNull( message = "{bouteille.region.notnull}")
    private Region region;

}
