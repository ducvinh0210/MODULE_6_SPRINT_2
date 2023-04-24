package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Size {
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

    public Set<ClothesSize> getClothesSizes() {
        return clothesSizes;
    }

    public void setClothesSizes(Set<ClothesSize> clothesSizes) {
        this.clothesSizes = clothesSizes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "size")
    private Set<ClothesSize> clothesSizes;

    public Size() {
    }

    public Size(Integer id, String name, boolean isDelete, Set<ClothesSize> clothesSizes) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.clothesSizes = clothesSizes;
    }

}
