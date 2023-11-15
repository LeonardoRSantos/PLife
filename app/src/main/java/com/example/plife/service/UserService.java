package com.example.plife.service;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;

import java.util.List;

public interface UserService {

//    String cadastrarUsuario(User user);
//    String existeUsuario(CpfUser cpfUser, CnpjUser cnpjUser);
//    List<CpfUser> getCpfUserList();
//    List<CnpjUser> getCnpjUserList();

    String cadastrarUsuario(User user);

    String existeUsuario(CpfUser cpfUser, CnpjUser cnpjUser);
}
