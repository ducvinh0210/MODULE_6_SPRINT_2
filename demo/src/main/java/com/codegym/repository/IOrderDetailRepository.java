package com.codegym.repository;

import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "select ")
}
