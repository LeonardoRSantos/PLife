package com.example.plife.service;


public interface RegistrationService {

    String cadastrarUsuario(Object user);

    String validarCPF(String cpfUser);

    String validarCNPJ(String cnpjUser);
}
