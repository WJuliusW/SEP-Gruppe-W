package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.ChatNachricht;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.ChatRaumService;
import de.unidue.sep.eteach.server.Service.NutzerService;
import de.unidue.sep.eteach.server.Service.VeranstaltungService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/eteach/chatRoom")
public class ChatRaumController extends AppController{

    public ChatRaumController(ChatRaumService chatRaumService, VeranstaltungService veranstaltungService, NutzerService nutzerService) {
        this.chatRaumService = chatRaumService;
        this.veranstaltungService = veranstaltungService;
        this.nutzerService = nutzerService;
    }

    @GetMapping("/{veranstaltungId}")
    public ResponseEntity<List<ChatNachricht>> getChatByVeranstaltung (@PathVariable Integer veranstaltungId){
        return new ResponseEntity<>(chatRaumService.getChatNachrichtenByChatRaum(veranstaltungService.getVeranstaltungById(veranstaltungId)), HttpStatus.OK);
    }

    @PostMapping(path = "{veranstaltungId}/{nutzerId}/newMessage", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ChatNachricht> createChatNachricht (@PathVariable Integer veranstaltungId, @PathVariable Integer nutzerId, @RequestBody ChatNachricht chatNachricht){
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        chatNachricht.setVeranstaltung(veranstaltung);
        chatNachricht.setAbsender(nutzerService.getNutzerById(nutzerId));

      // Dadurch musst der Zeitstempel nicht im Frontend gesezt werden
        if (chatNachricht.getZeitstempel() == null) {
            chatNachricht.setZeitstempel(new Date());
        }

        return new ResponseEntity<>(chatRaumService.createChatNachricht(chatNachricht), HttpStatus.CREATED);
    }



    @DeleteMapping("/{veranstaltungId}/clear")
    public ResponseEntity<List<ChatNachricht>> clearChatByVeranstaltung ( @PathVariable Integer veranstaltungId){

        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        chatRaumService.clearChatNachrichtenByChatRaum(veranstaltung);

        return new ResponseEntity<>(chatRaumService.getChatNachrichtenByChatRaum(veranstaltung), HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<ChatNachricht> deleteChatNachricht (@PathVariable Integer id){

        return new ResponseEntity<>(chatRaumService.deleteChatNachricht(id), HttpStatus.OK);
    }



}