package fr.eni.comptebanquaire;


import fr.eni.comptebanquaire.bll.CompteService;
import fr.eni.comptebanquaire.bo.Compte;
import fr.eni.comptebanquaire.bo.ComptePK;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testCompteService {

    @Autowired
    private CompteService compteService;

    @Test
    @Order(1)
    @Commit
    public void testSave() {

        Compte compteBob = Compte.builder()
                .numCompte("FR0000000000000001")
                .solde(0.0)
                .pseudo("Bob")
                .build();

        Compte compteBanane = Compte.builder()
                .numCompte("FR0000000000000002")
                .solde(0.0)
                .pseudo("Banane")
                .build();


        compteService.ajouterCompte(compteBob);
        compteService.ajouterCompte(compteBanane);

        Assertions.assertThat(compteBob).isNotNull();
        Assertions.assertThat(compteBanane).isNotNull();
    }

    @Test
    public void testGetCompteById() {
        ComptePK id = ComptePK.builder()
                .pseudo("Bob")
                .numCompte("FR0000000000000001")
                .build();

        Optional<Compte> compteOpt = compteService.getCompteById(id);
        Assertions.assertThat(compteOpt).isPresent();
        System.out.println(compteOpt.get());
    }

    @Test
    @Order(2)
    public void testCrediter() {
        final String numCompte = "FR0000000000000001";
        final Double montant =  20.0;

        Optional<Compte> compteOpt = compteService.getCompteByNumCompte(numCompte);

        Assertions.assertThat(compteOpt).isPresent();
        compteOpt.ifPresent(p -> {
            Double oldSolde = p.getSolde();
            compteService.crediter(p, montant);
            Assertions.assertThat(compteOpt.get().getSolde()).isEqualTo(oldSolde + montant);
        });
    }

    @Test
    @Order(3)
    public void testDebiter() {
        final String numCompte = "FR0000000000000001";
        final Double montant =  20.0;

        Optional<Compte> compteOpt = compteService.getCompteByNumCompte(numCompte);

        Assertions.assertThat(compteOpt.isPresent()).isTrue();
        compteOpt.ifPresent(p -> {
            Double oldSolde = p.getSolde();
            try {
                compteService.debiter(p, montant);
            } catch ( Exception e ) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(compteOpt.get().getSolde()).isEqualTo(oldSolde - montant);
        });
    }

    @Test
    @Order(4)
    public void testVirement() {
        final String numCompte = "FR0000000000000001";
        final String numCompte2 = "FR0000000000000002";
        final Double montant =  20.0;

        Optional<Compte> compteOpt = compteService.getCompteByNumCompte(numCompte);
        Optional<Compte> compte2Opt = compteService.getCompteByNumCompte(numCompte2);

        Assertions.assertThat(compteOpt).isPresent();
        Assertions.assertThat(compte2Opt).isPresent();

        // je credite le premier compte pour être sûr que le solde soit suffisant
        compteService.crediter(compteOpt.get(), montant);

        try {
            double oldSolde = compteOpt.get().getSolde();
            double oldSolde2 = compte2Opt.get().getSolde();
            compteService.virement(compteOpt.get(), compte2Opt.get(), montant);
            Assertions.assertThat(compteOpt.get().getSolde()).isEqualTo(oldSolde - montant);
            Assertions.assertThat(compte2Opt.get().getSolde()).isEqualTo(oldSolde2 + montant);
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }

}
