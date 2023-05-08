package com.codegym.service.impl;


import com.codegym.dto.IProductSizeDto;
import com.codegym.repository.IProductSizeRepository;
import com.codegym.service.IProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeService implements IProductSizeService {

    @Autowired
    private IProductSizeRepository iProductSizeRepository;

    @Override
    public List<IProductSizeDto> findAllSizeByClothes(Integer id) {
        return iProductSizeRepository.findAllSizeByClothes(id);
    }
}
