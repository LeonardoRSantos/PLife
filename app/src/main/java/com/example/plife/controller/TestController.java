package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.Endereco;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;

public class TestController {

    private RegistrationService registrationService;

    public TestController() {
        this.registrationService = new RegistrationServiceImpl();
    }

    public void testarCadastroCPF() {
        // Simulando dados de entrada
        CpfUser cpfUser = new CpfUser("60525455345", "leonardo@gmail.com","123456789", Role.ROLE_USER);


        registrationService.cadastrarCpfUser(cpfUser);


    }

    public void testarCadastroCNPJ() {
        // Simulando dados de entrada
        CnpjUser cnpjUser = new CnpjUser("475537080001333", "email@empresa.com", "1234567", Role.ROLE_COMPANY);


        // Chamando o método de cadastro de CNPJ
        registrationService.cadastrarCnpjUser(cnpjUser);

    }


}
