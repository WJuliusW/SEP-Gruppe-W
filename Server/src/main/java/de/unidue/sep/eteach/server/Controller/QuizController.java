package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Quiz;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/quiz")
public class QuizController extends AppController{

    @Autowired
    QuizService quizService;

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAll() {
        return new ResponseEntity<>(quizService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Quiz> createNewQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<Quiz>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }

    @PostMapping(path = "{veranstaltungId}/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Quiz> createNewQuizwithVeranstaltung(@PathVariable Integer veranstaltungId, @RequestBody Quiz quiz) {
        Veranstaltung passendeVeranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        quiz.setPassendeVeranstaltung(passendeVeranstaltung);
        quiz.setName(passendeVeranstaltung.getTitel().concat(" Quiz"));

        return new ResponseEntity<Quiz>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> editQuiz(
            @RequestBody Quiz quiz,
            @PathVariable Integer id) {
        return new ResponseEntity<>(quizService.editQuiz(quiz), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> deleteQuiz(
            @PathVariable Integer id) {
        return new ResponseEntity<>(quizService.deleteQuiz(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{veranstaltungId}/parse/xml")
    public ResponseEntity<Quiz> getQuizFromXml(@PathVariable Integer veranstaltungId, @RequestBody String xml){


        Quiz parsedQuiz = quizService.parseQuizFromXML(xml);
        Veranstaltung passendeVeranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        parsedQuiz.setPassendeVeranstaltung(passendeVeranstaltung);
        parsedQuiz.setName(passendeVeranstaltung.getTitel().concat(" Quiz")); //Hängt einen einen String dran -->concat
        parsedQuiz = quizService.createQuiz(parsedQuiz);
        return new ResponseEntity<>(parsedQuiz,HttpStatus.OK);

    }

}
