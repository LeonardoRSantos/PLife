package com.example.plife.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plife.R;
import com.example.plife.ui.cadastro.tarefas.CadastroTarefasActivity;
import com.example.plife.ui.cadastro.tarefas.HistoricoTarefasUsuarioActivity;
import com.example.plife.ui.cadastro.tarefas.TarefasUsuariosAceitasActivity;

public class MenuUsuarioActivity extends AppCompatActivity {

    Button btnTarefasUsuario, btnTarefasAceitasUsuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        btnTarefasUsuario = findViewById(R.id.btnTarefasUsuario);

        btnTarefasUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuarioActivity.this, HistoricoTarefasUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnTarefasAceitasUsuario = findViewById(R.id.btnTarefasAceitasUsuario);
        btnTarefasAceitasUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuarioActivity.this, TarefasUsuariosAceitasActivity.class);
                startActivity(intent);
            }
        });
    }
}
