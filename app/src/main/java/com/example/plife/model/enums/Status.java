package com.example.plife.model.enums;

public enum Status {

    DISPONIVEL("DISPONÍVEL"),
    ACEITA("ACEITA"),
    CONCLUIDA("CONCLUÍDA"),
    VALIDADA("VALIDADA");



    private String displayName;

    Status(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
