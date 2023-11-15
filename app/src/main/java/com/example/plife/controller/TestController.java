package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.Endereco;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;
import com.example.plife.service.impl.RegistrationServiceImpl;

public class TestController {


    private RegistrationService registrationService;
    private UserService userService;

    private LoginService loginService;

    public TestController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
        this.loginService = loginService;

    }


    public void testarCadastroCPF() {
        // Simulando dados de entrada
//        String cpf = "60525455345";
//        String email = "leonardo@gmail.com";
//        String senha = "123456789";
//        Role role = Role.ROLE_USER;

        CpfUser cpfUser = new CpfUser("60525455345", "leonardo@gmail.com", "123456789", Role.ROLE_USER);


        // Chamando o método de cadastro de CPF
        registrationService.cadastrarUsuario(cpfUser);

//        testarLogin(cpfUser);

    }

//    public void testarLogin(CpfUser cpfUser) {
//        String resultadoLoginCPF = loginService.fazerLogin(cpfUser);
//        System.out.println(resultadoLoginCPF);
//    }
//    String resultadoLoginCPF = loginService.fazerLogin(cpfUser);
//        System.out.println(resultadoLoginCPF);
//    public void testarCadastroCNPJ() {
//        // Simulando dados de entrada
//        String cnpj = "4755370800013";
//        String email = "email@empresa.com";
//        String senha = "1234567";
//        Role role = Role.ROLE_COMPANY;
//
//        // Chamando o método de cadastro de CNPJ
//        registrationService.cadastrarUsuario(new CnpjUser(cnpj, email, senha, role));
//    }


}
