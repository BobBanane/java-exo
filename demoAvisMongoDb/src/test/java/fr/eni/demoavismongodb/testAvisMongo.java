package fr.eni.demoavismongodb;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class testAvisMongo {

    @Autowired
    private AvisRepository avisRepository;

    @Test
    public void savereview() {

        Avis avis = Avis.builder()
                .content("test")
                .author("test")
                .createdAt(LocalDate.now())
                .build();

        Avis savedAvis = avisRepository.save(avis);

        assertThat(savedAvis.getId()).isNotNull();
        System.out.println(savedAvis.getId());
    }

}
