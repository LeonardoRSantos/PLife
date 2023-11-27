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
        PONTUACAO_MAP.put("Instalar lixeiras", 10);
        PONTUACAO_MAP.put("Construir uma horta comunitária", 20);
        PONTUACAO_MAP.put("Promover o uso de bicicletas", 20);
        PONTUACAO_MAP.put("Limpeza de rios ou lagos", 30);
        PONTUACAO_MAP.put("Práticas sustentáveis em empresas", 15);
        // Adicione mais tipos de tarefas conforme necessário
    }

    public static int getPontuacaoImediata(String tipoTarefa) {
        return PONTUACAO_MAP.getOrDefault(tipoTarefa, 0);  // Retorna 0 se o tipo de tarefa não estiver mapeado
    }
}
