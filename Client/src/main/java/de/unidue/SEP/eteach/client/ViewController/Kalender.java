package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Ereignis_Controller;
import de.unidue.SEP.eteach.client.Entities.Ereignis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Kalender extends ETeach_Controller implements Initializable
{

    @FXML
    private TableView<Ereignis> tblTermine;

    @FXML
    private TableColumn<Ereignis, String> tbcTitel;

    @FXML
    private TableColumn<Ereignis, Integer> tcbVorlesung;

    @FXML
    private TableColumn<Ereignis, String> tbcS1;

    @FXML
    private TableColumn<Ereignis, String> tbcS2;

    @FXML
    private TableColumn<Ereignis, String> tbcE1;

    @FXML
    private TableColumn<Ereignis, String> tbcE2;

    @FXML
    private TableColumn<Ereignis, String> anmerkung;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Context.currentVeranstaltung = null;
        //Ereignis Service get Ereigniss by Nutzer
        //Auffuellen
        tbcTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        tcbVorlesung.setCellValueFactory(new PropertyValueFactory<>("nameVorlesung"));
        tbcS1.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tbcS2.setCellValueFactory(new PropertyValueFactory<>("startZeit"));
        tbcE1.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tbcE2.setCellValueFactory(new PropertyValueFactory<>("endZeit"));
        anmerkung.setCellValueFactory(new PropertyValueFactory<>("anmerkungen"));

        Ereignis_Controller ereignis_controller = new Ereignis_Controller();
        tblTermine.setItems(ereignis_controller.getPersonalKalender(Context.getCurrentNutzer()));

    }

    public void terminAction(ActionEvent event)
    {
        Context.ereignisZeigen = null;
        openModalForm("ereignis_single","Persoenlicher Ereignis");
    }




    public void changeDatePressed(ActionEvent event)
    {
        openModalForm("changeDate","Zum Testen der Reminder");
    }


    public void tablTerminClicked(MouseEvent event)
    {
        if(tblTermine.getSelectionModel().getSelectedItem() != null)
        {
            Context.ereignisZeigen = tblTermine.getSelectionModel().getSelectedItem();
            openModalForm("ereignis_single", Context.ereignisZeigen.getTitel());
        }
    }
}
