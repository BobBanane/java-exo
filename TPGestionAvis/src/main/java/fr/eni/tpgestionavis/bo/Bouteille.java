package fr.eni.tpgestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection = "bottles")
public class Bouteille {

    @Id
    private BouteilleId id;

    @Field(name = "name")
    private String nom;

}
