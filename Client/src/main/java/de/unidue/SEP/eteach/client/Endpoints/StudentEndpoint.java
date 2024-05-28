package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Student;
import retrofit2.Call;
import retrofit2.http.*;

public interface StudentEndpoint {
    @GET("/eteach/student/{id}")
    Call<Student> getStudentById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/student/authenticate/{matrikelnummer}")
    Call<Student> getStudentByMatrikelnummer(@Header("Authorization") String auth, @Path("matrikelnummer") Integer matrikelnummer);
//
    @GET("/eteach/student/email/{email}")
    Call<Student> getStudentByEmail(@Header("Authorization") String auth, @Path("email") String email);

//    @GET("/eteach/student/authenticate/mp/{key}")
//    Call<Student> getStudentByKeyMatPass(@Header("Authorization") String auth, @Path("key") String key);

    @GET("/eteach/student/all")
    Call<Student[]> getAllStudent(@Header("Authorization") String auth);

    @POST("/eteach/student/new")
    Call<Student> createStudent(@Header("Authorization") String auth, @Body Student student);

    @PUT("/eteach/student/{id}")
    Call<Student> editStudent(@Header("Authorization") String auth, @Path("id") Integer id);

    @DELETE("/eteach/student/{id}")
    Call<Student> deleteStudent(@Header("Authorization") String auth, @Path("id") Integer id);

}
