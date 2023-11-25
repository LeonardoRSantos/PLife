package com.example.plife.service.util;
import java.util.HashMap;
import java.util.Map;

public class InsigniaCompanyService {
    private static final Map<String, String> INSIGNIA_COMPANY_MAP;

    static {
        INSIGNIA_COMPANY_MAP = new HashMap<>();
        INSIGNIA_COMPANY_MAP.put("Eco-Consciente", "Insignia_Eco_Consciente");
        INSIGNIA_COMPANY_MAP.put("Verde Visionário", "Insignia_Visionario");
        INSIGNIA_COMPANY_MAP.put("Sustentável Proativo", "Insignia_Proativo");
        INSIGNIA_COMPANY_MAP.put("Ecológico Exemplar", "Insignia_Exemplar");
        INSIGNIA_COMPANY_MAP.put("Amigo do Meio Ambiente", "Insignia_Amigo");
        INSIGNIA_COMPANY_MAP.put("Sustentabilidade Excepcional", "Insignia_Excepcional");
        INSIGNIA_COMPANY_MAP.put("Eco-Inovador Líder", "Insignia_Eco_Lider");
        INSIGNIA_COMPANY_MAP.put("Sustentabilidade Global", "Insignia_Global");
    }

    public static String getInsigniaCompany(int pontos) {
        String insignia = "Novato";  // Padrão: Novato

        if (pontos >= 50) {
            insignia = "Eco-Consciente";
        } else if (pontos >= 75) {
            insignia = "Verde Visionário";
        }else if (pontos >= 100) {
            insignia = "Sustentável Proativo";
        }else if (pontos >= 125) {
            insignia = "Ecológico Exemplar";
        }else if (pontos >= 150) {
            insignia = "Amigo do Meio Ambiente";
        }else if (pontos >= 175) {
            insignia = "Sustentabilidade Excepcional";
        }else if (pontos >= 200) {
            insignia = "Eco-Inovador Líder";
        }else if (pontos >= 225) {
            insignia = "Sustentabilidade Global";
        }

        return INSIGNIA_COMPANY_MAP.get(insignia);
    }


}

