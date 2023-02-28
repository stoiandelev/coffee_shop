package com.example.coffee_shop_exam.service.impl;

import com.example.coffee_shop_exam.model.entity.UserEntity;
import com.example.coffee_shop_exam.model.service.UserServiceModel;
import com.example.coffee_shop_exam.model.view.UserViewModel;
import com.example.coffee_shop_exam.repository.UserRepository;
import com.example.coffee_shop_exam.sec.CurrentUser;
import com.example.coffee_shop_exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUserNameAndPassword(String username, String password) {

        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);

    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser
                .setId(id)
                .setUsername(username);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUserAndCountOfOrdersOrderByCountDesc() {

        return userRepository
                .findAllByOrdersCountDescending()
                .stream()
                .map(userEntity -> {
                    UserViewModel userViewModel = new UserViewModel();

                    userViewModel
                            .setUsername(userEntity.getUsername())
                            .setCountOfOrders(userEntity.getOrders().size());

                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository
                .findByUsername(username)
                .orElse(null);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElse(null);
    }


}
