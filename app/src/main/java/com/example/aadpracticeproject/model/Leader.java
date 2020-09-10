package com.example.aadpracticeproject.model;

public class Leader {
    private String name;
    private String country;
    private String badgeUrl;
    private int hours;

    public Leader(String name, String country, String badgeUrl, int hours) {
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }
}
