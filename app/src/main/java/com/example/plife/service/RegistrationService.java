    package com.example.plife.service;

    import com.example.plife.model.Endereco;
    import com.example.plife.model.CnpjUser;
    import com.example.plife.model.CpfUser;

    public interface RegistrationService {

        String cadastrarCpfUser(CpfUser cpfUser);
//        String cadastrarCpfUser(CpfUser cpfUser, Endereco address);

        // Método para cadastrar uma empresa (CNPJ)
//        String cadastrarCnpjUser(CnpjUser cnpjUser, Endereco address);
        String cadastrarCnpjUser(CnpjUser cnpjUser);

        String validarCPF(String cpfUser);

        String validarCNPJ(String cnpjUser);
    }
