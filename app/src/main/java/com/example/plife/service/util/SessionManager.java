package com.example.plife.service.util;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.User;

public class SessionManager {
    private static SessionManager instance;

    private CpfUser cpfUser;
    private CnpjUser cnpjUser;

    private User userAdmin;

    private SessionManager() {
        // Evita instância direta
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public CpfUser getCpfUser() {
        return cpfUser;
    }

    public void setCpfUser(CpfUser cpfUser) {
        this.cpfUser = cpfUser;
        this.cnpjUser = null; // Limpa o usuário CNPJ ao definir um usuário CPF
        this.userAdmin = null;
    }

    public CnpjUser getCnpjUser() {
        return cnpjUser;
    }

    public void setCnpjUser(CnpjUser cnpjUser) {
        this.cnpjUser = cnpjUser;
        this.cpfUser = null; // Limpa o usuário
        this.userAdmin = null;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public User getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(User userAdmin) {
        this.userAdmin = userAdmin;
        this.cpfUser = null; // Limpa o usuário
        this.cnpjUser = null;
    }
}
