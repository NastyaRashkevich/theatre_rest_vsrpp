package org.example.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = true, length = 45)
    private String date;
    @Column(name = "place", nullable = true, length = 45)
    private String place;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=Performance.class, mappedBy = "repetition", cascade = {CascadeType.ALL})
    private Collection<Performance> performances;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=Actor.class, mappedBy = "repetition", cascade = {CascadeType.ALL})
    private Collection<Actor> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Collection<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(Collection<Performance> performances) {
        this.performances = performances;
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Репетиция " + id +
                ": дата - " + date +
                ", место - " + place +
                ", спектакль - " + performances +
                ", актёры - " + actors + ".";
    }
}
