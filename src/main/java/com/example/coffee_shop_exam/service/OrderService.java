package com.example.coffee_shop_exam.service;

import com.example.coffee_shop_exam.model.service.OrderServiceModel;
import com.example.coffee_shop_exam.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {


    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderOrdersByPriceDesc();

    void readyOrder(Long id);
}
