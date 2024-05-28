package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.PrivatNachricht;
import de.unidue.sep.eteach.server.Entities.PrivatNachrichtTyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivatNachrichtRepo extends JpaRepository<PrivatNachricht, Integer> {

    PrivatNachricht getPrivatNachrichtById(Integer id);
    List<PrivatNachricht> getPrivatNachrichtsByEmpfaengerAndTypEqualsOrderByZeitstempel(Nutzer nutzer, PrivatNachrichtTyp typ);

    // Kriegen die Nachrichten zwischen zwei Nutzer -->Absender und Empfänger
    List<PrivatNachricht> getPrivatNachrichtsByEmpfaengerAndTypEqualsOrEmpfaengerAndTypEqualsOrderByZeitstempel(Nutzer absender, PrivatNachrichtTyp typ1, Nutzer empfaenger,  PrivatNachrichtTyp typ2);

    //JUlius
    List<PrivatNachricht> findAll();


}
