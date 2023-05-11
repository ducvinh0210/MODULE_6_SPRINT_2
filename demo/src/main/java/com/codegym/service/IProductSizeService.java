package com.codegym.service;

import com.codegym.dto.IProductSizeDto;
import com.codegym.dto.Quantity;
import com.codegym.model.ProductSize;

import java.util.List;

public interface IProductSizeService {
    List<IProductSizeDto> findAllSizeByClothes(Integer id);

    void updateQuantity(Integer quantity, Integer id);

    List<Quantity> findAllProductSizeList(Integer customerId);


}
