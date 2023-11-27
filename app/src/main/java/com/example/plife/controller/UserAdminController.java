package com.example.plife.controller;

import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.impl.UserServiceImpl;

public class UserAdminController {

    private UserServiceImpl userService;

    public UserAdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void cadastrarUsuarioAdmin(String nomeUsuario, String senha) {
        User adminUser = new User(nomeUsuario, senha, Role.ROLE_ADMIN);
        userService.cadastrarUsuario(adminUser);
    }
}
