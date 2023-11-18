package com.example.plife.ui.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.plife.MainActivity;
import com.example.plife.R;
import com.example.plife.controller.CadastroController;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;
import com.example.plife.ui.cadastro.util.MaskUtil;

public class CadastroCompanyActivity extends AppCompatActivity {

    private RegistrationService registrationService;

    private UserServiceImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_company);

        userService = UserServiceImpl.getInstance();
        registrationService = new RegistrationServiceImpl(userService);

        String cnpj = getIntent().getStringExtra("cnpj");

        // Configurar EditText com a máscara de CPF e torná-lo não editável
        EditText cnpjEditText = findViewById(R.id.cpfCnpjCompany);

        // Adicionar o texto "CPF:" ao número formatado
        String textoFormatado = "CNPJ:" + MaskUtil.unmask(cnpj); // Remover a máscara para evitar interferências
        cnpjEditText.setText(textoFormatado);
        cnpjEditText.setEnabled(true);

        // Configurar o botão de cadastro
        Button btnCadastrar = findViewById(R.id.btnCadastrarCompany);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar os dados do usuário dos campos de entrada
                String cnpj = MaskUtil.unmask(((EditText) findViewById(R.id.cpfCnpjCompany)).getText().toString());
                String emailCompany = MaskUtil.unmask(((EditText) findViewById(R.id.emailCompany)).getText().toString());
                String razaoSocial = ((EditText) findViewById(R.id.razaoSocial)).getText().toString();
                String nomeFantasia = ((EditText) findViewById(R.id.nomeFantasia)).getText().toString();
                String telefone = ((EditText) findViewById(R.id.phone)).getText().toString();
                String senha = ((EditText) findViewById(R.id.password)).getText().toString();

                // Criar um objeto CpfUser com os dados do usuário
                CnpjUser userCnpj = new CnpjUser(nomeFantasia, telefone, emailCompany, senha, Role.ROLE_COMPANY, razaoSocial, cnpj);


                // Chamar o método cadastrarUsuario do CadastroController
                CadastroController cadastroController = new CadastroController(registrationService);
                String resultadoCadastro = cadastroController.cadastrarUsuario(null, userCnpj);

                if ("Cadastro bem-sucedido".equals(resultadoCadastro)) {
                    // A validação foi bem-sucedida, continue para a próxima tela
                    Intent intent = new Intent(CadastroCompanyActivity.this, MainActivity.class);
                    Toast.makeText(CadastroCompanyActivity.this, resultadoCadastro, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }  else {
                    Toast.makeText(CadastroCompanyActivity.this, resultadoCadastro, Toast.LENGTH_LONG).show();
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