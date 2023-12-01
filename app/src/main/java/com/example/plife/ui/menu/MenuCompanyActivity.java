package com.example.plife.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plife.R;
import com.example.plife.ui.cadastro.tarefas.CadastroTarefasActivity;
import com.example.plife.ui.cadastro.tarefas.HistoricoTarefasActivity;

public class MenuCompanyActivity extends AppCompatActivity {

    Button btnCadastrarTarefasCompany, btnHistoricoTarefas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_company);

        btnCadastrarTarefasCompany = findViewById(R.id.btnCadastrarTarefasCompany);
        btnHistoricoTarefas = findViewById(R.id.btnHistoricoTarefasCompany);

        btnCadastrarTarefasCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCompanyActivity.this, CadastroTarefasActivity.class);
                startActivity(intent);
            }
        });

        btnHistoricoTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCompanyActivity.this, HistoricoTarefasActivity.class);
                startActivity(intent);
            }
        });
    }


}