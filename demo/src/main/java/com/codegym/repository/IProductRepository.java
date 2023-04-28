package com.codegym.repository;


import com.codegym.dto.IProductDto;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select `product`.id as id, `product`.name as name , `product`.discount as discount, `product`.price as price, `product`.image as image,`product`.manufacturer as manufacturer, `product`.describes as describes, `product_type`.name as type from `product` join `product_type` on `product`.product_type_id= `product_type`.id join `product_size` on `product`.id= `product_size`.product_id where `product`.is_delete= false and `product`.name like %:nameProduct% and `product`.manufacturer like %:manufacturerProduct% and `product_type`.name like %:typeProduct%  and (`product`.price between :priceStart and :priceEnd) group by `product`.id having sum(`product_size`.quantity) > 0"
            , countQuery = "select count(*)  `product` join `product_type` on `product`.product_type_id= `product_type`.id join `product_size` on product.id= `product_size`.product_id  where `product`.is_delete= false and `product`.name like %:nameProduct and `product`.manufacturer like %:manufacturerProduct% and `product_type`.name like %:like% and and (`product`.price between :priceStart and :priceEnd) group by `product`.id having sum(`product_size`.quantity) > 0", nativeQuery = true)
    Page<IProductDto> showListClothes(@Param("nameProduct") String nameProduct,
                                      @Param("manufacturerProduct") String manufacturerProduct,
                                      @Param("typeProduct") String typeProduct,
                                      @Param("priceStart") Integer priceStart,
                                      @Param("priceEnd") Integer priceEnd,
                                      Pageable pageable);


    @Query(value = " select `product`.id as id, `product`.name as name , `product`.discount as discount, `product`.price as price, `product`.image as image,`product`.manufacturer as manufacturer, `product`.describes as describes, `product_type`.name as type from `product` join `product_type` on `product`.product_type_id= `product_type`.id where product.name like %:nameProduct% and product.is_delete = false", nativeQuery = true)
    Page<IProductDto> showList(@Param("nameProduct") String nameProduct, Pageable pageable);


}
