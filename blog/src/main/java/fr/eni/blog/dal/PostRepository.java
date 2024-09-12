package fr.eni.blog.dal;

import fr.eni.blog.bo.Post;
import fr.eni.blog.bo.PostPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, PostPK> {

//    List<Post> findByTitle(String title);

}
