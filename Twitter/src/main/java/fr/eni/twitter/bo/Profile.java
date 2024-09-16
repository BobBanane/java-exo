package fr.eni.twitter.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String bio;
    private LocalDate birthday;

    @ToString.Exclude
    @OneToOne(mappedBy = "profile")
    private User user;

}
