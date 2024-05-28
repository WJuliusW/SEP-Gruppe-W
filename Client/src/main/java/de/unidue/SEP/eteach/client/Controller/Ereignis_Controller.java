package de.unidue.SEP.eteach.client.Controller;


import de.unidue.SEP.eteach.client.Endpoints.EreignisEndpoint;
import de.unidue.SEP.eteach.client.Endpoints.MailEndpoint;
import de.unidue.SEP.eteach.client.Entities.*;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.util.Duration;
import retrofit2.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Ereignis_Controller extends ETeach_Controller {
    private Response<Ereignis[]> response;
    private Response<Ereignis> responseSingle;
    private Nutzer nutzer;

    public static EreignisEndpoint getEreignisEndpoint() {
        return RetrofitClient.getClient().create(EreignisEndpoint.class);
    }

    public static MailEndpoint getMailEndpoint() {
        return RetrofitClient.getClient().create(MailEndpoint.class);
    }

    ScheduledService<Void> scheduledService = new ScheduledService<>() {
        @Override
        protected Task<Void> createTask() {

            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    Ereignis[] reminderEreignisse;
                    try {

                        response = getEreignisEndpoint().getReminderForNutzer(MainApp.getAuthHeader(), nutzer.getId()).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    reminderEreignisse = response.body();
                    for (Ereignis ereignis : reminderEreignisse) {

                                 System.out.println(ereignis.getRemindertyp().toString());
                        if (ereignis.getRemindertyp() == Remindertyp.EMAIL || ereignis.getRemindertyp() == Remindertyp.BEIDES)
                        {
                            getMailEndpoint().sendReminder(MainApp.getAuthHeader(), ereignis).execute();
                        }
                    }
                    return null;
                }
            };
        }
    };
    public void watchReminderByNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
        scheduledService.setPeriod(Duration.minutes(60.0));
        scheduledService.start();
    }

    public void unwatchReminder() {
        if (scheduledService.getState() == Worker.State.RUNNING) {
            scheduledService.cancel();
        }
        this.nutzer = null;
    }



    public ObservableList<Ereignis> getEreignisList() {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getAll(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }

    public void editEreignis(Ereignis ereignisEdited) throws IOException {
        responseSingle = getEreignisEndpoint().editEreignis(MainApp.getAuthHeader(), ereignisEdited.getId()).execute();
    }

    public void saveEreignis(Ereignis ereignis) throws IOException {
        responseSingle = getEreignisEndpoint().createNewEreignis(MainApp.getAuthHeader(), ereignis).execute();
    }

    public void deleteEreignis(Ereignis ereignis) throws IOException {
        responseSingle = getEreignisEndpoint().deleteEreignis(MainApp.getAuthHeader(), ereignis.getId()).execute();
    }

    public ObservableList<Ereignis> getCreatedNutzerEreignisList(Nutzer nutzer) {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getAllEreignisByNutzer(MainApp.getAuthHeader(), nutzer.getId()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }

    public ObservableList<Ereignis> getPersonalKalender(Nutzer nutzer) {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getKalenderForNutzer(MainApp.getAuthHeader(), nutzer.getId()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }

    public ObservableList<Ereignis> getVeranstaltungKalender(Veranstaltung veranstaltung) {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getAllEreignisByVeranstaltung(MainApp.getAuthHeader(), veranstaltung.getId()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }

    public ObservableList<Ereignis> getReminder(Nutzer nutzer)
    {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getReminderForNutzer(MainApp.getAuthHeader(), nutzer.getId()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }

    public ObservableList<Ereignis> getReminderByDAy(Nutzer nutzer,String date)
    {
        Ereignis[] eList;
        try {
            response = getEreignisEndpoint().getReminderForDate(MainApp.getAuthHeader(), nutzer.getId(), date).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Ereignis> ereignisList = FXCollections.observableArrayList(eList);
        return ereignisList;
    }
}
