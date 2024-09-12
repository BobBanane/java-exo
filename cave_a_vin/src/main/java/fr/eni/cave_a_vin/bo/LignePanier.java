package fr.eni.cave_a_vin.bo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="CAV_LINE")
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUANTITE")
    private int quantite;

    @Column(name = "PRICE", precision = 2)
    private float prix;

    public float getTolalPrice() {
        return prix * quantite;
    }
}
