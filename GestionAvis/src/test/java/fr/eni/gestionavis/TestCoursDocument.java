package fr.eni.gestionavis;

import fr.eni.gestionavis.bo.Cours;
import fr.eni.gestionavis.bo.CoursId;
import fr.eni.gestionavis.dal.CoursRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestCoursDocument {

    @Autowired
    CoursRepository coursRepository;

    @Test
    void test_save_cours() {
        CoursId coursId = CoursId
                .builder()
                .reference("M360")
                .filiere("Développement")
                .build();

        Cours cours = Cours
                .builder()
                .id(coursId)
                .titre("Java Frameworks - API Web")
                .duree(10)
                .build();

        coursRepository.save(cours);

//Vérification en base
        Optional<Cours> opt = coursRepository.findById(coursId);
        assertThat(opt).isNotNull();
        assertThat(opt.isPresent()).isTrue();

        Cours coursDB = opt.get();
        assertThat(coursDB).isNotNull();
        assertThat(coursDB.getTitre()).isNotNull();
        assertThat(coursDB.getDuree()).isEqualTo(10);

        System.out.println(coursDB.toString());
    }

}