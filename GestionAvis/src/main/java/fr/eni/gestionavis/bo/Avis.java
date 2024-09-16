package fr.eni.gestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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

    @Field(name = "note")
    private int note;

    @Field(name = "commentary")
    private String commentary;

    @Field(name = "date")
    private LocalDate date;

}
