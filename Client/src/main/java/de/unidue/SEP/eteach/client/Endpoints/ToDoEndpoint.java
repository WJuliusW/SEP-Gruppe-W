package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Quiz;
import de.unidue.SEP.eteach.client.Entities.ToDo;
import retrofit2.Call;
import retrofit2.http.*;

public interface ToDoEndpoint {
    @GET("/eteach/todo/{id}")
    Call<ToDo> getToDoById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/todo/all")
    Call<ToDo[]> getAllToDo(@Header("Authorization") String auth);

    @POST("/eteach/todo/new")
    Call<ToDo> createToDo(@Header("Authorization") String auth, @Body ToDo todo);

    @PUT("/eteach/todo/{id}")
    Call<ToDo> editToDo(@Header("Authorization") String auth,@Body ToDo todo,@Path("id") Integer id);

    @DELETE("/eteach/todo/{id}")
    Call<ToDo> deleteToDo(@Header("Authorization") String auth, @Path("id") Integer id);
}
