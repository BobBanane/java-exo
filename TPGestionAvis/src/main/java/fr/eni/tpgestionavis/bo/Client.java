package fr.eni.tpgestionavis.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Client {

    @Field(name = "username")
    private String pseudo;
    
    @Field(name = "quantity")
    private int quantiteCommandee;

}
