package com.example.plife;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.plife.controller.TestController;
import com.example.plife.controller.TestLoginController;
import com.example.plife.model.enums.Role;
import com.example.plife.service.LoginService;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.UserService;
import com.example.plife.service.impl.LoginServiceImpl;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;

public class MainActivity extends AppCompatActivity {

    private UserServiceImpl userService;
    private RegistrationService registrationService;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crie uma instância compartilhada do UserServiceImpl
        userService = new UserServiceImpl();

        // Crie instâncias dos outros serviços usando a instância compartilhada
        registrationService = new RegistrationServiceImpl(userService);
        loginService = new LoginServiceImpl(registrationService, userService);

        // Testar o cadastro
        testarCadastro();

        // Testar o login
        testarLogin();
    }

    // Método de teste de cadastro
    private void testarCadastro() {
        // Use a instância compartilhada do UserServiceImpl criada na atividade principal
        TestController testController = new TestController(registrationService, userService);

        // Teste o cadastro de CPF e CNPJ
        testController.testarCadastroCPF();
        // testController.testarCadastroCNPJ(); // Descomente se necessário
    }

    // Método de teste de login
    private void testarLogin() {
        // Crie uma instância do seu TestLoginController
        TestLoginController testLoginController = new TestLoginController(loginService, registrationService, userService);

        // Teste o login com CPF, email, CNPJ, e email da empresa
        testLoginController.testarLogin();
    }
}
