package de.unidue.SEP.eteach.client.Controller;


import de.unidue.SEP.eteach.client.Endpoints.QuizEndpoint;
import de.unidue.SEP.eteach.client.Entities.Quiz;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Quiz_Controller extends ETeach_Controller {
    private Response<Quiz[]> response;
    private Response<Quiz> responseSingle;

    public static QuizEndpoint getQuizEndpoint() {
        return RetrofitClient.getClient().create(QuizEndpoint.class);
    }

    public ObservableList<Quiz> getQuizList() {
        Quiz[] eList;
        try {
            response = getQuizEndpoint().getAllQuiz(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Quiz> quizList = FXCollections.observableArrayList(eList);
        return quizList;

    }

    public Quiz editQuiz(Quiz quizEdited) throws IOException {

        return getQuizEndpoint().editQuiz(MainApp.getAuthHeader(),quizEdited,quizEdited.getId()).execute().body();

    }

    public void saveQuiz(Quiz quiz) throws IOException {
        responseSingle = getQuizEndpoint().createQuiz(MainApp.getAuthHeader(), quiz).execute();

    }

    public void deleteQuiz(Quiz quiz) throws IOException {
        responseSingle = getQuizEndpoint().deleteQuiz(MainApp.getAuthHeader(), quiz.getId()).execute();

    }
    public void saveQuizwithVeranstaltung(Quiz quiz, Veranstaltung veranstaltung) throws IOException {
        responseSingle = getQuizEndpoint().createQuizwithVeranstaltung(MainApp.getAuthHeader(), veranstaltung.getId(), quiz).execute();

    }
}