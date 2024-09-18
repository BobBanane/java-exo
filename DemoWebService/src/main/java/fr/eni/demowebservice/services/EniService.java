package fr.eni.demowebservice.services;

import fr.eni.demowebservice.entities.Crayon;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EniService {

    private List<Crayon> crayonsBouchonnes = Arrays.asList( // = list simulant BDD ne pas faire en vrai
        new  Crayon[] {
            Crayon.builder().id(1).type("Feutre").couleur("Rouge").build(),
            Crayon.builder().id(2).type("Stylo").couleur("Bleu").build(),
            Crayon.builder().id(3).type("Stabilo").couleur("Vert").build(),
        }
    );


    public List<Crayon> listerCrayon() {
        return crayonsBouchonnes;
    }

    public Optional<Crayon> getCrayonById(int id) {
        return crayonsBouchonnes.stream()
                .filter(crayon -> crayon.getId() == id)
                .findFirst();
    }

}
