package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;

public class CadastroController {

    private RegistrationService registrationService;

    public CadastroController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void cadastrarUsuario(String documento, String email, String senha, Role role) {
        String validationResult;

        if (role == Role.ROLE_USER) {
            validationResult = registrationService.validarDocumento(documento);
        } else if (role == Role.ROLE_COMPANY) {
            validationResult = registrationService.validarDocumento(documento);
        } else {
            // Lógica para outros tipos de usuário, se aplicável
            validationResult = "Tipo de usuário não suportado";
        }

        if ("Documento válido".equals(validationResult)) {
            // Ajuste para chamar o método correto com base no tipo de usuário
            if (role == Role.ROLE_USER) {
                registrationService.cadastrarUsuario(new CpfUser(documento, email, senha, role));
            } else if (role == Role.ROLE_COMPANY) {
                registrationService.cadastrarUsuario(new CnpjUser(documento, email, senha, role));
            }

            System.out.println("Cadastro bem-sucedido. Usuário: " + documento + ", Perfil: " + role);
        } else {
            System.out.println("Cadastro falhou. Detalhes: " + validationResult);
        }
    }

    public String validarDocumento(String documento){
        return registrationService.validarDocumento(documento);
    }
}
