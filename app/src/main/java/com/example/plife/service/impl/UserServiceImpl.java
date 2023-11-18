package com.example.plife.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;
import com.example.plife.service.UserService;

import java.util.ArrayList;


public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;
    private List<User> userList;
    private List<CpfUser> userCpfList;
    private List<CnpjUser> userCnpjList;
    private static final String USER_LIST_KEY = "userList";
//    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>() {}.getType();
    private static final Type USER_CPF_LIST_TYPE = new TypeToken<List<CpfUser>>() {}.getType();
    private static final Type USER_CNPJ_LIST_TYPE = new TypeToken<List<CnpjUser>>() {}.getType();
    private SharedPreferences sharedPreferences;
    private Gson gson;


    private UserServiceImpl(Context context) {
        this.userList = new ArrayList<>();
        this.userCpfList = new ArrayList<>();
        this.userCnpjList = new ArrayList<>();
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        this.gson = new Gson();

        // Carrega a lista de usuários do SharedPreferences
        loadUserList();
    }

    public static synchronized UserServiceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new UserServiceImpl(context);
        }
        return instance;
    }

    @Override
    public String cadastrarUsuario(Object user) {
        if (user instanceof CpfUser) {
            CpfUser cpfUser = (CpfUser) user;

            // Verifica se o usuário já está cadastrado com base no CPF
            boolean cpfUserExists = userCpfList.stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .anyMatch(existingUser -> existingUser.getCPF().equals(cpfUser.getCPF()));

            if (cpfUserExists) {
                return "Usuário CPF já existe.";
            }

            // Adiciona o usuário à lista
            userCpfList.add(cpfUser);
            saveUserList(); // Salva a lista de usuários no SharedPreferences
            return "Cadastro bem-sucedido";
        } else if (user instanceof CnpjUser) {
            CnpjUser cnpjUser = (CnpjUser) user;

            // Verifica se o usuário já está cadastrado com base no CNPJ
            boolean cnpjUserExists = userCnpjList.stream()
                    .filter(u -> u instanceof CnpjUser)
                    .map(u -> (CnpjUser) u)
                    .anyMatch(existingUser -> existingUser.getCNPJ().equals(cnpjUser.getCNPJ()));

            if (cnpjUserExists) {
                return "Usuário CNPJ já existe.";
            }

            // Adiciona o usuário à lista
            userCnpjList.add(cnpjUser);

            saveUserList(); // Salva a lista de usuários no SharedPreferences
            return "Cadastro bem-sucedido";
        } else {
            return "Tipo de usuário não suportado";
        }
    }

    @Override
    public String existeUsuario(String login) {
        String documento = login.replace(".", "").replace("-", "").replace("/", "").trim();

        if (documento.length() == 11) {
            boolean cpfUserExists = userCpfList.stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .anyMatch(existingUser -> existingUser.getCPF().equals(documento));

            return cpfUserExists ? "Usuário CPF existe" : "Usuário CPF não existe";
        } else if (documento.length() == 14) {
            boolean cnpjUserExists = userCnpjList.stream()
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

    private void loadUserList() {
        String userCpfListJson = sharedPreferences.getString("userCpfList", null);
        String userCnpjListJson = sharedPreferences.getString("userCnpjList", null);
            if (userCpfListJson != null) {
                userCpfList = gson.fromJson(userCpfListJson, USER_CPF_LIST_TYPE);
            }else if(userCnpjListJson != null){
                userCnpjList = gson.fromJson(userCnpjListJson, USER_CNPJ_LIST_TYPE);

        }

    }



    private void saveUserList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convertendo as listas específicas para JSON
        String userCpfListJson = gson.toJson(userCpfList, USER_CPF_LIST_TYPE);
        String userCnpjListJson = gson.toJson(userCnpjList, USER_CNPJ_LIST_TYPE);

        // Salvando as listas específicas no SharedPreferences
        editor.putString("userCpfList", userCpfListJson);
        editor.putString("userCnpjList", userCnpjListJson);
        editor.apply();
    }
}
