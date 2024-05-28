package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Freundschaft;
import retrofit2.Call;
import retrofit2.http.*;

public interface FreundschaftEndpoint {
    @GET("/eteach/freundschaft/{id}")
    Call<Freundschaft> getFreundschaftById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/freundschaft/all")
    Call< Freundschaft[]> getAllFreundschaft(@Header("Authorization") String auth);

    @POST("/eteach/freundschaft/new")
    Call< Freundschaft> createFreundschaft(@Header("Authorization") String auth, @Body  Freundschaft freundschaft);

    @PUT("/eteach/freundschaft/{id}")
    Call< Freundschaft> editFreundschaft(@Header("Authorization") String auth,@Body  Freundschaft freundschaft, @Path("id") Integer id);

    @DELETE("/eteach/freundschaft/{id}")
    Call< Freundschaft> deleteFreundschaft(@Header("Authorization") String auth, @Path("id") Integer id);
}
