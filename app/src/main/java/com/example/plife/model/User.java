package com.example.plife.model;



import com.example.plife.model.enums.Role;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private Endereco endereco;
    private Date birthDate;
    private String phoneNumber;
    private Role role; // Adicione o atributo 'role' para definir o papel do usuário

    private int pontos;

    private List<String> insignias;  // Lista de insignias ganhas



    public User(String name, String phoneNumber, String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;// Defina o papel ao criar o usuário
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(String login, String password, Role role) {
        this.name = login;
        this.password = password;
        this.role = role;
    }

    public User() {

    }


    // Métodos getters e setters para acessar e definir os atributos

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public List<String> getInsignias() {
        return insignias;
    }

    public void setInsignias(List<String> insignias) {
        this.insignias = insignias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(endereco, user.endereco) && Objects.equals(birthDate, user.birthDate) && Objects.equals(phoneNumber, user.phoneNumber) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, endereco, birthDate, phoneNumber, role);
    }
}
