package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Lehrender;
import retrofit2.Call;
import retrofit2.http.*;

public interface LehrenderEndpoint {
    @GET("/eteach/lehrender/{id}")
    Call<Lehrender> getLehrenderById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/lehrender/email/{email}")
    Call<Lehrender> getLehrenderByEmail(@Header("Authorization") String auth, @Path("email") String email);

    @GET("/eteach/lehrender/all")
    Call<Lehrender[]> getAllLehrender(@Header("Authorization") String auth);

    @POST("/eteach/lehrender/new")
    Call<Lehrender> createLehrender(@Header("Authorization") String auth, @Body Lehrender lehrender);

    @PUT("/eteach/lehrender/{id}")
    Call<Lehrender> editLehrender(
            @Header("Authorization") String auth,
            @Path("id") Integer id,
            @Body Lehrender lehrender);

    @DELETE("/eteach/lehrender/{id}")
    Call<Lehrender> deleteLehrender(@Header("Authorization") String auth, @Path("id") Integer id);
}
