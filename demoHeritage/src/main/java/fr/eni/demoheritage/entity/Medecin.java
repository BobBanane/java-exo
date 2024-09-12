package fr.eni.demoheritage.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder

@Entity
//@Table( name = "medecins")
@DiscriminatorValue(value = "Médecin")
public class Medecin extends Personne {

    private String specialite;
    private String numMedecin;
    private float tarif;


}
