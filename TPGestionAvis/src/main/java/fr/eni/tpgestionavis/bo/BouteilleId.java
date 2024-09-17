package fr.eni.tpgestionavis.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BouteilleId implements Serializable {
    public static final long serialVersionUID = 1L;

    private int idBouteille;
    private int idRegion;
    private int idCouleur;


}
