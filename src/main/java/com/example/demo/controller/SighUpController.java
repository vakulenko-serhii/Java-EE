package com.example.demo.controller;

import com.example.demo.model.UserEntity;
import com.example.demo.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SighUpController {

    private final UserRepository userRepository;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@Valid @RequestBody final UserEntity user) {

        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        userRepository.save(user);

    }
}
