package com.example.plife.controller;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;

public class CadastroController {

    private RegistrationService registrationService;

    public CadastroController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    public String cadastrarUsuario(CpfUser cpfUser, CnpjUser cnpjUser){
        if (cpfUser != null && cpfUser.getRole() == Role.ROLE_USER){
            return registrationService.cadastrarUsuario(cpfUser);
        } else if(cnpjUser != null && cnpjUser.getRole() == Role.ROLE_COMPANY){
            return registrationService.cadastrarUsuario(cnpjUser);
        } else {
            return "Tipo de usuário não suportado";
        }
    }



    public String validarDocumento(String documento) {
        return registrationService.validarDocumento(documento);
    }
}
