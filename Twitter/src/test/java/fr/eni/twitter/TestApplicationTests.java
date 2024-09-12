package fr.eni.twitter;

import fr.eni.twitter.bo.Profile;
import fr.eni.twitter.bo.User;
import fr.eni.twitter.dal.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        Profile profile = Profile.builder()
                .firstname("Bob")
                .lastname("Banane")
                .build();

        User user = User.builder()
                .username("Bob")
                .password("sdf5sd4f6s5d4f")
                .email("sdfsdf@sfs.sdf")
                .profile(profile)
                .build();

        User userSaved = userRepository.save(user);
        //Test seulement user pour tester le chargement de profile
        Assertions.assertThat(userSaved).isNotNull();
        System.out.println(userSaved.getProfile());
    }
}
