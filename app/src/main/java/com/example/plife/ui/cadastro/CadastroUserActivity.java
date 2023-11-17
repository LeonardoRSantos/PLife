package com.example.plife.ui.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plife.MainActivity;
import com.example.plife.R;
import com.example.plife.controller.CadastroController;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;
import com.example.plife.ui.cadastro.util.MaskUtil;

public class CadastroUserActivity extends AppCompatActivity {

    private RegistrationService registrationService;

    private UserServiceImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuacao_cadastro);

        userService = UserServiceImpl.getInstance();
        registrationService = new RegistrationServiceImpl(userService);

        String cpf = getIntent().getStringExtra("cpf");

        // Configurar EditText com a máscara de CPF e torná-lo não editável
        EditText cpfEditText = findViewById(R.id.cpfCnpjCadastro);

        // Adicionar o texto "CPF:" ao número formatado
        String textoFormatado = "CPF:" + MaskUtil.unmask(cpf); // Remover a máscara para evitar interferências
        cpfEditText.setText(textoFormatado);
        cpfEditText.setEnabled(true);

        // Configurar o botão de cadastro
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar os dados do usuário dos campos de entrada
                String cpf = MaskUtil.unmask(((EditText) findViewById(R.id.cpfCnpjCadastro)).getText().toString());
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String nome = ((EditText) findViewById(R.id.nome)).getText().toString();
                String telefone = ((EditText) findViewById(R.id.phone)).getText().toString();
                String senha = ((EditText) findViewById(R.id.password)).getText().toString();

                // Criar um objeto CpfUser com os dados do usuário
                CpfUser user = new CpfUser(nome, telefone, email, senha, Role.ROLE_USER, cpf);


                // Chamar o método cadastrarUsuario do CadastroController
                CadastroController cadastroController = new CadastroController(registrationService);
                String resultadoCadastro = cadastroController.cadastrarUsuario(user, null);

                if ("Cadastro bem-sucedido".equals(resultadoCadastro)) {
                    // A validação foi bem-sucedida, continue para a próxima tela
                    Intent intent = new Intent(CadastroUserActivity.this, MainActivity.class);
                    Toast.makeText(CadastroUserActivity.this, resultadoCadastro, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }  else {
                    Toast.makeText(CadastroUserActivity.this, resultadoCadastro, Toast.LENGTH_LONG).show();
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
