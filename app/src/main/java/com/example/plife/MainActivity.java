package com.example.plife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plife.controller.LoginController;
import com.example.plife.controller.UserAdminController;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.CpfUser;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.LoginServiceImpl;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;
import com.example.plife.ui.cadastro.CadastroActivity;
import com.example.plife.ui.cadastro.util.MaskUtil;
import com.example.plife.ui.menu.MenuCompanyActivity;
import com.example.plife.ui.menu.MenuUsuarioActivity;

public class MainActivity extends AppCompatActivity {

    LoginService loginService;

    private UserServiceImpl userService;

    private RegistrationService registrationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserAdminController userAdminController = new UserAdminController(userService);
        userAdminController.cadastrarUsuarioAdmin("leonardo", "123456");

        userService = UserServiceImpl.getInstance(getApplicationContext());
        registrationService = new RegistrationServiceImpl(userService);
        loginService = new LoginServiceImpl(registrationService,userService);

        EditText cpfCnpjEditText = findViewById(R.id.cpfCnpjLogin);
        cpfCnpjEditText.addTextChangedListener(MaskUtil.insert(cpfCnpjEditText));


        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpfCnpj = ((EditText) findViewById(R.id.cpfCnpjLogin)).getText().toString();
                String senha = ((EditText) findViewById(R.id.passwordLogin)).getText().toString();

                if(cpfCnpj.length() == 14){
                    CpfUser cpfUser = new CpfUser(cpfCnpj, senha, Role.ROLE_USER);

                    LoginController loginController = new LoginController(loginService);
                    String resultadoLogin = loginController.LoginUsuario(cpfUser, null);

                    // Trate o resultado do login conforme necessário
                    if ("Login bem-sucedido".equals(resultadoLogin)) {
                        // Login bem-sucedido, vá para a próxima tela
                        Intent intent = new Intent(MainActivity.this, MenuUsuarioActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, resultadoLogin, Toast.LENGTH_LONG).show();
                    }
                }else if(cpfCnpj.length() == 18){
                    CnpjUser cnpjUser = new CnpjUser(cpfCnpj, senha, Role.ROLE_COMPANY);

                    LoginController loginController = new LoginController(loginService);
                    String resultadoLogin = loginController.LoginUsuario(null, cnpjUser);

                    // Trate o resultado do login conforme necessário
                    if ("Login bem-sucedido".equals(resultadoLogin)) {
                        // Login bem-sucedido, vá para a próxima tela
                        Intent intent = new Intent(MainActivity.this, MenuCompanyActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, resultadoLogin, Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
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