package com.example.plife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plife.ui.cadastro.CadastroActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crie uma instância compartilhada do UserServiceImpl
//        userService = new UserServiceImpl();
//
//        // Crie instâncias dos outros serviços usando a instância compartilhada
//        registrationService = new RegistrationServiceImpl(userService);
//        loginService = new LoginServiceImpl(registrationService, userService);

        // Testar o cadastro
//        testarCadastro();
//
//        // Testar o login
//        testarLogin();

        // Adicione um ouvinte de clique ao botão Registrar
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade de cadastro ao clicar no botão Registrar
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }




}