package fr.eni.demo_lombok;

import fr.eni.demo_lombok.bo.Category;
import fr.eni.demo_lombok.bo.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
public class TestBoPost {


    @Test
    public void testBoPost() {

        Category category = Category.builder()
                .id(1)
                .name("Tutorial")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("How to to")
                .content("to smf,dqklnsdkfg")
                .author("Moi")
                .categories(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        post.getCategories().add(category);
        category.setPost(post);

        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post.getId()).isEqualTo(1);
        Assertions.assertThat(post.getTitle()).isNotBlank();
        Assertions.assertThat(post.getCategories().size()).isGreaterThan(0);

        Assertions.assertThat(category.getPost()).isNotNull();

        System.out.println(post);

    }
}
