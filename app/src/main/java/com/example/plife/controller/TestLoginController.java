package com.example.plife.controller;

import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;

public class TestLoginController {
    private LoginService loginService;
    private RegistrationService registrationService;
    private UserService userService;

    public TestLoginController(LoginService loginService, RegistrationService registrationService, UserService userService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    public void testarLogin() {
        // Simulando dados de entrada
//        String cpf = "60525455345";
        String senha = "123456789";
        String email = "leonardo@gmail.com";
//        String cnpj = "47553708000133";
//        String emailEmpresa = "email@empresa.com";

//        String senhaEmpresa = "1234567";

        // Testando login com CPF
        String resultadoLoginCPF = loginService.fazerLogin(email, senha);
        System.out.println(resultadoLoginCPF);

        // Testando login com email
//        String resultadoLoginEmail = loginService.fazerLogin(email, senha);
//        System.out.println(resultadoLoginEmail);
//
//        // Testando login com CNPJ
//        String resultadoLoginCNPJ = loginService.fazerLogin(cnpj, senhaEmpresa);
//        System.out.println(resultadoLoginCNPJ);
//
//        // Testando login com email da empresa
//        String resultadoLoginEmailEmpresa = loginService.fazerLogin(emailEmpresa, senhaEmpresa);
//        System.out.println(resultadoLoginEmailEmpresa);
    }
}
