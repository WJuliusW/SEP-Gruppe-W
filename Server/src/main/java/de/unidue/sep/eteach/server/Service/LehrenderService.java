package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Lehrender;
import de.unidue.sep.eteach.server.Repositories.LehrenderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LehrenderService {

    LehrenderRepo lehrenderRepo;

    @Autowired
    public LehrenderService(LehrenderRepo lehrenderRepo) {
        this.lehrenderRepo = lehrenderRepo;
    }

    public Lehrender getLehrenderById(Integer id){return lehrenderRepo.findLehrenderById(id);}

    public Lehrender findLehrenderById(Integer id) {return lehrenderRepo.findLehrenderById(id);}

    public Lehrender createLehrender(Lehrender lehrender) {
        return lehrenderRepo.save(lehrender);
    }

    public Lehrender getLehrenderByEmail(String email) {
        return lehrenderRepo.findLehrenderByEmail(email);
    }

    public List<Lehrender> getAll() {
        return lehrenderRepo.findAll();
    }

    public Lehrender editLehrender(Lehrender lehrenderEditiert) {
        return lehrenderRepo.save(lehrenderEditiert);
    }

    public Lehrender deleteLehrender(Integer id) {
        Lehrender lehrender = lehrenderRepo.findLehrenderById(id);
        lehrenderRepo.delete(lehrender);
        return lehrender;
    }
}
