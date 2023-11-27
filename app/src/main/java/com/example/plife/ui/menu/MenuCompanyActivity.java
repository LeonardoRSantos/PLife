package com.example.plife.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plife.R;
import com.example.plife.ui.cadastro.CadastroCompanyActivity;
import com.example.plife.ui.cadastro.tarefas.CadastroTarefasActivity;

public class MenuCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_company);

        Button btnCadastrarTarefasCompany = findViewById(R.id.btnCadastrarTarefasCompany);

        btnCadastrarTarefasCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCompanyActivity.this, CadastroTarefasActivity.class);
                startActivity(intent);
            }
        });
    }


}