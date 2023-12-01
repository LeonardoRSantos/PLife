package com.example.plife.controller;

import android.content.Context;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.service.TarefaService;
import com.example.plife.service.impl.TarefaServiceImpl;

import java.io.InputStream;
import java.util.List;

public class TarefaController {

    private TarefaService tarefaService;

    public TarefaController(Context context) {
        // Certifique-se de que o contexto seja passado corretamente
        this.tarefaService = TarefaServiceImpl.getInstance(context);
    }

    public String cadastrarTarefaCnpj(CnpjUser cnpjUser, TarefaSustentavel tarefaSustentavel, InputStream imagemStream) {
        return tarefaService.cadastrarTarefaCnpj(cnpjUser, tarefaSustentavel, imagemStream);
    }

    public String validarTarefa(String adminUsername, Long tarefaId) {
        return tarefaService.validarTarefa(adminUsername, tarefaId);
    }

    public List<TarefaSustentavel> getTarefasDisponiveisCpf(CpfUser cpfUser) {
        return tarefaService.getTarefasDisponiveisCpf(cpfUser);
    }

    public List<TarefaSustentavel> getTarefasAceitas(CpfUser cpfUser) {
        return tarefaService.getTarefasAceitasCpf(cpfUser);
    }

    public String aceitarTarefa(String userCpf, Long tarefaId) {
        return tarefaService.aceitarTarefa(userCpf, tarefaId);
    }

    public String concluirTarefa(String userCpf, Long tarefaId, InputStream imagemStream) {
        return tarefaService.concluirTarefa(userCpf, tarefaId, imagemStream);
    }

    public List<TarefaSustentavel> getTarefasPorCnpj(CnpjUser cnpjUser) {
        return tarefaService.getTarefasPorCnpj(cnpjUser);
    }

}