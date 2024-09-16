package fr.eni.cave_a_vin.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@SuperBuilder

@Entity
@Table(name="CAV_CLIENT")
public class Client extends Utilisateur {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

}
