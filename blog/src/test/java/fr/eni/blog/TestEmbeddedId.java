package fr.eni.blog;

import fr.eni.blog.bo.Post;
import fr.eni.blog.bo.PostPK;
import fr.eni.blog.dal.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class TestEmbeddedId {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void test() {
        PostPK postPk = PostPK.builder()
                .title("Formation")
                .author("moi")
                .build();

        Post post = Post.builder()
                .id(postPk)
//                .title("Formation")
                .content("blebleble")
//                .author("moi")
                .createdAt(LocalDateTime.now())
                .build();

        Post postsaved = postRepository.save(post);
        Assertions.assertThat(postsaved).isNotNull();

        Optional<Post> p = postRepository.findById(postPk);
        Assertions.assertThat(p).isPresent();
    }
}
