package com.example.coffee_shop_exam.service.impl;

import com.example.coffee_shop_exam.model.entity.OrderEntity;
import com.example.coffee_shop_exam.model.service.OrderServiceModel;
import com.example.coffee_shop_exam.model.view.OrderViewModel;
import com.example.coffee_shop_exam.repository.OrderRepository;
import com.example.coffee_shop_exam.sec.CurrentUser;
import com.example.coffee_shop_exam.service.CategoryService;
import com.example.coffee_shop_exam.service.OrderService;
import com.example.coffee_shop_exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ModelMapper modelMapper, CurrentUser currentUser,
                            UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity order = modelMapper.map(orderServiceModel, OrderEntity.class);

        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryName(orderServiceModel.getCategory()));

        orderRepository.save(order);
    }

    @Override
    public List<OrderViewModel> findAllOrderOrdersByPriceDesc() {
        return orderRepository
                .findAllByOrderByPriceDesc()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
