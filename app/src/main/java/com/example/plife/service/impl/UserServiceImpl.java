package com.example.plife.service.impl;


import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private List<User> userList;

    public UserServiceImpl() {
        this.userList = new ArrayList<>();
    }

    @Override
    public String cadastrarUsuario(User user) {
        // Verifica se o usuário já está cadastrado
        if (userList.contains(user)) {
            return "Usuário já cadastrado.";
        }

        // Adiciona o usuário à lista
        userList.add(user);

        return "Cadastro bem-sucedido";
    }



    public List<User> getUserList() {
        return userList;
    }


}
