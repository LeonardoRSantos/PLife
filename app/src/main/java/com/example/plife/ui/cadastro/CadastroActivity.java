package com.example.plife.ui.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plife.MainActivity;
import com.example.plife.R;
import com.example.plife.ui.cadastro.util.MaskUtil;

public class CadastroActivity extends AppCompatActivity {

    private EditText cpfCnpjEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cpfCnpjEditText = findViewById(R.id.cpfCnpj);
        cpfCnpjEditText.addTextChangedListener(MaskUtil.insert(cpfCnpjEditText));

        Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade de cadastro ao clicar no botão Registrar
                Intent intent = new Intent(CadastroActivity.this, CadastroUserActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chame o método onBackPressed() para fechar a atividade atual
                onBackPressed();
            }
        });


    }
}
