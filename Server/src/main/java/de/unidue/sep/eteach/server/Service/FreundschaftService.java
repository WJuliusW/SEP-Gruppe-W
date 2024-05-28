package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Freundschaft;
import de.unidue.sep.eteach.server.Repositories.FreundschaftRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreundschaftService {

    FreundschaftRepo freundschaftRepo;

    @Autowired
    public FreundschaftService(FreundschaftRepo freundschaftRepo) {
        this.freundschaftRepo = freundschaftRepo;
    }

    public Freundschaft getFreundschaftById(Integer id) {return freundschaftRepo.findFreundschaftById(id);}

    public Freundschaft createFreundschaft(Freundschaft freundschaft) {
        return freundschaftRepo.save(freundschaft);
    }

    public List<Freundschaft> getAll() {
        return freundschaftRepo.findAll();
    }

    public Freundschaft editFreundschaft(Freundschaft freundschaftEditiert){
        return freundschaftRepo.save(freundschaftEditiert);
    }

    public Freundschaft deleteFreundschaft(Integer id){
        Freundschaft freundschaft = freundschaftRepo.findFreundschaftById(id);
        freundschaftRepo.delete(freundschaft);
        return freundschaft;
    }


}
