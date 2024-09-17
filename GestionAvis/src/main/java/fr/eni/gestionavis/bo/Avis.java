package fr.eni.gestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "REVIEWS")
public class Avis {

    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "note_pedagogie")
    private int notePedagogie;

    @Field(name = "note_cours")
    private int noteCours;

    @Field(name = "commentaire_pedagogie")
    private String commentairePedagogie;

    @Field(name = "Commentaire_cours")
    private String commentaireCours;

    @Field(name = "date")
    private LocalDate date;

    @Field(name = "student")
    private Stagiaire stagiaire;

    @DocumentReference // référence par id
    @Field(name = "trainer_id")
    private Formateur formateur;

    @DBRef /// Strategie 3 : clé composite
    @Field(name = "computer_cours_id")
    private Cours cours;
}
