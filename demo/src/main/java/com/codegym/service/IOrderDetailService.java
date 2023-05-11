package com.codegym.service;

import com.codegym.dto.IClothesCartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDetailService {

    List<IClothesCartDto> findCartByUser(Integer id);

    void addProductCart(Integer quantity, Integer customerId, Integer productSizeId);

    void descQuantity(Integer id);

    void ascQuantity(Integer id);

    void removeCart(Integer id);

    void paymentClothes(Integer id);

    Page<IClothesCartDto> findAllHistoryCart(Integer id, Pageable pageable);

}
