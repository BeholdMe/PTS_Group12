package com.example.pts.Search;

public class Tutor {
    String name;
    String category;
    String location;
    String distance;
    String time;
    String price;

    public Tutor(String name, String category, String location, String distance, String time, String price) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.distance = distance;
        this.time = time;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    public String getLocation() {
        return location;
    }
    public String getDistance() {
        return distance;
    }
    public String getTime() {
        return time;
    }
    public String getPrice() {
        return price;
    }

    /*(@Override
    public int compareTo(Tutor other) {

        return Integer.compare(Integer.parseInt(this.price), Integer.parseInt(other.price));
    }*/
}
