package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Abgabe;
import de.unidue.sep.eteach.server.Entities.Quiz;
import de.unidue.sep.eteach.server.Repositories.AbgabeRepo;
import de.unidue.sep.eteach.server.Repositories.QuizRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbgabeService {

    AbgabeRepo abgabeRepo;

    @Autowired
    public AbgabeService(AbgabeRepo abgabeRepo) {
        this.abgabeRepo = abgabeRepo;
    }

    public Abgabe getAbgabeById(Integer id) {return abgabeRepo.findAbgabeById(id);}

    public Abgabe createAbgabe(Abgabe abgabe) {
        return abgabeRepo.save(abgabe);
    }

    public List<Abgabe> getAll() {
        return abgabeRepo.findAll();
    }

    public Abgabe editAbgabe(Abgabe abgabeEditiert){
        return abgabeRepo.save(abgabeEditiert);
    }

    public Abgabe deleteAbgabe(Integer id){
        Abgabe abgabe= abgabeRepo.findAbgabeById(id);
        abgabeRepo.delete(abgabe);
        return abgabe;
    }


}
