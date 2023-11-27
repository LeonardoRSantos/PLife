package com.example.plife.service.impl;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;
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
        if (login.length() == 14) { // Se o login é um CPF
            CpfUser cpfUser = new CpfUser(login, senha, Role.ROLE_USER);
            String validationResult = registrationService.validarDocumento(login);

            if ("Documento válido".equals(validationResult)) {
                String existeUsuarioResult = userService.existeUsuario(login);

                if ("Usuário CPF existe".equals(existeUsuarioResult) && senha.equals(cpfUser.getPassword())) {
                    return "Login bem-sucedido";
                } else {
                    return "Login falhou. Usuário CPF não cadastrado ou senha incorreta.";
                }
            } else {
                return "Login falhou. Verifique suas credenciais.";
            }
        } else if (login.length() == 18) { // Se o login é um CNPJ
            CnpjUser cnpjUser = new CnpjUser(login, senha, Role.ROLE_COMPANY);
            String validationResult = registrationService.validarDocumento(login);

            if ("Documento válido".equals(validationResult)) {
                String existeUsuarioResult = userService.existeUsuario(login);

                if ("Usuário CNPJ existe".equals(existeUsuarioResult) && senha.equals(cnpjUser.getPassword())) {
                    return "Login bem-sucedido";
                } else {
                    return "Login falhou. Usuário CNPJ não cadastrado ou senha incorreta.";
                }
            } else {
                return "Login falhou. Verifique suas credenciais.";
            }
        }else if (login.equals("leonardo")) { // Se o login é um ADMIN // Ajustar aqui depois.
            User user = new User(login, senha, Role.ROLE_ADMIN);
                if (login.equals("leonardo") && senha.equals(user.getPassword())) {
                    return "Login bem-sucedido";
            } else {
                return "Login falhou. Verifique suas credenciais.";
            }
        }else {
            return "Tipo de usuário não suportado para login.";
        }
    }
}
