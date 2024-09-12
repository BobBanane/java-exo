package fr.eni.comptebanquaire.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name ="Comptes")
@IdClass(ComptePK.class)
public class Compte {

    @Id
    @Column(length = 20, unique = true, nullable = false)
    private String pseudo;
    @Id
    @Column(length = 20, unique = true, nullable = false)
    private String numCompte;
    private Double solde;

}
