package org.example.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Column(name = "telephone", nullable = true, length = 45)
    private String telephone;
    @Column(name = "experience", nullable = true, length = 45)
    private String experience;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=Performance.class, mappedBy = "director", cascade = {CascadeType.ALL})
    private Collection<Performance> performances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Collection<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(Collection<Performance> performances) {
        this.performances = performances;
    }

    @Override
    public String toString() {
        return "Режиссёр " + id +
                ":  имя - " + name +
                ", тлф - " + telephone +
                ", опыт - " + experience + " лет.";
    }
}
