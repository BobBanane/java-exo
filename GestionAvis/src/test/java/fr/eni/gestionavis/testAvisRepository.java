package fr.eni.gestionavis;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.dal.AvisRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
                .notePedagogie(2)
                .commentairePedagogie("Bonjour")
                .noteCours(2)
                .commentaireCours("Bonjour")
                .date(LocalDate.now())
                .build();
        Avis avis2 = Avis.builder()
                .notePedagogie(3)
                .commentairePedagogie("wow")
                .noteCours(2)
                .commentaireCours("wow")
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
