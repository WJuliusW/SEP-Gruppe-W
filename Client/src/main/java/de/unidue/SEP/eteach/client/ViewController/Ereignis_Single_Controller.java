package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Ereignis_Controller;
import de.unidue.SEP.eteach.client.Entities.Ereignis;
import de.unidue.SEP.eteach.client.Entities.Remindertyp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Ereignis_Single_Controller extends Ereignis_Controller implements Initializable {

    @FXML
    private Label lblHeadline;

    @FXML
    private TextField txtTitel;

    @FXML
    private DatePicker dateStartDatum;

    @FXML
    private DatePicker dateEndeDatum;

    @FXML
    public TextField txf1,txf2,txf3,txf4;

    @FXML
    private Button btnAbbruch;

    @FXML
    private Button btnSpeichern;

    @FXML
    private TextArea txaAnmerkungen;

    @FXML
    private TextField txfReminder;

    @FXML
    public AnchorPane anchor;

    @FXML
    public RadioButton btnPOPUP;

    @FXML
    public RadioButton btnEmail;

    @FXML
    void handleAbbruchButtonAction(ActionEvent event)
    {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void handleSpeichernButtonAction(ActionEvent event) throws IOException {
        if(txtTitel.getText().equals(""))
        {
            showInfoWindows("Fehler","Titel eingeben");
        }
        else if(dateStartDatum.getValue().toString().equals("") || dateEndeDatum.getValue().toString().equals(""))
        {
            showInfoWindows("Fehler","Start/Enddatum eingeben");
        }
        else if(txf1.getText().equals("") || txf2.getText().equals("") || txf3.getText().equals("") || txf4.getText().equals(""))
        {
            showInfoWindows("Fehler","Uhrzeit eingeben");
        }
        else if(txfReminder.getText().equals(""))
        {
            showInfoWindows("Fehler","Bitte Reminder eingeben");
        }
        else
        {
            int i = Integer.parseInt(txf1.getText());
            int j = Integer.parseInt(txf2.getText());
            int k = Integer.parseInt(txf3.getText());
            int l = Integer.parseInt(txf4.getText());
            if (((0 <= i) && (i <= 24)) && ((0 <= k) && (k <= 24)) && ((0 <= j) && (j <= 60)) && ((0 <= l) && (l <= 60))) {
                Ereignis ereignis = new Ereignis(txtTitel.getText(), dateStartDatum.getValue().toString(), dateEndeDatum.getValue().toString(), i + ":" + j, k + ":" + l, txaAnmerkungen.getText());
                ereignis.setErinnerungsIntervall(Integer.parseInt(txfReminder.getText()));
                if(Context.currentVeranstaltung != null)
                {
                    ereignis.setVeranstaltung(Context.currentVeranstaltung.getId());
                    ereignis.setNameVorlesung(Context.currentVeranstaltung.getTitel());
                }
                else
                {
                    // der nutzer sollte nur gesetzt sein wenn es ein persönlicher Termin ist
                    ereignis.setNutzer(currentNutzer.getId());
                }

                //
                if(btnEmail.isSelected() && btnPOPUP.isSelected())
                {
                    ereignis.remindertyp = Remindertyp.BEIDES;
                }
                else if(btnEmail.isSelected())
                {
                    ereignis.remindertyp = Remindertyp.EMAIL;
                }
                else if(btnPOPUP.isSelected())
                {
                    ereignis.remindertyp = Remindertyp.POPUP;
                }

                saveEreignis(ereignis);

                showInfoWindows("!", "Termin gespeichert");
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            } else
                showInfoWindows("!", "Falsche Uhrzeit");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(Context.ereignisZeigen != null)
        {
            txfReminder.setText(String.valueOf(Context.ereignisZeigen.erinnerungsIntervall));
            txtTitel.setText(Context.ereignisZeigen.getTitel());
            txaAnmerkungen.setText(Context.ereignisZeigen.getAnmerkungen());
            dateStartDatum.setValue(LocalDate.parse(Context.ereignisZeigen.getStartDatum()));
            dateEndeDatum.setValue(LocalDate.parse(Context.ereignisZeigen.getEndDatum()));

            txf1.setText(Context.ereignisZeigen.getStartZeit());
            txf3.setText(Context.ereignisZeigen.getEndZeit());
            disableEverything();
        }

    }

    public void disableEverything()
    {
        for (Node node : anchor.getChildren())
        {
            try
            {
                node.setDisable(true);
            }
            catch(Exception e){}

        }
    }

}
