package fr.eni.demo_lombok.bo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Post {

    @NonNull
    private Integer id;
    @NonNull
    private String title;
    private String content;
    @NonNull
    private String author;

    private List<Category> categories;

    @ToString.Exclude
    private LocalDateTime createdAt;

}
