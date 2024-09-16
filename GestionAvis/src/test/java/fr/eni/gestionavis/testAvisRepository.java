package fr.eni.gestionavis;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.dal.AvisRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testAvisRepository {

    @Autowired
    private AvisRepository avisRepository;

    @Test
    @Order(1)
    @Commit
    public void testSave() {
        Avis avis = Avis.builder()
                .note(2)
                .commentary("Bonjour")
                .date(LocalDate.now())
                .build();
        Avis avis2 = Avis.builder()
                .note(3)
                .commentary("wow")
                .date(LocalDate.now())
                .build();

        avisRepository.deleteAll();

        Avis savedAvis = avisRepository.save(avis);
        Avis savedAvis2 = avisRepository.save(avis2);
        assertThat(savedAvis.getId()).isNotNull();
        assertThat(savedAvis2.getId()).isNotNull();
        System.out.println(savedAvis);
    }

    @Test
    @Order(2)
    public void testindAllAvis() {

        List<Avis> avis = avisRepository.findAll();
        assertThat(avis).hasSize(2);

    }

}
