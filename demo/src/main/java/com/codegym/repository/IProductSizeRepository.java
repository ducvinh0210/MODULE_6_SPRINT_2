package com.codegym.repository;

import com.codegym.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer> {
}
