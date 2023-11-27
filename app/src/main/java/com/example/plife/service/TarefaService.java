package com.example.plife.service;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.TarefaSustentavel;

import java.io.InputStream;
import java.util.List;

public interface TarefaService {
//    void cadastrarTarefaCnpj(String cnpj, String tipo, String objetivo, int pontos, String descricao, String produto);
      String cadastrarTarefaCnpj(CnpjUser cnpjUser, TarefaSustentavel tarefaSustentavel, InputStream imagemStream);

      List<TarefaSustentavel> getTarefasDisponiveisCpf();

      String validarTarefa(String adminUsername, Long tarefaId);

      String aceitarTarefa(String userCpf, Long tarefaId);
}