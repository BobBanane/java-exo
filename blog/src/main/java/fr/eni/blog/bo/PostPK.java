package fr.eni.blog.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PostPK implements Serializable {

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String author;
}
