package com.example.plife.model;



import com.example.plife.model.enums.Role;

import java.util.Date;

public class CpfUser extends User {

    private String CPF;

    public CpfUser(String cpf, String email, String password, Role role) {
        super(email, password, role);
        this.CPF = cpf;
    }




    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
