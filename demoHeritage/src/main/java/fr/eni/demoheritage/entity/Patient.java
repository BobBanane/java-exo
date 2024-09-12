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
//@Table(name = "patients")
@DiscriminatorValue(value = "Patient")
public class Patient extends Personne{

    private String nss;

}
