package fr.eni.demowebservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "bonHommes")
public class Bonhomme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull @Size(min = 2, max = 50, message = "Doit faire entre 2 et 50 caratères")
    private String nom;

    @Min(18) @Max(140)
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private Animal animal;
}
