package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;


    @OneToMany(mappedBy = "productType")
    private Set<Product> products;


    public ProductType() {
    }

    public ProductType(Integer id, String name, boolean isDelete, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.products = products;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
