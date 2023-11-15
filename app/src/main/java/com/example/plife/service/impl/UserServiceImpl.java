package com.example.plife.service.impl;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
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
        System.out.println("Cadastro bem-sucedido");
        System.out.println("User List: " + userList);
        return "Cadastro bem-sucedido";
    }

    @Override
    public String existeUsuario(CpfUser cpfUser, CnpjUser cnpjUser) {
        // Adicione um log para verificar o estado da userList
        System.out.println("User List: " + userList.toString());

        // Verifique se o usuário existe com base no CPF ou CNPJ
        if (cpfUser != null) {
            boolean cpfUserExists = userList.stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .anyMatch(existingUser -> existingUser.getCPF().equals(cpfUser.getCPF()));

            return cpfUserExists ? "Usuário CPF existe" : "Usuário CPF não existe";
        } else if (cnpjUser != null) {
            boolean cnpjUserExists = userList.stream()
                    .filter(u -> u instanceof CnpjUser)
                    .map(u -> (CnpjUser) u)
                    .anyMatch(existingUser -> existingUser.getCNPJ().equals(cnpjUser.getCNPJ()));

            return cnpjUserExists ? "Usuário CNPJ existe" : "Usuário CNPJ não existe";
        }
        return "Parâmetros inválidos";
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
