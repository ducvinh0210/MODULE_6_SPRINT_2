package com.codegym.service;

import com.codegym.dto.IProductDto;
import com.codegym.dto.IQuantity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IProductService {

    Page<IProductDto> showListClothes(String nameProduct, String manufacturerProduct, String typeProduct, Integer priceStart, Integer priceEnd, Pageable pageable);


    Page<IProductDto> showList(String nameProduct, Pageable pageable);

    IProductDto findById(Integer id);

    Integer findByIdSize( Integer idSize, Integer idProduct);

//    Integer checkQuantity(Integer idSize, Integer idProduct, Integer quantityChose);


}
