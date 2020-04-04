package com.example.eq;

public class User {

    private String id;
    private String password;
    private String userEmail;
    private String timeSlot;
    private int part;

    public User(String id, String password, String userEmail) {
        this.id = id;
        this.password = password;
        this.userEmail = userEmail;
        this.timeSlot = "00";
        this.part = 0;
    }

    public User(){

    }

    public String getId() {
        return id;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}
