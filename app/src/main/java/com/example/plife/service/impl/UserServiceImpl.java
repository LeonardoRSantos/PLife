package com.example.plife.service.impl;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService { // Classe Singleton

    private List<User> userList;
    private static UserServiceImpl instance;


    public UserServiceImpl() {
        this.userList = new ArrayList<>();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public String cadastrarUsuario(Object user) {
        if (user instanceof CpfUser) {
            CpfUser cpfUser = (CpfUser) user;

            // Verifica se o usuário já está cadastrado com base no CPF
            boolean cpfUserExists = userList.stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .anyMatch(existingUser -> existingUser.getCPF().equals(cpfUser.getCPF()));

            if (cpfUserExists) {
                return "Usuário CPF já existe.";
            }

            // Adiciona o usuário à lista
            userList.add(cpfUser);
//            System.out.println("Cadastro bem-sucedido");
//            System.out.println("User List: " + userList);
            return "Cadastro bem-sucedido";
        } else if (user instanceof CnpjUser) {
            CnpjUser cnpjUser = (CnpjUser) user;

            // Verifica se o usuário já está cadastrado com base no CNPJ
            boolean cnpjUserExists = userList.stream()
                    .filter(u -> u instanceof CnpjUser)
                    .map(u -> (CnpjUser) u)
                    .anyMatch(existingUser -> existingUser.getCNPJ().equals(cnpjUser.getCNPJ()));

            if (cnpjUserExists) {
                return "Usuário CNPJ já existe.";
            }

            // Adiciona o usuário à lista
            userList.add(cnpjUser);
            return "Cadastro bem-sucedido";
        } else {
            return "Tipo de usuário não suportado";
        }
    }


    @Override
    public String existeUsuario(String login) {
        String documento = login.replace(".", "").replace("-", "").replace("/", "").trim();

        if (documento.length() == 11) {
            boolean cpfUserExists = userList.stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .anyMatch(existingUser -> existingUser.getCPF().equals(documento));

            return cpfUserExists ? "Usuário CPF existe" : "Usuário CPF não existe";
        } else if (documento.length() == 14) {
            boolean cnpjUserExists = userList.stream()
                    .filter(u -> u instanceof CnpjUser)
                    .map(u -> (CnpjUser) u)
                    .anyMatch(existingUser -> existingUser.getCNPJ().equals(documento));

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
