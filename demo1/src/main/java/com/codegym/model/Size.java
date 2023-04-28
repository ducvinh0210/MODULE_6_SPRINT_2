package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;
    @OneToMany(mappedBy = "size")
    private Set<ProductSize> productSizes;

    public Size() {
    }

    public Size(Integer id, String name, boolean isDelete, Set<ProductSize> productSizes) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.productSizes = productSizes;
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

    public Set<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }
}
