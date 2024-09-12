package fr.eni.demoheritage;

import fr.eni.demoheritage.entity.Medecin;
import fr.eni.demoheritage.entity.Patient;
import fr.eni.demoheritage.repository.MedecinRepository;
import fr.eni.demoheritage.repository.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestHeritage {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testSaveMedecin() {

        Medecin medecin = Medecin.builder()
                .prenom("Bob")
                .nom("Banane")
                .email("test@test.test")
                .adresse("Quimper")
                .dateDeNaissance(LocalDate.now())
                .specialite("Proctologue")
                .tarif(56f)
                .numMedecin("123")
                .build();

        Medecin savedMedecin = medecinRepository.save(medecin);

        Assertions.assertThat(savedMedecin.getId()).isGreaterThan(0);

    }


    @Test
    public void testSavepatient() {

        Patient patient = Patient.builder()
                .prenom("Steve")
                .nom("Steve")
                .email("Steve@Steve.test")
                .adresse("Steve")
                .dateDeNaissance(LocalDate.now())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        Assertions.assertThat(savedPatient.getId()).isGreaterThan(0);

    }

}
