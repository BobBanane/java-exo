package fr.eni.demo_lombok.bo;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Integer id;
    private String name;

    @ToString.Exclude
    private Post post;

}
