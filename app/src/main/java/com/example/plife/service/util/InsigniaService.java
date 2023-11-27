package com.example.plife.service.util;


import java.util.HashMap;
import java.util.Map;


public class InsigniaService {
    private static final Map<String, String> INSIGNIA_MAP;

    static {
        INSIGNIA_MAP = new HashMap<>();
        INSIGNIA_MAP.put("Conquistador Eco", "Insignia_Eco");
        INSIGNIA_MAP.put("Campeão Verde", "Insignia_Verde");
        INSIGNIA_MAP.put("Visionário", "Insignia_Visionario");
        INSIGNIA_MAP.put("Herói Ecológico", "Insignia_Heroi");
        INSIGNIA_MAP.put("Defensor Meio Ambiente", "Insignia_Defensor");
        INSIGNIA_MAP.put("Embaixador Eco", "Insignia_Embaixador");
        INSIGNIA_MAP.put("Líder Sustentável", "Insignia_Lider");
        INSIGNIA_MAP.put("Guardião Sustentável", "Insignia_Guardiao");
        // Adicione mais tipos de insígnias conforme necessário
    }

    public static String getInsignia(int pontos) {
        String insignia = "Novato";  // Padrão: Novato

        if (pontos >= 50) {
            insignia = "Conquistador Eco";
        } else if (pontos >= 75) {
            insignia = "Campeão Verde";
        }else if (pontos >= 100) {
            insignia = "Visionário";
        }else if (pontos >= 125) {
            insignia = "Herói Ecológico";
        }else if (pontos >= 150) {
            insignia = "Defensor Meio Ambiente";
        }else if (pontos >= 175) {
            insignia = "Embaixador Eco";
        }else if (pontos >= 200) {
            insignia = "Líder Sustentável";
        }else if (pontos >= 225) {
            insignia = "Guardião Sustentável";
        }

        return INSIGNIA_MAP.get(insignia);
    }
}

