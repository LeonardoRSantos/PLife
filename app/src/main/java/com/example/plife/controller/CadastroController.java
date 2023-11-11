package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;

public class CadastroController {

    private RegistrationService registrationService;

    public CadastroController() {
        this.registrationService = new RegistrationServiceImpl();
    }

    public void cadastrarCpfUser(String cpf, String email, String senha) {
        String validationResult = validarCpf(cpf);
        if ("CPF válido".equals(validationResult)) {
            registrationService.cadastrarCpfUser(new CpfUser(cpf, email, senha, Role.ROLE_USER));
        } else {
            System.out.println("CPF inválido. Cadastro não realizado. Detalhes: " + validationResult);
        }
    }

    public void cadastrarCnpjUser(String cnpj, String email, String senha) {
        String validationResult = validarCnpj(cnpj);
        if ("CNPJ válido".equals(validationResult)) {
            registrationService.cadastrarCnpjUser(new CnpjUser(cnpj, email, senha, Role.ROLE_COMPANY));
        } else {
            System.out.println("CNPJ inválido. Cadastro não realizado. Detalhes: " + validationResult);
        }
    }

    private String validarCpf(String cpf) {
        return registrationService.validarCPF(cpf);
    }

    private String validarCnpj(String cnpj) {
        return registrationService.validarCNPJ(cnpj);
    }
}
