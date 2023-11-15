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
    public String fazerLogin(String login, String senha) {
        if (login.matches("\\d{11}")) { // Se o login é um CPF
            CpfUser cpfUser = new CpfUser(login, null, senha, null);
            String validationResult = registrationService.validarCPF(cpfUser.getCPF());

            if ("CPF válido".equals(validationResult)) {
                String existeUsuarioResult = userService.existeUsuario(cpfUser, null);

                if ("Usuário CPF existe".equals(existeUsuarioResult) && senha.equals(cpfUser.getPassword())) {
                    return "Login bem-sucedido. Usuário: " + cpfUser.getCPF() + ", Perfil: " + Role.ROLE_USER;
                } else {
                    return "Login falhou. Usuário CPF não cadastrado ou senha incorreta.";
                }
            } else {
                return "Login falhou. Verifique suas credenciais.";
            }
        } else if (login.matches("\\d{14}")) { // Se o login é um CNPJ
            CnpjUser cnpjUser = new CnpjUser(login, null, senha, null);
            String validationResult = registrationService.validarCNPJ(cnpjUser.getCNPJ());

            if ("CNPJ válido".equals(validationResult)) {
                String existeUsuarioResult = userService.existeUsuario(null, cnpjUser);

                if ("Usuário CNPJ existe".equals(existeUsuarioResult) && senha.equals(cnpjUser.getPassword())) {
                    return "Login bem-sucedido. Usuário: " + cnpjUser.getCNPJ() + ", Perfil: " + Role.ROLE_COMPANY;
                } else {
                    return "Login falhou. Usuário CNPJ não cadastrado ou senha incorreta.";
                }
            } else {
                return "Login falhou. Verifique suas credenciais.";
            }
        } else if (login.contains("@")) { // Se o login é um email
            // Lógica de validação de email
            // ...

            // Restante da lógica de login com email
            // ...

            return "Login bem-sucedido. Usuário: " + login + ", Perfil: [Defina o perfil aqui]";
        } else {
            return "Tipo de usuário não suportado para login.";
        }
    }
}
