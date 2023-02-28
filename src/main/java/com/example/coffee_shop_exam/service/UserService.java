package com.example.coffee_shop_exam.service;

import com.example.coffee_shop_exam.model.entity.UserEntity;
import com.example.coffee_shop_exam.model.service.UserServiceModel;
import com.example.coffee_shop_exam.model.view.UserViewModel;

import java.util.List;

public interface UserService {


    UserServiceModel  registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);

    List<UserViewModel> findAllUserAndCountOfOrdersOrderByCountDesc();

    UserEntity findByName(String username);

    UserEntity findByEmail(String email);
}
