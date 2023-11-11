package com.example.plife.service.impl;

import com.example.plife.model.CpfUser;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;

import java.util.Arrays;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public String cadastrarCpfUser(CpfUser cpfUser) {
        String validationResult = validarCPF(cpfUser.getCPF());
        if ("CPF válido".equals(validationResult)) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            User user = new User(cpfUser.getCPF(), cpfUser.getPassword(), Role.ROLE_USER);
            return userServiceImpl.cadastrarUsuario(user);
        } else {
            System.out.println("CPF inválido: " + validationResult);
            return "CPF inválido: " + validationResult;
        }
    }

    @Override
    public String cadastrarCnpjUser(CnpjUser cnpjUser) {
        String validationResult = validarCNPJ(cnpjUser.getCNPJ());
        if ("CNPJ válido".equals(validationResult)) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            User user = new User(cnpjUser.getCNPJ(), cnpjUser.getPassword(), Role.ROLE_COMPANY);
            return userServiceImpl.cadastrarUsuario(user);
        } else {
            System.out.println("CNPJ inválido: " + validationResult);
            return "CNPJ inválido: " + validationResult;
        }
    }

    @Override
    public String validarCPF(String cpf) {
        try {
            cpf = cpf.replace(".", "").replace("-", "").trim();

            if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf)) {
                return "Verifique o formato e os dígitos.";
            }

            Long.parseLong(cpf);

            if (!calcularDigitosVerificadores(cpf.substring(0, 9)).equals(cpf.substring(9, 11))) {
                return "Dígitos verificadores não correspondem.";
            }

            return "CPF válido";
        } catch (NumberFormatException e) {
            return "Deve conter apenas números.";
        }
    }

    @Override
    public String validarCNPJ(String cnpj) {
        try {
            cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "").trim();

            if (cnpj == null || cnpj.length() != 14 || isCNPJPadrao(cnpj)) {
                return "Verifique o formato e os dígitos.";
            }

            Long.parseLong(cnpj);

            if (!calcularDigitosVerificadoresCNPJ(cnpj.substring(0, 12)).equals(cnpj.substring(12, 14))) {
                return "Dígitos verificadores não correspondem.";
            }

            return "CNPJ válido";
        } catch (NumberFormatException e) {
            return "Deve conter apenas números.";
        }
    }

    // Métodos auxiliares

    private String calcularDigitosVerificadores(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        primDig = (soma % 11 == 0 || soma % 11 == 1) ? 0 : 11 - (soma % 11);

        soma = 0;
        peso = 11;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig * 2;

        segDig = (soma % 11 == 0 || soma % 11 == 1) ? 0 : 11 - (soma % 11);

        return primDig.toString() + segDig.toString();
    }

    private boolean isCPFPadrao(String cpf) {
        List<String> cpfsPadrao = Arrays.asList("000.000.000-00", "111.111.111-11", "222.222.222-22");
        String cpfSemPontos = cpf.replace(".", "").replace("-", "").trim();
        return cpfsPadrao.contains(cpfSemPontos);
    }

    private String calcularDigitosVerificadoresCNPJ(String num) {
        int[] multiplicadoresPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadoresSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;

        for (int i = 0; i < 12; i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * multiplicadoresPrimeiroDigito[i];
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        num += primeiroDigito;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * multiplicadoresSegundoDigito[i];
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        return primeiroDigito + "" + segundoDigito;
    }

    private boolean isCNPJPadrao(String cnpj) {
        List<String> cnpjsPadrao = Arrays.asList("00.000.000/0000-00", "11.111.111/1111-11", "22.222.222/2222-22");
        String cnpjSemPontos = cnpj.replace(".", "").replace("-", "").replace("/", "").trim();
        return cnpjsPadrao.contains(cnpjSemPontos);
    }
}