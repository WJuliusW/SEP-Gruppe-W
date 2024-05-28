package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Ereignis;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;

public interface EreignisEndpoint {
    @GET("/eteach/ereignis/{id}")
    Call<Ereignis> getById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/ereignis/all")
    Call<Ereignis[]> getAll(@Header("Authorization") String auth);

    @GET("/eteach/ereignis/nutzer/{nutzerId}/all")
    Call<Ereignis[]> getAllEreignisByNutzer(@Header("Authorization") String auth, @Path("nutzerId") Integer nutzerId);

    @GET("/eteach/ereignis/veranstaltung/{veranstaltungId}/all")
    Call<Ereignis[]> getAllEreignisByVeranstaltung(@Header("Authorization") String auth, @Path("veranstaltungId") Integer veranstaltungId);

    @GET("/eteach/ereignis/kalender/{nutzerId}")
    Call<Ereignis[]> getKalenderForNutzer(@Header("Authorization") String auth, @Path("nutzerId") Integer nutzerId);

    @POST("/eteach/ereignis/new")
    Call<Ereignis> createNewEreignis(@Header("Authorization") String auth, @Body Ereignis ereignis);

    @PUT("/eteach/ereignis/{id}")
    Call<Ereignis> editEreignis(@Header("Authorization") String auth, @Path("id") Integer id);

    @DELETE("/eteach/ereignis/{id}")
    Call<Ereignis> deleteEreignis(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/ereignis/reminder/{nutzerId}")
    Call<Ereignis[]> getReminderForNutzer(@Header("Authorization") String auth, @Path("nutzerId") Integer nutzerId);

    @POST("/eteach/ereignis/reminder/{nutzerId}/date")
    Call<Ereignis[]> getReminderForDate(@Header("Authorization") String auth, @Path("nutzerId") Integer nutzerId, @Body String date);

}