package com.codegym.repository;

import com.codegym.dto.IProductSizeDto;
import com.codegym.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    @Query(value =" select product_size.id as id, product_size.quantity as quantity, size.name as size from product_size join product on product_size.product_id= product.id join size on product_size.size_id= size.id where product.id=:id and product.is_delete= false and product_size.id>0", nativeQuery = true)
    List<IProductSizeDto> findAllSizeByClothes(@Param("id") Integer id);

}
