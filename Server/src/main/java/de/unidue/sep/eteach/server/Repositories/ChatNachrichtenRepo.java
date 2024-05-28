package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.ChatNachricht;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatNachrichtenRepo extends JpaRepository<ChatNachricht, Integer> {

    ChatNachricht getChatNachrichtById(Integer id);

    // Magic is happening here
    // holen uns die Nachrichten, von genau einer Veranstaltung
    List<ChatNachricht> getChatNachrichtByVeranstaltungOrderByZeitstempel(Veranstaltung veranstaltung);


}
