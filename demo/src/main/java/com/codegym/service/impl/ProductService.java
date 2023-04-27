package com.codegym.service.impl;


import com.codegym.dto.IProductDto;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
 @Autowired
 private IProductRepository iProductRepository;

    @Override
    public Page<IProductDto> showListClothes(String nameProduct, String manufacturerProduct, String typeProduct, double priceStart, double priceEnd, Pageable pageable) {
        return iProductRepository.showListClothes(nameProduct, manufacturerProduct, typeProduct,priceStart, priceEnd, pageable);
    }

    @Override
    public Page<IProductDto> showListClothesByName(String nameProduct, Pageable pageable) {
        return iProductRepository.showListByName(nameProduct,pageable);
    }
}
