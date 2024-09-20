package fr.eni.tpgestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "REVIEWS")
public class Avis {

    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "note")
    private int note;

    @Field(name = "commentary")
    private String commentaire;

    @Field(name = "date")
    private LocalDateTime date;

    @Field(name = "client")
    private Client client;

    @Field(name = "bouteille")
    private Bouteille bouteille;

}