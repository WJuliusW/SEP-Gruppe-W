package de.unidue.SEP.eteach.client.Controller;


import de.unidue.SEP.eteach.client.Endpoints.ToDoEndpoint;
import de.unidue.SEP.eteach.client.Entities.ToDo;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class ToDo_Controller extends ETeach_Controller {
    private Response<ToDo[]> response;
    private Response<ToDo> responseSingle;

    public static ToDoEndpoint getToDoEndpoint() {
        return RetrofitClient.getClient().create(ToDoEndpoint.class);
    }

    public ObservableList<ToDo> getToDoList() {
        ToDo[] eList;
        try {
            response = getToDoEndpoint().getAllToDo(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<ToDo> todoList = FXCollections.observableArrayList(eList);
        return todoList;
    }

    public void editToDo(ToDo todoEdited) throws IOException {
        responseSingle = getToDoEndpoint().editToDo(MainApp.getAuthHeader(),todoEdited, todoEdited.id).execute();
    }

    public void saveToDo(ToDo todo) throws IOException {
        responseSingle = getToDoEndpoint().createToDo(MainApp.getAuthHeader(), todo).execute();
    }

    public void deleteToDo(ToDo todo) throws IOException {
        responseSingle = getToDoEndpoint().deleteToDo(MainApp.getAuthHeader(), todo.id).execute();
    }
}
