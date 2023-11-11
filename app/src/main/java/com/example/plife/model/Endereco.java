package com.example.plife.model;

public class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private String CEP;
    private String pais;



    public Endereco(String rua, String cidade, String estado, String CEP, String pais) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;
        this.pais = pais;
    }

    // Métodos getters e setters para acessar e definir os atributos

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }





}
