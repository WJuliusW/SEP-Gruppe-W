package de.unidue.sep.eteach.server.Service;


import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.PrivatNachricht;
import de.unidue.sep.eteach.server.Entities.PrivatNachrichtTyp;
import de.unidue.sep.eteach.server.Entities.Quiz;
import de.unidue.sep.eteach.server.Repositories.PrivatNachrichtRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivatNachrichtService {

    private PrivatNachrichtRepo privatNachrichtRepo;

    @Autowired
    public PrivatNachrichtService (PrivatNachrichtRepo privatNachrichtRepo){

        this.privatNachrichtRepo = privatNachrichtRepo;
    }

    public List<PrivatNachricht> getPrivatNachrichtenByEmpfaenger (Nutzer nutzer){

        return privatNachrichtRepo.getPrivatNachrichtsByEmpfaengerAndTypEqualsOrderByZeitstempel(nutzer, PrivatNachrichtTyp.NACHRICHT);
    }

    public List<PrivatNachricht> getFreundschaftsanfragenByEmpfaenger (Nutzer nutzer){

        return privatNachrichtRepo.getPrivatNachrichtsByEmpfaengerAndTypEqualsOrderByZeitstempel(nutzer, PrivatNachrichtTyp.FREUNDSCHAFTSANFRAGE);
    }

    public List<PrivatNachricht> getPrivatNachrichtenByAbsenderOrEmpfaenger (Nutzer absender, Nutzer empfaenger){

        return  privatNachrichtRepo.getPrivatNachrichtsByEmpfaengerAndTypEqualsOrEmpfaengerAndTypEqualsOrderByZeitstempel(absender, PrivatNachrichtTyp.NACHRICHT, empfaenger, PrivatNachrichtTyp.NACHRICHT);
    }

    public PrivatNachricht createPrivatNachricht (PrivatNachricht privatNachricht){

        return  privatNachrichtRepo.save(privatNachricht);
    }

    public PrivatNachricht deletePrivatNachricht (Integer id){

        PrivatNachricht privatNachricht = privatNachrichtRepo.getPrivatNachrichtById(id);
        privatNachrichtRepo.delete(privatNachricht);

        return privatNachricht;
    }

    //Julius

    public List<PrivatNachricht> getAll() {

        return privatNachrichtRepo.findAll();
    }

    }


