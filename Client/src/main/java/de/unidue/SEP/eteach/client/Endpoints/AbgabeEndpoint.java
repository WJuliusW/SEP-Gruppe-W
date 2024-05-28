package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Abgabe;
import retrofit2.Call;
import retrofit2.http.*;

public interface AbgabeEndpoint {
    @GET("/eteach/abgabe/{id}")
    Call<Abgabe> getAbgabeById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/abgabe/all")
    Call<Abgabe[]> getAllAbgabe(@Header("Authorization") String auth);

    @POST("/eteach/abgabe/new")
    Call<Abgabe> createAbgabe(@Header("Authorization") String auth, @Body Abgabe abgabe);

    @PUT("/eteach/abgabe/{id}")
    Call<Abgabe> editAbgabe(@Header("Authorization") String auth,@Body Abgabe abgabe, @Path("id") Integer id);

    @DELETE("/eteach/abgabe/{id}")
    Call<Abgabe> deleteAbgabe(@Header("Authorization") String auth, @Path("id") Integer id);
}
