package fr.eni.blog.dal;

import fr.eni.blog.bo.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByTitle(String title);

}
