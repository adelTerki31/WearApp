package com.example.wearapplication;

import java.util.HashMap;

public class Message {

    private  int id,student_id;
    private  String student_message;
    private HashMap coordinates;

    public Message(int id,int student_id,String student_message,HashMap coordinates){
        this.id=id;
        this.student_id=student_id;
        this.student_message=student_message;
        this.coordinates=coordinates;
    }

    public int getId() {
        return id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getStudent_message() {
        return student_message;
    }

    public HashMap getCoordinates() {
        return coordinates;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setStudent_message(String student_message) {
        this.student_message = student_message;
    }

    public void setCoordinates(HashMap coordinates) {
        this.coordinates = coordinates;
    }


}
