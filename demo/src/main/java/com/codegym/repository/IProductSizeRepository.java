package com.codegym.repository;

import com.codegym.dto.IProductSizeDto;
import com.codegym.dto.Quantity;
import com.codegym.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    @Query(value = " select product_size.id as id, product_size.quantity as quantity, size.name as size from product_size join product on product_size.product_id= product.id join size on product_size.size_id= size.id where product.id=:id and product.is_delete= false and product_size.id>0", nativeQuery = true)
    List<IProductSizeDto> findAllSizeByClothes(@Param("id") Integer id);


    @Modifying
    @Query(value = "update product_size set quantity=:quantity where id=:id ", nativeQuery = true)
    void updateQuantity(@Param("quantity") Integer quantity,
                        @Param("id") Integer id);


    @Query(value = " select order_detail.quantity as quantity, product_size.quantity as quantityProduct,  product_size.id as productId \n" +
            " from product_size join order_detail on product_size.id= order_detail.product_size_id\n" +
            " where order_detail.customer_id=:customerId and order_detail.is_pay=false ", nativeQuery = true)
    List<Quantity> findAllProductSize(@Param("customerId") Integer customerId);




}
