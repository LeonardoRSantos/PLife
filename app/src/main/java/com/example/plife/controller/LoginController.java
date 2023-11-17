package com.example.plife.controller;


import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;

public class LoginController {

    LoginService loginService;

    public  LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    public String LoginUsuario(CpfUser cpfUser, CnpjUser cnpjUser){
        if (cpfUser.getRole() == Role.ROLE_USER){
            return loginService.fazerLogin(cpfUser.getCPF(), cpfUser.getPassword());
        }else if(cnpjUser.getRole() == Role.ROLE_COMPANY){
            return loginService.fazerLogin(cnpjUser.getCNPJ(), cnpjUser.getPassword());
        }else {
            return "Login ou senha inválidos.";
        }

    }
}
