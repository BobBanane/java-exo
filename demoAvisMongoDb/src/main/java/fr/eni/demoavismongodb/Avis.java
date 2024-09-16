package fr.eni.demoavismongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "REVIEWS")
public class Avis {

    @Id
    private String id;
    private String author;
    private String content;
    private LocalDate createdAt;

}
