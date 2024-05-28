package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Entities.Abgabe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AbgabeController extends ETeach_Controller implements Initializable
{
    @FXML
    public TableView<Abgabe> tblAntworten;

    @FXML
    public TableColumn<Abgabe,String> tbcFrage;

    @FXML
    public TableColumn<Abgabe,Boolean> tbcKorrekt;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
            tbcKorrekt.setCellValueFactory(new PropertyValueFactory<>("richtig"));
            tbcFrage.setCellValueFactory(new PropertyValueFactory<>("frage"));
            ObservableList<Abgabe> abgaben = FXCollections.observableArrayList( Context.quizAntworten);
            tblAntworten.setItems(abgaben);
    }

}
