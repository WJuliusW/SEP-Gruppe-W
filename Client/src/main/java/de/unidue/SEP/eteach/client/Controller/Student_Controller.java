package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Entities.Student;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Student_Controller extends ETeach_Controller {
    private Response<Student[]> students;
    private Response<Student> response;
    private ObservableList<Student> studentList;

    public ObservableList<Student> getStudentList() {
        Student[] studentArray;
        try {
            students = getStudentEndpoint().getAllStudent(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        studentArray = students.body();
        studentList = FXCollections.observableArrayList(studentArray);
        return studentList;
    }

    public Student getStudentById(Integer id) throws IOException {
        response = getStudentEndpoint().getStudentById(MainApp.getAuthHeader(), id).execute();
        return response.body();
    }

    public Student getStudentByMatrikelnummer(Integer matrikelnummer) throws IOException {
        response = getStudentEndpoint().getStudentByMatrikelnummer(MainApp.getAuthHeader(), matrikelnummer).execute();
        return response.body();
    }

    public void editStudent(Student studentEdited) throws IOException {
        response = getStudentEndpoint().editStudent(MainApp.getAuthHeader(), studentEdited.getId()).execute();
    }

    public void saveStudent(Student student) throws IOException {
        response = getStudentEndpoint().createStudent(MainApp.getAuthHeader(), student).execute();
    }

    public void deleteStudent(Student student) throws IOException {
        response = getStudentEndpoint().deleteStudent(MainApp.getAuthHeader(), student.getId()).execute();
    }

}
