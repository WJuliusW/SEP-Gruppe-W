package de.unidue.sep.eteach.server.Service;


import de.unidue.sep.eteach.server.Entities.ChatNachricht;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Repositories.ChatNachrichtenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor

public class ChatRaumService {

    private ChatNachrichtenRepo chatNachrichtenRepo;

    // Macht die Notwendigkeit eines Chatnachrichtenrepo nicht nötig, dadurch das es automatisch erstellt wird
    // Haben es trotzde,
    @Autowired
    public ChatRaumService(ChatNachrichtenRepo chatNachrichtenRepo){

        this.chatNachrichtenRepo = chatNachrichtenRepo;
    }

    public List<ChatNachricht> getChatNachrichtenByChatRaum (Veranstaltung veranstaltung){

        return chatNachrichtenRepo.getChatNachrichtByVeranstaltungOrderByZeitstempel(veranstaltung);
    }

    public void clearChatNachrichtenByChatRaum (Veranstaltung veranstaltung){

        veranstaltung.getChat().forEach(chatNachricht -> chatNachrichtenRepo.delete(chatNachricht));
    }

    public ChatNachricht createChatNachricht( ChatNachricht chatNachricht){

        return chatNachrichtenRepo.save(chatNachricht);
    }
    public ChatNachricht deleteChatNachricht(Integer id){

        ChatNachricht chatNachricht = chatNachrichtenRepo.getChatNachrichtById(id);
        chatNachrichtenRepo.delete(chatNachricht);

        return chatNachricht;
    }

}