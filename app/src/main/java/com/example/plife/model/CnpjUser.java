package com.example.plife.model;

import android.location.Address;

import com.example.plife.model.enums.Role;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CnpjUser  extends User {

    private List<TarefaSustentavel> tarefas;

    private String CNPJ;

    // Atributos específicos de empresa (CNPJ)
    private String razaoSocial;
    private String nomeFantasia;


    public CnpjUser(String nameFantasia, String phoneNumber, String email, String password, Role role, String razaoSocial, String cnpj) {
        super(nameFantasia, phoneNumber, email, password, role);
        this.CNPJ = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }



    public CnpjUser(String cnpj, String password, Role role) {
        super(cnpj,password,role);
//        this.CNPJ = cnpj;
    }

    public CnpjUser() {

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

    public List<TarefaSustentavel> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaSustentavel> tarefas) {
        this.tarefas = tarefas;
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

