package com.example.plife.service;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;

import java.util.List;

public interface UserService {
      String cadastrarUsuario(Object user);

    String existeUsuario(CpfUser cpfUser, CnpjUser cnpjUser);
}
