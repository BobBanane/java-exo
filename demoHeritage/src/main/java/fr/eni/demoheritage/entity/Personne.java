package fr.eni.demoheritage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity

//TABLE_PER_CLASS -----------------
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//JOINED -----------------
@Inheritance(strategy = InheritanceType.JOINED)

// SINGLE_TABLE -----------------------------
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type")
public abstract class Personne {

    @Id
    //TABLE_PER_CLASS -----------------
    //@GeneratedValue(strategy = GenerationType.TABLE)
    // SINGLE_TABLE \ JOINED-----------------------------
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private LocalDate dateDeNaissance;
    private String email;

}
