//package fr.eni.blog;
//
//import fr.eni.blog.bo.Post;
//import fr.eni.blog.dal.PostRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Commit;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class TestPostEntity {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Test
//    @Commit
//    public void testSavePost() {
//
//        Post post = Post.builder()
//                .title("Formation")
//                .content("blebleble")
//                .author("moi")
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        Post postDB = postRepository.save(post);
//
//        Assertions.assertThat(postDB).isNotNull();
//        Assertions.assertThat(postDB.getTitle()).isEqualTo("Formation");
//    }
//
//    @Test
//    public void testPersistWithEm() {
//
//        Post post = Post.builder()
//                .title("Formation")
//                .content("blebleble")
//                .author("moi")
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        em.persist(post);
//        em.flush();
//
//        // test le persist/flush fonctionne en regardant si l'id est attribué
//        Assertions.assertThat(post.getId()).isNotNull();
//    }
//
//    @Test
//    public void testFindOne() {
//        final Integer id = 1;
//
//        Post post = Post.builder()
//                .title("Formation")
//                .content("blebleble")
//                .author("moi")
//                .createdAt(LocalDateTime.now())
//                .build();
//
////        Optional<Post> postOpt = postRepository.findById(id);
////
////        Assertions.assertThat(postOpt.isPresent()).isTrue();
////        postOpt.ifPresent((p) -> { // <- récup depuis sql server
////            Assertions.assertThat(p.equals(post)).isTrue();
////        });
//    }
//
//    @Test
//    public void testFindAll() {
//        List<Post> posts = postRepository.findAll();
//
//        Assertions.assertThat(posts.size()).isGreaterThan(0);
//        posts.forEach(System.out::println);
//    }
//
//    @Test
//    public void testFinTitle() {
//        final String title = "Formation";
//        List<Post> postOpt = postRepository.findByTitle(title);
//
//        Assertions.assertThat(postOpt.size()).isGreaterThan(0);
//    }
//}
