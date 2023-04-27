package com.codegym.service;

import com.codegym.dto.IProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Page<IProductDto> showListClothes(String nameProduct, String manufacturerProduct, String typeProduct, double priceStart, double priceEnd, Pageable pageable);
    Page<IProductDto> showListClothesByName(String nameProduct, Pageable pageable);
}
