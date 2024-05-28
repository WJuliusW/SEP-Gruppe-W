package de.unidue.sep.eteach.server.Service;


import de.unidue.sep.eteach.server.Entities.Ereignis;
import de.unidue.sep.eteach.server.Entities.Mail;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender emailSender;
    private final VeranstaltungService veranstaltungService;
    private final NutzerService nutzerService;

    @Autowired
    public MailService(JavaMailSender emailSender, VeranstaltungService veranstaltungService, NutzerService nutzerService) {
        this.emailSender = emailSender;
        this.veranstaltungService = veranstaltungService;
        this.nutzerService = nutzerService;
    }

    public Mail sendMail(Mail mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getVon());
        message.setTo(mail.getAn());
        message.setSubject(mail.getBetreff());
        message.setText(mail.getNachricht());

        emailSender.send(message);
        return mail;

        /*
        try {
            emailSender.send(message);
            return "Success";
        } catch (MailSendException exception){

            return exception.getMessage();
        }

         */

    }

    public Mail sendReminder(Ereignis ereignis){

        if (ereignis.getVeranstaltung() != null) {
            Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(ereignis.getVeranstaltung());
            veranstaltung.getVeranstaltungsteilnehmer().forEach(nutzer -> {
                sendReminderEmail(ereignis, nutzer.getEmail());
                System.out.println("Reminder Mail an Nutzer verschickt");
            });
        } else {
            Nutzer nutzer = nutzerService.getNutzerById(ereignis.getNutzer());
            sendReminderEmail(ereignis, nutzer.getEmail());
        }

        return new Mail();
    }

    private void sendReminderEmail(Ereignis ereignis, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("eteach@fmeyer-it.de");
        message.setTo(email);
        message.setSubject(ereignis.getTitel() + "Reminder");
        message.setText("Das Ereignis " + ereignis.getTitel() + " startet um " + ereignis.getStartZeit());
        emailSender.send(message);
    }
}



