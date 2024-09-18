package fr.eni.demowebservice.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder

//@Entity @Table(name = "crayons")
public class Crayon {

    private Integer id;

    private String couleur;
    private String type;

}
