package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Repositories.AdresseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdresseService {

    AdresseRepo adresseRepo;

    @Autowired
    public AdresseService(AdresseRepo adresseRepo) {
        this.adresseRepo = adresseRepo;
    }

    public Adresse getAdresseById(Integer id) {return adresseRepo.findAdresseById(id);}

    public Adresse createAdresse(Adresse adresse) {
        return adresseRepo.save(adresse);
    }

    public List<Adresse> getAll() {
        return adresseRepo.findAll();
    }

    public Adresse editAdresse(Adresse adresseEditiert){
        return adresseRepo.save(adresseEditiert);
    }

    public Adresse deleteAdresse(Integer id){
        Adresse adresse=adresseRepo.findAdresseById(id);
        adresseRepo.delete(adresse);
        return adresse;
    }
}
