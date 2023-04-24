package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private Integer discount;
    private String manufacturer;
    private String image;
    private String describes;
    private boolean isDelete;


    @JsonIgnore
    @OneToMany(mappedBy = "clothes")
    private Set<ClothesSize> clothesSizes;

    @ManyToOne
    @JoinColumn(name = "clothes_type_id", referencedColumnName = "id")
    private ClothesType clothesType;
    public Clothes() {
    }

    public Clothes(Integer id, String name, double price, Integer discount, String manufacturer, String image, String describes, boolean isDelete, Set<ClothesSize> clothesSizes, ClothesType clothesType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.manufacturer = manufacturer;
        this.image = image;
        this.describes = describes;
        this.isDelete = isDelete;
        this.clothesSizes = clothesSizes;
        this.clothesType = clothesType;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
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

    public ClothesType getClothesType() {
        return clothesType;
    }

    public void setClothesType(ClothesType clothesType) {
        this.clothesType = clothesType;
    }
}
