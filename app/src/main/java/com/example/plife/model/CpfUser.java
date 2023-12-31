package com.example.plife.model;



import com.example.plife.model.enums.Role;

import java.util.Objects;

public class CpfUser extends User {

    private String CPF;


    public CpfUser(String name, String phoneNumber, String email, String password, Role role, String cpf) {
        super(name, phoneNumber, email, password, role);
        this.CPF = cpf;
    }

    public CpfUser(String cpf,String password, Role role) {
        super(cpf,password, role);
        this.CPF = cpf;
    }

    public CpfUser() {

    }


    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CpfUser cpfUser = (CpfUser) o;
        return Objects.equals(CPF, cpfUser.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), CPF);
    }
}
