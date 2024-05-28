package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Quiz;
import de.unidue.sep.eteach.server.Repositories.QuizRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.sql.rowset.spi.XmlReader;
import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    QuizRepo quizRepo;

    @Autowired
    public QuizService(QuizRepo quizRepo) {

        this.quizRepo = quizRepo;
    }

    public Quiz getQuizById(Integer id) {
        return quizRepo.findQuizById(id);
    }

    public Quiz createQuiz(Quiz quiz) {

        return quizRepo.save(quiz);
    }

    public List<Quiz> getAll() {

        return quizRepo.findAll();
    }

    public Quiz editQuiz(Quiz quizEditiert){

        return quizRepo.save(quizEditiert);
    }

    public Quiz deleteQuiz(Integer id){
        Quiz quiz= quizRepo.findQuizById(id);
        quizRepo.delete(quiz);
        return quiz;
    }

    @SneakyThrows
    public Quiz parseQuizFromXML(String xml) {

        // Zum einlesen

        DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
        DocumentBuilder bldr = fctr.newDocumentBuilder();
        InputSource insrc = new InputSource(new StringReader(xml));


        //StringReader reader = new StringReader(xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // Casting es, weil der unmarshaller nicht weiß was ein quiz ist

        Quiz quiz = (Quiz) unmarshaller.unmarshal(bldr.parse(insrc));
        return quiz;

    }

}
