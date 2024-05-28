package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Repositories.NutzerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutzerService {

    NutzerRepo nutzerRepo;

    @Autowired
    public NutzerService(NutzerRepo nutzerRepo) {
        this.nutzerRepo = nutzerRepo;
    }

    public Nutzer getNutzerById(Integer id) {
        return nutzerRepo.findNutzerById(id);
    }

    public Nutzer createNutzer(Nutzer nutzer) {
        return nutzerRepo.save(nutzer);
    }

    public Nutzer getNutzerByEmail(String email) {
        return nutzerRepo.findNutzerByEmail(email);
    }

    public List<Nutzer> getAll() {
        return nutzerRepo.findAll();
    }

    public Nutzer editNutzer(Nutzer nutzerEditiert) {
        return nutzerRepo.save(nutzerEditiert);
    }

    public Nutzer deleteNutzer(Integer id) {
        Nutzer nutzer = nutzerRepo.findNutzerById(id);
        nutzerRepo.delete(nutzer);
        return nutzer;
    }
/*
    public List<Nutzer> addFriend(Integer nutzer1, Integer nutzer2) {
        Nutzer nutzerA = nutzerRepo.findNutzerById(nutzer1);
        Nutzer nutzerB = nutzerRepo.findNutzerById(nutzer2);

        nutzerA.getFreunde().add(nutzerB);
        nutzerB.getFreunde().add(nutzerA);

        nutzerRepo.save(nutzerA);
        nutzerRepo.save(nutzerB);

        return new ArrayList<>(nutzerA.getFreunde());

    }
 */
}
