package fr.eni.cave_a_vin.bo;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="CAV_SHOPPING_CART")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ORDER_NUMBER")
    private String numCommande;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private float prixTotal;

    @Column(name = "PAYED")
    private boolean paye;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private List<LignePanier> lignesPanier = new ArrayList<>();

    public void addLignePanier(LignePanier lignePanier) {
        this.setPrixTotal(this.getPrixTotal() + lignePanier.getTolalPrice());
        this.lignesPanier.add(lignePanier);
    }

}
