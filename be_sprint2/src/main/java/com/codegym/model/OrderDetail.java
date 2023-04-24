package com.codegym.model;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String datePayment;
    private Integer quantity;
    private boolean isDelete;
    private boolean isPay;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "clothes_size_id", referencedColumnName = "id")
    private ClothesSize clothesSize;


    public OrderDetail() {
    }

    public OrderDetail(String id, String datePayment, Integer quantity, boolean isDelete, boolean isPay, Customer customer, ClothesSize clothesSize) {
        this.id = id;
        this.datePayment = datePayment;
        this.quantity = quantity;
        this.isDelete = isDelete;
        this.isPay = isPay;
        this.customer = customer;
        this.clothesSize = clothesSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ClothesSize getClothesSize() {
        return clothesSize;
    }

    public void setClothesSize(ClothesSize clothesSize) {
        this.clothesSize = clothesSize;
    }
}
