package com.tomh.studentspringboot.entity;

import javax.persistence.*;

@Table(name="essay")
public class Essay {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "course")
    private String course;
    @Column(name = "year")
    private String year;
    @Column(name = "link")
    private String link;


// Initialization

    public Essay() {
    }

    public Essay(int id, String name, String course, String link) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.link = link;
    }


// Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
