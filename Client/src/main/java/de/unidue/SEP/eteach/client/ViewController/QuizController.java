package de.unidue.SEP.eteach.client.ViewController;
import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Abgabe_Controller;
import de.unidue.SEP.eteach.client.Controller.Quiz_Controller;
import de.unidue.SEP.eteach.client.Entities.Abgabe;
import de.unidue.SEP.eteach.client.Entities.Frage;
import de.unidue.SEP.eteach.client.Entities.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizController extends ETeach_Controller
{
    @FXML
    public Button btnSpeichern;

    @FXML
    private Polygon links;

    @FXML
    private Polygon rechts;

    @FXML
    private Button btnStatistik;

    @FXML
    private TextField lblName;

    @FXML
    private TextField lblFrage;

    @FXML
    private CheckBox choiceAntwort1;

    @FXML
    private CheckBox choiceAntwort2;

    @FXML
    private CheckBox choiceAntwort3;

    @FXML
    private CheckBox choiceAntwort4;

    @FXML
    public Button btnAdd;

    @FXML
    public TextField txfA1,txfA2,txfA3,txfA4;



    //ALLES AN DARSTELLUNG NIX TOUCHY
    public int momentaneFrage;
    public List<Frage> fragen;
    public int phase;//1 == Erstellen; 2 == Bearbeitung


    //Eigene Antworten
    //Stats
    public List<Abgabe> antworten = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(currentLehrender == null)
        {
            btnStatistik.setDisable(true);
            btnStatistik.setVisible(false);
        }

        if(currentQuiz != null)
        {
            lblFrage.setDisable(true);
            lblName.setText(currentQuiz.name);

            btnAdd.setVisible(false);
            btnAdd.setDisable(true);

            links.setVisible(false);
            links.setDisable(true);

            //Nochmal Angucken Das erste Element der Liste
            momentaneFrage = 0;

            fragen = currentQuiz.fragenList;

            fill(fragen.get(momentaneFrage));
            if(currentQuiz.fragenList.size() == 1 && currentLehrender ==  null)
            {
                rechts.setDisable(true);
                rechts.setVisible(false);
            }

        }
        else
        {
            rechts.setVisible(false);
            rechts.setDisable(true);

            links.setVisible(false);
            links.setDisable(true);

            btnAdd.setVisible(true);
            btnAdd.setDisable(false);

            txfA1.clear();
            txfA1.setDisable(false);

            txfA2.clear();
            txfA2.setDisable(false);

            txfA3.clear();
            txfA3.setDisable(false);

            txfA4.clear();
            txfA4.setDisable(false);

            lblName.clear();
            lblName.setEditable(true);

            lblFrage.clear();
            lblFrage.setEditable(true);
            lblFrage.setDisable(false);

            btnSpeichern.setDisable(true);
            btnSpeichern.setVisible(false);
        }


    }

    @FXML
    void btnStatistikPressed(ActionEvent event) 
    {
        openModalForm("Statistik","Uebersicht zu " + currentQuiz.name);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void linksClicked(MouseEvent event) 
    {
        System.out.println("Links");
        momentaneFrage -= 1;
        if(momentaneFrage != 0)
        {
            System.out.println("Show Links");
            fill(fragen.get(momentaneFrage));
            links.setDisable(false);
            links.setVisible(true);
        }
        else if(momentaneFrage == 0)
        {
            links.setDisable(true);
            links.setVisible(false);
            fill(fragen.get(momentaneFrage));
        }
        if(currentQuiz.fragenList.size() > momentaneFrage || currentLehrender != null)
        {
            rechts.setDisable(false);
            rechts.setVisible(true);
            System.out.println("Rechts wird angezeigt");
        }
    }

    @FXML
    void rechtsPressed(MouseEvent event) 
    {
        System.out.println("Rechts");
        momentaneFrage += 1;
        if(momentaneFrage < currentQuiz.fragenList.size())
        {
            Frage frage = fragen.get(momentaneFrage);
            fill(frage);


            System.out.println("Rechte frage");
            if(momentaneFrage+1 == currentQuiz.fragenList.size() && currentLehrender == null)
            {
                rechts.setDisable(true);
                rechts.setVisible(false);
            }
        }
        else if((momentaneFrage == currentQuiz.fragenList.size()) && (currentLehrender != null))
        {
            rechts.setDisable(true);
            rechts.setVisible(false);

            btnAdd.setVisible(true);
            btnAdd.setDisable(false);

            lblFrage.clear();
            lblFrage.setDisable(false);

            txfA1.clear();
            txfA1.setDisable(false);

            txfA2.clear();
            txfA2.setDisable(false);

            txfA3.clear();
            txfA3.setDisable(false);

            txfA4.clear();
            txfA4.setDisable(false);
        }

        links.setDisable(false);
        links.setVisible(true);
    }

    public void btnSpeichernPressed(ActionEvent event)
    {
        if(fragen.get(momentaneFrage).korrekteAntwort.equals("A") || fragen.get(momentaneFrage).korrekteAntwort.equals("B") || fragen.get(momentaneFrage).korrekteAntwort.equals("C") || fragen.get(momentaneFrage).korrekteAntwort.equals("D"))
        {
         if(fragen.get(momentaneFrage).korrekteAntwort.equals("A") && choiceAntwort1.isSelected())
         {
             Abgabe abgabe = new Abgabe(momentaneFrage, true);
             antworten.add(momentaneFrage, abgabe);
         }
         else if(fragen.get(momentaneFrage).korrekteAntwort.equals("B") && choiceAntwort2.isSelected())
         {
             Abgabe abgabe = new Abgabe(momentaneFrage, true);
             antworten.add(momentaneFrage, abgabe);
         }
         else if(fragen.get(momentaneFrage).korrekteAntwort.equals("C") && choiceAntwort3.isSelected())
         {
             Abgabe abgabe = new Abgabe(momentaneFrage, true);
             antworten.add(momentaneFrage, abgabe);
         }
         else if(fragen.get(momentaneFrage).korrekteAntwort.equals("D") && choiceAntwort4.isSelected())
         {
             Abgabe abgabe = new Abgabe(momentaneFrage, true);
             antworten.add(momentaneFrage, abgabe);
         }
         else
         {
             Abgabe abgabe = new Abgabe(momentaneFrage, false);
             antworten.add(momentaneFrage, abgabe);
         }
            showInfoWindows("Antwort eingegeben", "Antwort gespeichert");
        }
        else {
            if (choiceAntwort1.isSelected() && fragen.get(momentaneFrage).antwortA.equals(fragen.get(momentaneFrage).korrekteAntwort)) {
                Abgabe abgabe = new Abgabe(momentaneFrage, true);
                antworten.add(momentaneFrage, abgabe);
            } else if (choiceAntwort2.isSelected() && fragen.get(momentaneFrage).antwortB.equals(fragen.get(momentaneFrage).korrekteAntwort)) {
                Abgabe abgabe = new Abgabe(momentaneFrage, true);
                antworten.add(momentaneFrage, abgabe);
            } else if (choiceAntwort3.isSelected() && fragen.get(momentaneFrage).antwortC.equals(fragen.get(momentaneFrage).korrekteAntwort)) {
                Abgabe abgabe = new Abgabe(momentaneFrage, true);
                antworten.add(momentaneFrage, abgabe);
            } else if (choiceAntwort4.isSelected() && fragen.get(momentaneFrage).antwortD.equals(fragen.get(momentaneFrage).korrekteAntwort)) {
                Abgabe abgabe = new Abgabe(momentaneFrage, true);
                antworten.add(momentaneFrage, abgabe);
            } else {
                Abgabe abgabe = new Abgabe(momentaneFrage, false);
                antworten.add(momentaneFrage, abgabe);
            }
            showInfoWindows("Antwort eingegeben", "Antwort gespeichert");
        }
    }

    public void btnAddPressed(ActionEvent event) throws IOException {
        Frage nFrage = new Frage();
        int zuViel = 0;

        if(choiceAntwort1.isSelected())
        {
            zuViel++;
        }
        else if (choiceAntwort2.isSelected())
        {
            zuViel++;
        }
        else if (choiceAntwort3.isSelected())
        {
            zuViel++;
        }
        else if (choiceAntwort4.isSelected())
        {
            zuViel++;
        }


        if((txfA1.getText().isEmpty() && txfA2.getText().isEmpty() && txfA3.getText().isEmpty() && txfA4.getText().isEmpty()) ) {
            showInfoWindows("Fehler","Bitte geben sie Antwortmöglichkeiten ein");
        }
        else if(zuViel != 1)
        {
            showInfoWindows("Fehler","Falsche Anzahl an Antwortmöglichkeiten");
        }
        else if(lblFrage.getText().isEmpty())
        {
            showInfoWindows("Fehler","Geben sie eine Fragestellung ein");
        }
        else if(lblName.getText().isEmpty())
        {
            showInfoWindows("Fehler","Geben sie einen Quiznamen ein");
        }
        else
        {//Neue frage erzeugen zur Liste hinzufügen +Speichern

            nFrage.antwortA = txfA1.getText();
            nFrage.antwortB = txfA2.getText();
            nFrage.antwortC = txfA3.getText();
            nFrage.antwortD = txfA4.getText();
            nFrage.fragestellung = lblFrage.getText();
            if(choiceAntwort1.isSelected())
            {
                nFrage.korrekteAntwort = nFrage.antwortA;
            }
            else if (choiceAntwort2.isSelected())
            {
                nFrage.korrekteAntwort = nFrage.antwortB;
            }
            else if (choiceAntwort3.isSelected())
            {
                nFrage.korrekteAntwort = nFrage.antwortC;
            }
            else if (choiceAntwort4.isSelected())
            {
                nFrage.korrekteAntwort = nFrage.antwortD;
            }


            if(currentQuiz == null)
            {
                Quiz nQuiz = new Quiz();
                nQuiz.name = lblName.getText();
                nQuiz.fragenList = new ArrayList<Frage>();
                nQuiz.fragenList.add(nFrage);
                nQuiz.passendeVeranstaltung = Context.currentVeranstaltung;
                currentQuiz = nQuiz;
                //LISTEN ZUR STATISTIK


                //Neues Quiz Abspeichern
                Quiz_Controller quizC = new Quiz_Controller();
                quizC.saveQuiz(nQuiz);


                fill(nFrage);
                rechts.setVisible(true);
                rechts.setDisable(false);

            }
            else
            {
                //LISTEN ZUR STATISTIK

                //Neue frage Abspeichern

                Quiz_Controller quizC = new Quiz_Controller();
                quizC.deleteQuiz(currentQuiz);
                currentQuiz.fragenList.add(nFrage);
                quizC.editQuiz(currentQuiz);
                fill(nFrage);
                links.setVisible(true);
                links.setDisable(false);

            }
            showInfoWindows("Quiz erstellt","Quiz wurde erstellt");
            rechts.setVisible(true);
            rechts.setDisable(false);
        }


    }


    public void fill(Frage f)
    {
        txfA1.setText(f.antwortA);
        txfA2.setText(f.antwortB);
        txfA3.setText(f.antwortC);
        txfA4.setText(f.antwortD);
        lblFrage.setText(f.fragestellung);

        txfA1.setVisible(true);
        txfA1.setDisable(true);

        txfA2.setVisible(true);
        txfA2.setDisable(true);

        txfA3.setVisible(true);
        txfA3.setDisable(true);

        txfA4.setVisible(true);
        txfA4.setDisable(true);

        lblFrage.setDisable(true);
        lblName.setDisable(true);

        choiceAntwort1.setSelected(false);
        choiceAntwort2.setSelected(false);
        choiceAntwort3.setSelected(false);
        choiceAntwort4.setSelected(false);

        btnSpeichern.setVisible(true);
        btnSpeichern.setDisable(false);

        btnAdd.setDisable(true);
        btnAdd.setVisible(false);
    }


    public void btnAbgabePressed(ActionEvent event) throws IOException {

        Context.currentQuiz.versuchsAnzahl++;
        //NUll Elemente füllen
        for(int i = 0; i < fragen.size();i++)
        {
            if(antworten.get(i) == null)
            {
                Abgabe abgabe = new Abgabe(i,true);
                antworten.add(momentaneFrage,abgabe);
            }
        }
        //Bestandender Versuch?
        int counter = 0;
        for(int i = 0; i < fragen.size();i++)
        {
            if(antworten.get(i).richtig == true)
            {
                counter++;
            }
        }

        if(1.0 * counter >= 1.0 * antworten.size()/2)
        {
            Context.currentQuiz.erfolgreicheVersuche++;
        }
        Context.quizAntworten = antworten;
        openModalForm("Abgabe","Ergebnis");

        Quiz_Controller quizC = new Quiz_Controller();
        quizC.editQuiz(currentQuiz);

        //Abgabe speichern

        Abgabe_Controller abgabeController = new Abgabe_Controller();

        for(int i = 0; i < fragen.size();i++)
        {
            abgabeController.saveAbgabe(antworten.get(i));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
