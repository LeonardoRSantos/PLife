package com.example.plife.model;


import com.example.plife.model.enums.Status;

import java.util.Objects;

public class TarefaSustentavel {

    private static long contador = 999; //Inicializei o meu
    private long id;
    private String tipo;
    private String objetivo;
    private int pontos;
    private String descricao;
    private String produto; // Caminho da imagem ou outro identificador

    private boolean validado;

    private CnpjUser cnpjUser;

    private Status status = Status.DISPONIVEL;

    // Construtor e métodos getters e setters

    public TarefaSustentavel(CnpjUser cnpjUser, String tipo, String objetivo, int pontos, String descricao, String produto) {
        this.tipo = tipo;
        this.objetivo = objetivo;
        this.pontos = pontos;
        this.descricao = descricao;
        this.produto = produto;
        this.cnpjUser = cnpjUser;
        this.id = ++contador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CnpjUser getCnpjUser() {
        return cnpjUser;
    }

    public void setCnpjUser(CnpjUser cnpjUser) {
        this.cnpjUser = cnpjUser;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public boolean isValidado() {
        return validado;
    }

    public static long getContador() {
        return contador;
    }

    public static void setContador(long contador) {
        TarefaSustentavel.contador = contador;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarefaSustentavel that = (TarefaSustentavel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
