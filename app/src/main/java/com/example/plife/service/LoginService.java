package com.example.plife.service;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;

public interface LoginService {
    String fazerLoginCpf(CpfUser cpfUser);

    String fazerLoginCnpj(CnpjUser cnpjUser);


}
