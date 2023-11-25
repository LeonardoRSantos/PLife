package com.example.plife.service.util;

import java.util.HashMap;
import java.util.Map;

public class PontuacaoTarefa {
    private static final Map<String, Integer> PONTUACAO_MAP;

    static {
        PONTUACAO_MAP = new HashMap<>();
        PONTUACAO_MAP.put("Reciclar Latas", 10);
        PONTUACAO_MAP.put("Economizar Água", 15);
        PONTUACAO_MAP.put("Plantar uma árvore", 20);
        // Adicione mais tipos de tarefas conforme necessário
    }

    public static int getPontuacaoImediata(String tipoTarefa) {
        return PONTUACAO_MAP.getOrDefault(tipoTarefa, 0);  // Retorna 0 se o tipo de tarefa não estiver mapeado
    }
}
