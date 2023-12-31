package com.example.plife.service;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.TarefaSustentavel;

import java.io.InputStream;
import java.util.List;

public interface TarefaService {
//    void cadastrarTarefaCnpj(String cnpj, String tipo, String objetivo, int pontos, String descricao, String produto);
      String cadastrarTarefaCnpj(CnpjUser cnpjUser, TarefaSustentavel tarefaSustentavel, InputStream imagemStream);

      List<TarefaSustentavel> getTarefasDisponiveisCpf(CpfUser cpfUser);
      List<TarefaSustentavel> getTarefasAceitasCpf(CpfUser cpfUser);

      List<TarefaSustentavel> getTarefasPorCnpj(CnpjUser cnpjUser);

      String validarTarefa(String adminUsername, Long tarefaId);

      String aceitarTarefa(String userCpf, Long tarefaId);
      String concluirTarefa(String userCpf, Long tarefaId, InputStream imagemStream);
}