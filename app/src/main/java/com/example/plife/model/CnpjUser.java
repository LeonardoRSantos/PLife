package com.example.plife.model;

import android.location.Address;

import com.example.plife.model.enums.Role;

import java.util.Date;
import java.util.Objects;

public class CnpjUser  extends User {

    private String CNPJ;

    // Atributos específicos de empresa (CNPJ)
    private String razaoSocial;
    private String nomeFantasia;

    public CnpjUser(String cnpj, String email, String password, Role role) {
        super(email, password, role);
        this.CNPJ = cnpj;
    }


    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CnpjUser cnpjUser = (CnpjUser) o;
        return Objects.equals(CNPJ, cnpjUser.CNPJ) && Objects.equals(razaoSocial, cnpjUser.razaoSocial) && Objects.equals(nomeFantasia, cnpjUser.nomeFantasia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), CNPJ, razaoSocial, nomeFantasia);
    }
}

