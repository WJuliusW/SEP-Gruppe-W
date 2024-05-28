package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Freundschaft_Controller;
import de.unidue.SEP.eteach.client.Controller.PrivatChat_Controller;
import de.unidue.SEP.eteach.client.Entities.Freundschaft;
import de.unidue.SEP.eteach.client.Entities.PrivatNachricht;
import de.unidue.SEP.eteach.client.Entities.PrivatNachrichtenTyp;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PrivatChatController extends ETeach_Controller implements Initializable
{
    @FXML
    public TableView<PrivatNachricht> tblPrivatChat;

    @FXML
    public TableColumn<PrivatNachricht, Date> tbcZeit;

    @FXML
    public TableColumn<PrivatNachricht,String> tbcSender;

    @FXML
    public TableColumn<PrivatNachricht,String> tbcMessage;

    @FXML
    public TextField txfNachricht;

    @FXML
    public Button btnFreund;

    public static Freundschaft frSchaft;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //DEN BUTTON fuer ANFRAGEN AKZEPTIEREN / SCHICKEN VERSTECKEN
        Freundschaft_Controller frC = new Freundschaft_Controller();

        FilteredList<Freundschaft> list =new FilteredList(frC.getFreundschaftList());
        list.setPredicate(t -> (t.getNutzer1().getId() == Context.currentNutzer.getId()) && (t.getNutzer2().getId() == Context.privaterChatEmpfaenger.getId()));
        //list.setPredicate(t -> (t.getNutzer2().getId() == Context.privaterChatEmpfaenger.getId()));


        if(list.isEmpty())
        {
            //NEUE ANFRAGE
            btnFreund.setVisible(true);
            btnFreund.setDisable(false);
            btnFreund.setText("Freundschaftsanfrage versenden");
        }
        else if(list.size() > 1)
        {
            System.out.println("Huuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii Bitte nicht");
            btnFreund.setText("Etwas ist schiefgelaufen");
        }
        else
        {
            //ANFRAGE IST BEREITS DURCH
            if(list.get(0).freundschaftAngenommen)
            {
             btnFreund.setVisible(false);
             btnFreund.setDisable(true);
            }
            else
            {
                //ANFRAGE VON UNS
                frSchaft = list.get(0);

                if(frSchaft.getAnfrageID() == Context.currentNutzer.getId()) {
                    btnFreund.setVisible(true);
                    btnFreund.setDisable(true);
                    btnFreund.setText("Freundschaftsanfrage verschickt");
                }
                else
                {
                    //WIR HABEN NICHT Die ANFRAGE VERSCHICKT
                    btnFreund.setVisible(true);
                    btnFreund.setDisable(false);
                    btnFreund.setText("Freundschaftsanfrage annehmen");
                }
            }
        }


        Context.privatChat = tblPrivatChat;

        //TableView
        tbcZeit.setCellValueFactory(new PropertyValueFactory<>("zeitstempel"));
        tbcSender.setCellValueFactory(new PropertyValueFactory<>("absender"));
        tbcMessage.setCellValueFactory(new PropertyValueFactory<>("inhalt"));

        //DAten holen
        PrivatChat_Controller pCc = new PrivatChat_Controller();

        /*
        FilteredList<PrivatNachricht> list1  = new FilteredList(pCc.getNutzerList(Context.getCurrentNutzer()));
        list1.setPredicate(t-> t.getEmpfaenger().getId() == Context.privaterChatEmpfaenger.getId());

        FilteredList<PrivatNachricht> list2  = new FilteredList(pCc.getNutzerList(Context.privaterChatEmpfaenger));
        list2.setPredicate(t-> t.getEmpfaenger().getId() == Context.currentNutzer.getId());

        List<PrivatNachricht> chat = null;
        chat.addAll(list1);
        chat.addAll(list2);
        chat.sort();

         */

        FilteredList<PrivatNachricht> list1 =new FilteredList<> ( pCc.getPrivatNachrichtList());
        list1.setPredicate(t -> ((t.getAbsender().getId() == Context.currentNutzer.getId() && t.getEmpfaenger().getId() == Context.privaterChatEmpfaenger.getId()) || t.getEmpfaenger().getId() == Context.currentNutzer.getId() && t.getAbsender().getId() == Context.privaterChatEmpfaenger.getId()));

        tblPrivatChat.setItems(list1);

    }


    public void Send(ActionEvent event) throws IOException {

        //UNDER CONSTRUCTION
        PrivatNachricht pn = new PrivatNachricht();

        pn.setAbsender(Context.currentNutzer);
        pn.setEmpfaenger(Context.privaterChatEmpfaenger);
        pn.setInhalt(txfNachricht.getText());
        pn.setTyp(PrivatNachrichtenTyp.NACHRICHT);
        //pn.setZeitstempel(new Date());


        PrivatChat_Controller pcC = new PrivatChat_Controller();
        pcC.createPrivatNachricht(Context.currentNutzer,Context.privaterChatEmpfaenger,pn);
        //UND LOSSCHICKEN
        txfNachricht.clear();
        //DAten holen
        PrivatChat_Controller pCc = new PrivatChat_Controller();
        FilteredList<PrivatNachricht> list1 =new FilteredList<> ( pCc.getPrivatNachrichtList());
        list1.setPredicate(t -> ((t.getAbsender().getId() == Context.currentNutzer.getId() && t.getEmpfaenger().getId() == Context.privaterChatEmpfaenger.getId()) || t.getEmpfaenger().getId() == Context.currentNutzer.getId() && t.getAbsender().getId() == Context.privaterChatEmpfaenger.getId()));

        tblPrivatChat.setItems(list1);
    }

    public void btnFreundPressed(ActionEvent event) throws IOException {
        Freundschaft_Controller frC = new Freundschaft_Controller();

        if(btnFreund.getText().equals("Freundschaftsanfrage versenden"))
        {
            Freundschaft freundschaft = new Freundschaft(Context.currentNutzer,Context.privaterChatEmpfaenger,false);
            freundschaft.setAnfrageID(Context.currentNutzer.getId());
            frC.saveFreundschaft(freundschaft);


            Freundschaft freundschaft1 = new Freundschaft(Context.privaterChatEmpfaenger,Context.currentNutzer,false);
            freundschaft1.setAnfrageID(Context.currentNutzer.getId());
            frC.saveFreundschaft(freundschaft1);

            btnFreund.setText("Freundschaftsanfrage verschickt");
            btnFreund.setDisable(true);

        }
        else if(btnFreund.getText().equals("Freundschaftsanfrage annehmen"))
        {

            //Hin und Rueckrunde
            frC.deleteFreundschaft(frSchaft);
            Freundschaft freundschaft = new Freundschaft(Context.privaterChatEmpfaenger,Context.currentNutzer,true);
            freundschaft.setAnfrageID(Context.currentNutzer.getId());
            frC.saveFreundschaft(freundschaft);

            Freundschaft freundschaft1 = new Freundschaft(Context.currentNutzer,Context.privaterChatEmpfaenger,true);
            freundschaft.setAnfrageID(Context.currentNutzer.getId());
            frC.saveFreundschaft(freundschaft1);

            //Das Aquivalent zur fr die wir gerade gelöscht haben
            FilteredList<Freundschaft> list =new FilteredList(frC.getFreundschaftList());
            list.setPredicate(t -> (t.getNutzer2().getId() == Context.currentNutzer.getId()) && (t.getNutzer1().getId() == Context.privaterChatEmpfaenger.getId()) && !t.freundschaftAngenommen);
            //list.setPredicate(t -> (t.getNutzer1().getId() == Context.privaterChatEmpfaenger.getId()));
            //list.setPredicate(t -> !t.freundschaftAngenommen);
            frC.deleteFreundschaft(list.get(0));


            btnFreund.setDisable(true);
            btnFreund.setVisible(false);
        }
        else if(btnFreund.getText().equals("Freundschaftsanfrage verschickt"))
        {
            showInfoWindows("","Freundschaftsanfrage wurde verschickt bitte warten bis die andere Person annimmt");
        }

    }
}
