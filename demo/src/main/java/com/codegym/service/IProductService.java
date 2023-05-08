package com.codegym.service;

import com.codegym.dto.IProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Page<IProductDto> showListClothes(String nameProduct, String manufacturerProduct, String typeProduct, Integer priceStart, Integer priceEnd, Pageable pageable);


    Page<IProductDto> showList(String nameProduct, Pageable pageable);

    IProductDto findById(Integer id);


}
