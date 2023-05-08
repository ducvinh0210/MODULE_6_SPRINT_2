package com.codegym.repository;

import com.codegym.dto.IClothesCartDto;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Transactional
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "select order_detail.id as id, product.name as name, `size`.name as size , product.price as price , product.discount as discount, order_detail.quantity as quantity, product.image as image" +
            " from order_detail join product_size on product_size.id= order_detail.product_size_id join  product on product.id=product_size.product_id join size on size.id = product_size.size_id" +
            " where order_detail.is_pay= false and order_detail.is_delete= false and order_detail.quantity>0 and order_detail.customer_id =:id  ", nativeQuery = true)
    List<IClothesCartDto> findCartByUser(@Param("id") Integer id);


    @Modifying
    @Query(value = " insert into order_detail(date_payment, quantity, customer_id, product_size_id) value(now(),:quantity,:customerId, :productSizeId) ", nativeQuery = true)
    void addOrderDetailCart(@Param("quantity") Integer quantity,
                            @Param("customerId") Integer customerId,
                            @Param("productSizeId") Integer productSizeId);

    @Modifying
    @Query(value = "update order_detail set quantity= (quantity-1) where is_pay=false and id=:id", nativeQuery = true)
    void descQuantity(@Param("id") Integer id);

    @Modifying
    @Query(value = "update order_detail set quantity= (quantity+1) where  is_pay =false  and id=:id", nativeQuery = true)
    void ascQuantity(@Param("id") Integer id);


    @Modifying
    @Query(value = "delete from order_detail where id=:id", nativeQuery = true)
    void removeCart(@Param("id") Integer id);

    @Modifying
    @Query(value = "update order_detail set date_payment= now(), is_pay= true where is_pay = false and customer_id =:id", nativeQuery = true)
    void paymentClothes(@Param("id") Integer id);

}
