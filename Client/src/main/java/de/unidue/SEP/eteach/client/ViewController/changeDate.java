package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Ereignis_Controller;
import de.unidue.SEP.eteach.client.Endpoints.MailEndpoint;
import de.unidue.SEP.eteach.client.Entities.Ereignis;
import de.unidue.SEP.eteach.client.Entities.Remindertyp;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class changeDate implements Initializable
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    @FXML
    public TextField txft1;
    @FXML
    public TextField txft2;
    @FXML
    public DatePicker dateDate;

    public void ConfirmPressed(ActionEvent event)
    {
        //DATUM übertragen

        System.out.println(dateDate.getValue());
        Date date  = asDate(dateDate.getValue());
        date.setHours(Integer.parseInt(txft1.getText()));
        date.setMinutes(Integer.parseInt(txft2.getText()));
        System.out.println(date);



        Ereignis_Controller eC = new Ereignis_Controller();
        try {
            List<Ereignis> eList = eC.getReminderByDAy(Context.currentNutzer, new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(date)); //new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(date)

            for (int i = 0; i < eList.size(); i++) {
                if (eList.get(i).remindertyp.equals(Remindertyp.POPUP) || eList.get(i).remindertyp.equals(Remindertyp.BEIDES)) {
                    String a = "";
                    if (eList.get(i).getVeranstaltung() != null) {
                        a = eList.get(i).getNameVorlesung();
                    } else {
                        a = "Persoenlicher Termin von ihnen";
                    }

                    showInfoWindows(eList.get(i).getTitel(), "Das Event " + eList.get(i).getTitel() + " von " + a + " beginnt in " + eList.get(i).getErinnerungsIntervall() + " Stunden");

                    if (eList.get(i).getRemindertyp() == Remindertyp.EMAIL || eList.get(i).getRemindertyp() == Remindertyp.BEIDES)
                    {
                        getMailEndpoint().sendReminder(MainApp.getAuthHeader(), eList.get(i)).execute();
                    }

                }
            }
        }
        catch(Exception e){}

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public static MailEndpoint getMailEndpoint() {
        return RetrofitClient.getClient().create(MailEndpoint.class);
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void showInfoWindows(String titel, String nachricht) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(nachricht);
        alert.showAndWait();
    }
}
