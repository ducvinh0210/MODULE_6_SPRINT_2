package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClothesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;
    @JsonIgnore
    @OneToMany(mappedBy = "clothesType")
    private Set<Clothes> clothes;

    public ClothesType() {
    }

    public ClothesType(Integer id, String name, boolean isDelete, Set<Clothes> clothes) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.clothes = clothes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Set<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(Set<Clothes> clothes) {
        this.clothes = clothes;
    }
}
