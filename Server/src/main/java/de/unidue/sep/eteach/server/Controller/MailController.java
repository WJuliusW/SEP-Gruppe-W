package de.unidue.sep.eteach.server.Controller;


import de.unidue.sep.eteach.server.Entities.Ereignis;
import de.unidue.sep.eteach.server.Entities.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eteach/mail")
public class MailController extends AppController{

@PostMapping ("/send")
public ResponseEntity<Mail> send (@RequestBody Mail mail){

    return  new ResponseEntity<>(mailService.sendMail(mail), HttpStatus.CREATED);

}

@PostMapping("/sendReminder")
public ResponseEntity<Mail> sendReminder(@RequestBody Ereignis ereignis){

    return new ResponseEntity<>(mailService.sendReminder(ereignis), HttpStatus.CREATED);
}
}
