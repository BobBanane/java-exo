package fr.eni.gestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CoursId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reference;
    private String filiere;
}
