package com.example.plife.ui.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.plife.R;
import com.example.plife.controller.CadastroController;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.ui.cadastro.util.MaskUtil;

public class CadastroActivity extends AppCompatActivity {

    private EditText cpfCnpjEditText;
    private CadastroController cadastroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        RegistrationService registrationService = new RegistrationServiceImpl();
        cadastroController = new CadastroController(registrationService);

        cpfCnpjEditText = findViewById(R.id.cpfCnpj);
        cpfCnpjEditText.addTextChangedListener(MaskUtil.insert(cpfCnpjEditText));

        Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenha o valor do CPF/CNPJ digitado
                String documento = cpfCnpjEditText.getText().toString().replaceAll("[^0-9]", "");

                Log.d("CadastroActivity", "Botão Continuar clicado");

                // Verifique se o documento é um CPF ou CNPJ chamando o método do CadastroController
                String validationResult = cadastroController.validarDocumento(documento); // Substitua ROLE_USER pelo papel correto

                if ("Documento válido".equals(validationResult) || "Documento válido".equals(validationResult)) {
                    // A validação foi bem-sucedida, continue para a próxima tela
                    Intent intent = new Intent(CadastroActivity.this, CadastroUserActivity.class);
                    intent.putExtra("cpf", documento);
                    startActivity(intent);
                }  else {
                    // A validação falhou, exiba a mensagem de erro
                    Toast.makeText(CadastroActivity.this, validationResult, Toast.LENGTH_LONG).show();
//                    Log.d("CadastroActivity", "Erro: " + validationResult);
                }
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


