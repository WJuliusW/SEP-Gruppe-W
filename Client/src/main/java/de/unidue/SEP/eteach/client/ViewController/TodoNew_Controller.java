package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.ToDo_Controller;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static de.unidue.SEP.eteach.client.Context.currentVeranstaltung;

public class TodoNew_Controller implements Initializable
{

    public ListView<Nutzer> listNutzer;
    public TextField txfName;

    public Nutzer selectedNutzer;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Nutzer> nutzerList = FXCollections.observableArrayList(currentVeranstaltung.getVeranstaltungsteilnehmerList());
        listNutzer.setItems(nutzerList);
        if(Context.editToDo != null)
        {
            txfName.setText(Context.editToDo.aufgabe);
        }
    }


    public void btnSpeichernPressed(ActionEvent event) throws IOException {
        ToDo neu = new ToDo();
        if(selectedNutzer != null)
        {
            neu.aufgabensteller = selectedNutzer;
        }
        else
        {
            neu.aufgabensteller = Context.currentNutzer;
        }
        neu.aufgabe = txfName.getText();
        neu.veranstaltung = currentVeranstaltung;
        ToDo_Controller c = new ToDo_Controller();
        if(Context.editToDo == null) {
            c.saveToDo(neu);
        }
        else
        {
            c.editToDo(neu);
            c.deleteToDo(Context.editToDo);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void listClicked(MouseEvent event)
    {
        this.selectedNutzer = listNutzer.getSelectionModel().getSelectedItem();
    }
}
