package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Freundschaft_Controller;
import de.unidue.SEP.eteach.client.Controller.Lehrender_Controller;
import de.unidue.SEP.eteach.client.Controller.PrivatChat_Controller;
import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.ChatNachricht;
import de.unidue.SEP.eteach.client.Entities.Freundschaft;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.PrivatNachricht;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Freunde_Controller extends ETeach_Controller
{

    @FXML
    private TableView<Freundschaft> tblAnfragen;

    @FXML
    private TableColumn<Freundschaft, String> tbcAnfragen;



    @FXML
    private TableView<Freundschaft> tblFreunde;

    @FXML
    private TableColumn<Freundschaft, String> tbcFreunde;



    @FXML
    private TableView<PrivatNachricht> tblChat;

    @FXML
    private TableColumn<PrivatNachricht, String> tbcNutzer;

    @FXML
    private TableColumn<PrivatNachricht, String> tbcNachricht;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //Chatnachrichten
        tbcNutzer.setCellValueFactory(new PropertyValueFactory<>("absender"));
        tbcNachricht.setCellValueFactory(new PropertyValueFactory<>("inhalt"));

        PrivatChat_Controller privatChatController = new PrivatChat_Controller();

        tblChat.setItems(privatChatController.getPrivatNachrichtenByAbsenderOrEmpfaenger(Context.currentNutzer,Context.currentNutzer));



        //Freundesliste
        Freundschaft_Controller frC = new Freundschaft_Controller();
        FilteredList<Freundschaft> list =new FilteredList(frC.getFreundschaftList());
        list.setPredicate(t -> (t.getNutzer1().getId() == Context.currentNutzer.getId()) &&  t.freundschaftAngenommen );
        //list.setPredicate(t -> t.freundschaftAngenommen); Predicates sidn absolut scheisse
        //Wir brauchen nur den anderen Namen
        tbcFreunde.setCellValueFactory(new PropertyValueFactory<>("nutzer2"));
        tblFreunde.setItems(list);


        //Freundschaftsanfragen
        FilteredList<Freundschaft> list1 =new FilteredList(frC.getFreundschaftList());

        list1.setPredicate(t -> (t.getNutzer1().getId() == Context.currentNutzer.getId()) && !t.freundschaftAngenommen);
        //list1.setPredicate(t -> !t.freundschaftAngenommen);
        tbcAnfragen.setCellValueFactory(new PropertyValueFactory<>("nutzer2"));
        tblAnfragen.setItems(list1);

    }


    public void tblAnfragenClicked(MouseEvent event) throws IOException
    {
        Nutzer nutzer;
        if(tblAnfragen.getSelectionModel().getSelectedItem() != null) {
            if (tblAnfragen.getSelectionModel().getSelectedItem().nutzer1.getId() != currentNutzer.getId()) {
                nutzer = tblAnfragen.getSelectionModel().getSelectedItem().nutzer1;
            } else {
                nutzer = tblAnfragen.getSelectionModel().getSelectedItem().nutzer2;
            }
            Student_Controller student_controller = new Student_Controller();
            Lehrender_Controller lehrender_controller = new Lehrender_Controller();

            if (nutzer.getNutzer_typ().equals("Student")) {
                selectedStudent = student_controller.getStudentById(nutzer.getId());
                openModalForm("student_single_view", "Profil von :" + nutzer.getVorname() + " " + nutzer.getNachname());
                selectedStudent = null;
            } else {
                selectedLehrender = lehrender_controller.getLehrenderById(nutzer.getId());
                openModalForm("lehrender_single", "Profil von :" + nutzer.getVorname() + " " + nutzer.getNachname());
                selectedLehrender = null;
            }
        }
    }


    public void tblFreundeClicked(MouseEvent event) throws IOException {
        Nutzer nutzer;

        if(tblFreunde.getSelectionModel().getSelectedItem().nutzer1.getId() != currentNutzer.getId())
        {
            nutzer = tblFreunde.getSelectionModel().getSelectedItem().nutzer1;
        }
        else
        {
            nutzer = tblFreunde.getSelectionModel().getSelectedItem().nutzer2;
        }
        Student_Controller student_controller = new Student_Controller();
        Lehrender_Controller lehrender_controller = new Lehrender_Controller();

        if (nutzer.getNutzer_typ().equals("Student")) {
            selectedStudent = student_controller.getStudentById(nutzer.getId());
            openModalForm("student_single_view", "Profil von :" + nutzer.getVorname() + " " + nutzer.getNachname());
            selectedStudent = null;
        }
        else
        {
            selectedLehrender = lehrender_controller.getLehrenderById(nutzer.getId());
            openModalForm("lehrender_single", "Profil von :" + nutzer.getVorname()+ " " + nutzer.getNachname());
            selectedLehrender = null;
        }
    }

    public void tblChatClicked(MouseEvent event)
    {
        if(tblChat.getSelectionModel().getSelectedItem() != null) {
            Nutzer nutzer;
            if (tblChat.getSelectionModel().getSelectedItem().getAbsender().getId() != currentNutzer.getId()) {
                nutzer = tblChat.getSelectionModel().getSelectedItem().getAbsender();
            } else {
                nutzer = tblChat.getSelectionModel().getSelectedItem().getEmpfaenger();
            }

            Context.privaterChatEmpfaenger = nutzer;
            openModalForm("PrivatChat", "PrivatChat mit  " + nutzer.getVorname() + " " + nutzer.getNachname());
        }
    }


}

