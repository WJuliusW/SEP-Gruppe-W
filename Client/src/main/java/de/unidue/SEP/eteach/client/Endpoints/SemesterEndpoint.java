package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Semester;
import retrofit2.Call;
import retrofit2.http.*;

public interface SemesterEndpoint {
    @GET("/eteach/semester/{id}")
    Call<Semester> getSemesterById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/semester/all")
    Call<Semester[]> getAllSemester(@Header("Authorization") String auth);

    @POST("/eteach/semester/new")
    Call<Semester> createSemester(@Header("Authorization") String auth, @Body Semester semester);

    @PUT("/eteach/semester/{id}")
    Call<Semester> editSemester(@Header("Authorization") String auth, @Path("id") Integer id);

    @DELETE("/eteach/semester/{id}")
    Call<Semester> deleteSemester(@Header("Authorization") String auth, @Path("id") Integer id);
}
