package com.codegym.service.impl;

import com.codegym.dto.IClothesCartDto;
import com.codegym.repository.IOrderDetailRepository;
import com.codegym.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;


    @Override
    public List<IClothesCartDto> findCartByUser(Integer id) {
        return iOrderDetailRepository.findCartByUser(id);
    }

    @Override
    public void addProductCart(Integer quantity, Integer customerId, Integer productSizeId) {
        iOrderDetailRepository.addOrderDetailCart(quantity, customerId, productSizeId);
    }

    @Override
    public void descQuantity(Integer id) {
        iOrderDetailRepository.descQuantity(id);
    }

    @Override
    public void ascQuantity(Integer id) {
        iOrderDetailRepository.ascQuantity(id);
    }

    @Override
    public void removeCart(Integer id) {
        iOrderDetailRepository.removeCart(id);
    }

    @Override
    public void paymentClothes(Integer id) {
        iOrderDetailRepository.paymentClothes(id);
    }

    @Override
    public Page<IClothesCartDto> findAllHistoryCart(Integer id, Pageable pageable) {
        return iOrderDetailRepository.findAllHistoryCart(id,pageable);
    }

}
