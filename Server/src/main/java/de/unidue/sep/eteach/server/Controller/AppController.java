package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AppController {
    @Autowired
    NutzerService nutzerService;

    @Autowired
    EreignisService ereignisService;

    @Autowired
    LehrenderService lehrenderService;

    @Autowired
    MaterialService materialService;

    @Autowired
    StudentService studentService;

    @Autowired
    VeranstaltungService veranstaltungService;

    @Autowired
    AdresseService adresseService;

    @Autowired
    AbgabeService abgabeService;

    @Autowired
    ChatRaumService chatRaumService;

    @Autowired
    ToDoService toDoService;
    @Autowired
    QuizService quizService;

    @Autowired
    PrivatNachrichtService privatNachrichtService;

    @Autowired
    MailService mailService;
}
