package com.example.mmd;

public class Contacts {
    String name;
    String number;
    String time;
    int profile;

    public Contacts(String name, String number, String time, int profile) {
        this.name = name;
        this.number = number;
        this.time = time;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
}