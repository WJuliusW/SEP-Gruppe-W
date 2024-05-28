package de.unidue.SEP.eteach.client.ViewController;
import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Abgabe_Controller;
import de.unidue.SEP.eteach.client.Entities.Abgabe;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Stats;
import de.unidue.SEP.eteach.client.Entities.Stats2;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatistikController implements Initializable
{

    @FXML
    private TextField txfProTeilnehmer;

    @FXML
    private TextField txfBestanden;

    @FXML
    private TableView<Stats> tblTeilnehmer;

    @FXML
    private TableColumn<Stats, String> tbcTeilnehmer;

    @FXML
    private TableColumn<Stats, Integer> tbcAnzahlV;

    @FXML
    private TableView<Stats2> tblFragen;

    @FXML
    private TableColumn<Stats2, Integer> tbcFrage;

    @FXML
    private TableColumn<Stats2, Integer> tbcAnzahlKorrekt;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Abgabe_Controller abgabeController = new Abgabe_Controller();

        Double ProzentSatzRichtig = (1.0 * Context.currentQuiz.erfolgreicheVersuche/Context.currentQuiz.versuchsAnzahl)* 100;
        txfBestanden.setText(String.valueOf(ProzentSatzRichtig)+ "%");

        FilteredList<Abgabe> list1 =new FilteredList<> (abgabeController.getAbgabeList());
        list1.setPredicate(t -> t.getQuiz().getId() == Context.currentQuiz.getId());


        List<Nutzer> nutzerDieBearbeitethaben = new ArrayList<>();
        //Ich weiß doch auch nicht mehr
        nutzerDieBearbeitethaben.add(list1.get(0).nutzer);
        double length = 0;

        for(int i = 1;  i< list1.size();i++ )
        {
            boolean in = false;
            for(int u = 0;u <nutzerDieBearbeitethaben.size();u++)
            {
                if (nutzerDieBearbeitethaben.get(u).getId() == list1.get(i).nutzer.getId())
                {
                    in= true;
                }
            }
            if(in)
            {

            }
            else
            {
                if(list1.get(i).nutzer.getNutzer_typ().equals("Student"))
                {
                    length++;
                }
                nutzerDieBearbeitethaben.add(list1.get(i).nutzer);
            }
        }

        int anzahlStudentenImKurs = 0;
        for(int i = 0; i < Context.currentQuiz.passendeVeranstaltung.getVeranstaltungsteilnehmerList().size(); i++)
        {
            if(Context.currentQuiz.passendeVeranstaltung.getVeranstaltungsteilnehmerList().get(i).getNutzer_typ().equals("Student"))
            {
                anzahlStudentenImKurs++;
            }
        }

        Double proTeilnehmer = 1.0 * ( 1.0 * length / anzahlStudentenImKurs)*100;
        txfProTeilnehmer.setText(proTeilnehmer + "%");


        List<Stats> table1 = new ArrayList<>();

        //RICHTIGES FORMAT
        for(int i = 0; i< nutzerDieBearbeitethaben.size(); i++)
        {
            Stats stat = new Stats(nutzerDieBearbeitethaben.get(i));
            table1.add(stat);
        }


        //ZÄHLEN
        for (int i = 0; i < list1.size();i++)
        {
            for(int u = 0; u < table1.size();u++)
            {
                if(list1.get(i).nutzer.getId() == table1.get(u).nutzer.getId())
                {
                    table1.get(u).anzahl++;
                }
            }
        }

        //Jede Einzelne Fraage bringt eine Abgabe deshalb kriegt man zu viele Abgaben möglicherweise anzhahl / currentQuiz.fragenList.size()

        tbcTeilnehmer.setCellValueFactory(new PropertyValueFactory<>("nutzer"));
        tbcAnzahlV.setCellValueFactory(new PropertyValueFactory<>("anzahl"));
        tblTeilnehmer.setItems(FXCollections.observableList(table1));




        tbcFrage.setCellValueFactory(new PropertyValueFactory<>("frage"));
        tbcAnzahlKorrekt.setCellValueFactory(new PropertyValueFactory<>("anzahl"));

        FilteredList<Abgabe> list =new FilteredList<> (abgabeController.getAbgabeList());
        list.setPredicate(t -> t.getQuiz().getId() == Context.currentQuiz.getId());


        //ALLE FRAGEN
        List<Stats2> table2 = new ArrayList<>();
        for(int i = 0; i< Context.currentQuiz.fragenList.size();i++)
        {
            Stats2 stat = new Stats2(list.get(i).frage);
            table2.add(stat);
        }


        for(int i = 0; i<list.size();i++)
        {
            for (int u= 0; u < table2.size();u++)
            {
                if(list.get(i).frage == table2.get(u).frage && list.get(i).richtig)
                {
                    table2.get(u).anzahl++;
                }
            }
        }
        tblFragen.setItems(FXCollections.observableList(table2));

    }
}
