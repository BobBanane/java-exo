package fr.eni.twitter;

import fr.eni.twitter.bo.Profile;
import fr.eni.twitter.bo.User;
import fr.eni.twitter.dal.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TwitterApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void contextLoads() {
        Profile profile = Profile.builder()
                .firstname("Pierre")
                .lastname("dupond")
                .build();
        User user = User.builder()
                .username("Ddpierre")
                .password("1234")
                .email("email@email.com")
                .profile(profile)
                .dateCreated(LocalDate.now())
                .build();

        Profile profile1 = Profile.builder()
                .firstname("Pierre")
                .lastname("Dupont")
                .build();
        User user1 = User.builder()
                .username("Dtpierre")
                .password("1234")
                .email("email1@email1.com")
                .profile(profile1)
                .dateCreated(LocalDate.now())
                .build();

        User userBdd = userRepository.save(user);
        User userBdd1 = userRepository.save(user1);
        Assertions.assertThat(userRepository.findById(userBdd.getId())).isNotNull();
        Assertions.assertThat(userRepository.findById(userBdd1.getId())).isNotNull();
        System.out.println(userBdd.getProfile());
    }

    @Test
    @Order(2)
    public void testSearchByUsername(){
        String keyword = "pierre";
        List<User> users = userRepository.searchUserByUsername(keyword);
        Assertions.assertThat(users).hasSize(2);
        System.out.println(users);
    }

    @Test
    @Order(3)
    public void testSearchByEmail(){
        // recherche valide
        String keyword = "email1@email1.com";
        Optional<User> userOpt = userRepository.searchUsersByEmail(keyword);
        Assertions.assertThat(userOpt).isPresent();
        System.out.println(userOpt.get());

        // recherche invalide
        keyword = "e@e.com";
        userOpt = userRepository.searchUsersByEmail(keyword);
        Assertions.assertThat(userOpt).isNotPresent();
    }


}
