package fr.eni.cave_a_vin.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
@SuperBuilder

@Entity
@Table(name="CAV_OWNER")
public class Proprietaire extends Utilisateur {

    @Column(length = 20, name="CLIENT_NUMBER")
    private String siret;

}
