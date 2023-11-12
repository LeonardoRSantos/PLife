package com.example.plife.service.impl;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;

public class LoginServiceImpl implements LoginService {

    private RegistrationService registrationService;
    private UserService userService;

    public LoginServiceImpl(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @Override
    public String fazerLoginCpf(CpfUser cpfUser) {
        String validationResult = registrationService.validarCPF(cpfUser.getCPF());

        if ("CPF válido".equals(validationResult)) {
            String existeUsuarioResult = userService.existeUsuario(cpfUser, null);

            if ("Usuário CPF existe".equals(existeUsuarioResult)) {
                return "Login bem-sucedido. Usuário: " + cpfUser.getCPF() + ", Perfil: " + Role.ROLE_USER;
            } else {
                return "Login falhou. Usuário CPF não cadastrado.";
            }
        } else {
            return "Login falhou. Verifique suas credenciais.";
        }
    }

    @Override
    public String fazerLoginCnpj(CnpjUser cnpjUser) {
        String validationResult = registrationService.validarCNPJ(cnpjUser.getCNPJ());

        if ("CNPJ válido".equals(validationResult)) {
            String existeUsuarioResult = userService.existeUsuario(null, cnpjUser);

            if ("Usuário CNPJ existe".equals(existeUsuarioResult)) {
                return "Login bem-sucedido. Usuário: " + cnpjUser.getCNPJ() + ", Perfil: " + Role.ROLE_COMPANY;
            } else {
                return "Login falhou. Usuário CNPJ não cadastrado.";
            }
        } else {
            return "Login falhou. Verifique suas credenciais.";
        }
    }
}
