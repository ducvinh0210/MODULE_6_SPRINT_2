package com.codegym.service;

import com.codegym.dto.IProductSizeDto;

import java.util.List;

public interface IProductSizeService {
    List<IProductSizeDto>findAllSizeByClothes(Integer id);

}
