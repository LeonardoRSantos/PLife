package com.example.plife.service.impl;

import com.example.plife.model.CpfUser;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;

import java.util.Arrays;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {

    private UserService userService;

    public RegistrationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String   cadastrarUsuario(Object user) {
        if (user instanceof CpfUser) {
            CpfUser cpfUser = (CpfUser) user;
            String validationResult = validarDocumento(cpfUser.getCPF());
            if ("CPF válido".equals(validationResult)) {
//                return userService.cadastrarUsuario(new User(cpfUser.getCPF(), cpfUser.getPassword(), cpfUser.getRole()));
                return userService.cadastrarUsuario(cpfUser);
            } else {
                System.out.println("CPF inválido: " + validationResult);
                return "CPF inválido: " + validationResult;
            }
        } else if (user instanceof CnpjUser) {
            CnpjUser cnpjUser = (CnpjUser) user;
            String validationResult = validarDocumento(cnpjUser.getCNPJ());
            if ("CNPJ válido".equals(validationResult)) {
//                return userService.cadastrarUsuario(new User(cnpjUser.getCNPJ(), cnpjUser.getPassword(), cnpjUser.getRole()));
                return userService.cadastrarUsuario(cnpjUser);
            } else {
                System.out.println("CNPJ inválido: " + validationResult);
                return "CNPJ inválido: " + validationResult;
            }
        } else {
            return "Tipo de usuário não suportado";
        }
    }

    @Override
    public String validarDocumento(String documento) {
        documento = documento.replace(".", "").replace("-", "").replace("/", "").trim();

        if (documento == null || (documento.length() != 11 && documento.length() != 14) || isDocumentoPadrao(documento)) {
            return "Verifique o formato e os dígitos.";
        }

        try {
            Long.parseLong(documento);

            if (documento.length() == 11) {
                // É um CPF
                if (!calcularDigitosVerificadores(documento.substring(0, 9)).equals(documento.substring(9, 11))) {
                    return "CPF inválido, informe um CPF válido.";
                }
                return "CPF válido";
            } else if (documento.length() == 14) {
                // É um CNPJ
                if (!calcularDigitosVerificadoresCNPJ(documento.substring(0, 12)).equals(documento.substring(12, 14))) {
                    return "CNPJ inválido, informe um CNPJ válido.";
                }
                return "CNPJ válido";
            } else {
                return "Documento inválido";
            }
        } catch (NumberFormatException e) {
            return "Deve conter apenas números.";
        }
    }

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

    private boolean isDocumentoPadrao(String documento) {
        List<String> documentosPadrao = Arrays.asList("000.000.000-00", "11.111.111-11", "22.222.222-22", "00.000.000/0000-00", "11.111.111/1111-11", "22.222.222/2222-22");
        String documentoSemPontos = documento.replace(".", "").replace("-", "").replace("/", "").trim();
        return documentosPadrao.contains(documentoSemPontos);
    }


}