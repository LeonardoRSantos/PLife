package com.example.plife.controller;

import android.content.Context;

import com.example.plife.model.CnpjUser;
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

    public List<TarefaSustentavel> getTarefasDisponiveisCpf() {
        return tarefaService.getTarefasDisponiveisCpf();
    }

    public String aceitarTarefa(String userCpf, Long tarefaId) {
        return tarefaService.aceitarTarefa(userCpf, tarefaId);
    }
}