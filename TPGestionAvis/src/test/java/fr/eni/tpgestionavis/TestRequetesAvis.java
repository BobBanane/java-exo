package fr.eni.tpgestionavis;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Bouteille;
import fr.eni.tpgestionavis.bo.BouteilleId;
import fr.eni.tpgestionavis.dal.AvisRepository;
import fr.eni.tpgestionavis.dal.BouteilleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestRequetesAvis {

    @Autowired
    AvisRepository avisRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    public void test01_avis_notes_less_than() {
        final int note = 3;
        final int nbAvis = 3;

        List<Avis> avisList = avisRepository.findByNoteLessThan(note);

        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);
        avisList.stream()
                .map(Avis::getNote)
                .forEach(n -> assertThat(n).isLessThan(note));

        avisList.forEach(System.out::println);
    }

    @Test
    public void test02_avis_notes_greater_than_equals() {
        final int note = 3;
        final int nbAvis = 6;

        List<Avis> avisList = avisRepository.findByNoteGreaterThanEqual(note);

        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);
        avisList.stream()
                .map(Avis::getNote)
                .forEach(n -> assertThat(n).isGreaterThanOrEqualTo(note));

        avisList.forEach(System.out::println);
    }

    @Test
    public void test03_avis_by_bouteilles() {
        final int nbAvis = 3;

        BouteilleId bouteilleId = BouteilleId.builder()
                .idBouteille(18298)
                .idRegion(3)
                .idCouleur(1)
                .build();

        Optional<Bouteille> bouteilleOpt = bouteilleRepository.findById(bouteilleId);
        assertThat(bouteilleOpt).isPresent();

        List<Avis> avisList = avisRepository.findByBouteille(bouteilleOpt.get());

        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);

        avisList.forEach(System.out::println);
    }
    
    @Test
    public void test04_avis_by_client_pseudo() {
        final String pseudo = "bobeponge@email.fr";
        final int nbAvis = 3;
        
        List<Avis> avisList = avisRepository.findByClient_Pseudo(pseudo);
        
        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);

        System.out.println(avisList);
    }

    @Test
    public void test05_avis_by_quantiteCommandee() {
        final int quantiteCommandee = 100;
        final int nbAvis = 4;

        List<Avis> avisList = avisRepository.findByClient_QuantiteCommandeeGreaterThan(quantiteCommandee);

        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);

        System.out.println(avisList);
    }

    @Test
    public void test06_avis_by_date_between() {
        final LocalDate from = LocalDate.of(2023, Month.JULY, 13);
        final LocalDate to =  LocalDate.of(2023, Month.JULY, 31);
        final int nbAvis = 6;

        List<Avis> avisList = avisRepository.findByDateBetween(from, to);

        assertThat(avisList).isNotEmpty();
        assertThat(avisList).hasSize(nbAvis);
        avisList.stream()
                .map(Avis::getDate)
                .forEach(d -> {
                    assertThat(d.toLocalDate()).isAfterOrEqualTo(from);
                    assertThat(d.toLocalDate()).isBeforeOrEqualTo(to);
                });

        System.out.println(avisList);
    }

}
