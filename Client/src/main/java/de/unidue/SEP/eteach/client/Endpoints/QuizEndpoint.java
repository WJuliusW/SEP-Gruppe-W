package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Quiz;
import retrofit2.Call;
import retrofit2.http.*;

public interface QuizEndpoint {
    @GET("/eteach/quiz/{id}")
    Call<Quiz> getQuizById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/quiz/all")
    Call<Quiz[]> getAllQuiz(@Header("Authorization") String auth);

    @POST("/eteach/quiz/new")
    Call<Quiz> createQuiz(@Header("Authorization") String auth, @Body Quiz quiz);

    //Meine
    @POST("/eteach/quiz/{veranstaltungsId}/new")
    Call<Quiz> createQuizwithVeranstaltung(@Header("Authorization") String auth, @Path("veranstaltungsId") Integer veranstaltungsId, @Body Quiz quiz);


    @PUT("/eteach/quiz/{id}")
    Call<Quiz> editQuiz(@Header("Authorization") String auth,@Body Quiz quiz, @Path("id") Integer id);

    @DELETE("/eteach/quiz/{id}")
    Call<Quiz> deleteQuiz(@Header("Authorization") String auth, @Path("id") Integer id);
}