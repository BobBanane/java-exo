package fr.eni.gestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection = "Trainers")
public class Formateur {

    @Id
    private String email;

    @Field(name = "lastname")
    @Indexed(unique = true)
    private String nom;

    @Field(name = "firstname")
    @Indexed(unique = true)
    private String prenom;

}
