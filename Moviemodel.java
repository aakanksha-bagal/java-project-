package com.example.jpt;

import javafx.collections.ObservableList;

public class Moviemodel {

    String movies;
    String times;
    String dates;
    String rows;
    int columns;


    public Moviemodel( String movies, String dates,String rows,int columns ,String times) {

        this.movies = movies;
        this.times = times;
        this.dates = dates;
        this.rows = rows;
        this.columns = columns;


    }



    public static void setItems(ObservableList<Moviemodel> items) {
    }

    public String getMovies() { return movies;}

    public String getTimes() {return times;}

    public String getDates() {return dates;}

    public String getRows() {return rows;}

    public int getColumns() {return columns;}
}
