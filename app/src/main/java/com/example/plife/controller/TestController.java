package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.Endereco;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;

public class TestController {


    private RegistrationService registrationService;

    public TestController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void testarCadastroCPF() {
        // Simulando dados de entrada
        String cpf = "60525455345";
        String email = "leonardo@gmail.com";
        String senha = "123456789";
        Role role = Role.ROLE_USER;

        // Chamando o método de cadastro de CPF
        registrationService.cadastrarUsuario(new CpfUser(cpf, email, senha, role));
    }

    public void testarCadastroCNPJ() {
        // Simulando dados de entrada
        String cnpj = "4755370800013";
        String email = "email@empresa.com";
        String senha = "1234567";
        Role role = Role.ROLE_COMPANY;

        // Chamando o método de cadastro de CNPJ
        registrationService.cadastrarUsuario(new CnpjUser(cnpj, email, senha, role));
    }


}
