package com.example.plife.service.impl;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;
import com.example.plife.service.util.SessionManager;

public class LoginServiceImpl implements LoginService {

    private RegistrationService registrationService;
    private UserService userService;

    public LoginServiceImpl(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }


    @Override
    public String fazerLogin(String login, String senha) {
        String documento = login.replace(".", "").replace("-", "").replace("/", "").trim();

        if (documento.length() == 11) { // Se o login é um CPF
            CpfUser cpfUser = userService.getUsersCpf().stream()
                    .filter(u -> u instanceof CpfUser)
                    .map(u -> (CpfUser) u)
                    .filter(existingUser -> existingUser.getCPF().equals(documento))
                    .findFirst()
                    .orElse(null);

            if (cpfUser != null && senha.equals(cpfUser.getPassword())) {
                SessionManager.getInstance().setCpfUser(cpfUser);
                return "Login bem-sucedido";
            } else {
                return "Login falhou. Usuário CPF não cadastrado ou senha incorreta.";
            }
        } else if (documento.length() == 14) { // Se o login é um CNPJ
            CnpjUser cnpjUser = userService.getUsersCnpj().stream()
                    .filter(u -> u instanceof CnpjUser)
                    .map(u -> (CnpjUser) u)
                    .filter(existingUser -> existingUser.getCNPJ().equals(documento))
                    .findFirst()
                    .orElse(null);

            if (cnpjUser != null && senha.equals(cnpjUser.getPassword())) {
                SessionManager.getInstance().setCnpjUser(cnpjUser);
                return "Login bem-sucedido";
            } else {
                return "Login falhou. Usuário CNPJ não cadastrado ou senha incorreta.";
            }
        } else if (documento.equals("leonardo")) { // Se o login é um ADMIN
            User adminUser = userService.getUsersAdminList().stream()
                    .filter(u -> u instanceof User)
                    .map(u -> (User) u)
                    .filter(existingUser -> existingUser.getName().equals(documento))
                    .findFirst()
                    .orElse(null);

            if (adminUser != null && senha.equals(adminUser.getPassword())) {
                SessionManager.getInstance().setUserAdmin(adminUser);
                return "Login bem-sucedido";
            } else {
                return "Login falhou. Usuário admin não cadastrado ou senha incorreta.";
            }
        } else {
            return "Tipo de usuário não suportado para login.";
        }
    }
}



