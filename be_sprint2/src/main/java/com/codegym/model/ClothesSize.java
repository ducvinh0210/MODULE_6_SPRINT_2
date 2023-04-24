package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ClothesSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "clothesSize")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "clothes_id", referencedColumnName = "id")
    private Clothes clothes;
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size size;

    public ClothesSize() {
    }

    public ClothesSize(Integer id, String name, boolean isDelete, Set<OrderDetail> orderDetails, Clothes clothes, Size size) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.orderDetails = orderDetails;
        this.clothes = clothes;
        this.size = size;
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

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
