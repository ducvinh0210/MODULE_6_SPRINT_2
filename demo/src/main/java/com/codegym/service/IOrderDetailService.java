package com.codegym.service;

import com.codegym.dto.IClothesCartDto;

import java.util.List;

public interface IOrderDetailService {

    List<IClothesCartDto> findCartByUser(Integer id);

    void addProductCart(Integer quantity, Integer customerId, Integer productSizeId);

    void descQuantity(Integer id);

    void ascQuantity(Integer id);

    void removeCart(Integer id);

    void paymentClothes(Integer id);
}
