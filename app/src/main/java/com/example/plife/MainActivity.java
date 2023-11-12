package com.example.plife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.os.Bundle;

import com.example.plife.controller.TestController;
import com.example.plife.service.RegistrationService;
import com.example.plife.service.impl.RegistrationServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testarCadastro();
    }

    // Método de teste
    private void testarCadastro() {
        // Crie uma instância do seu controlador de teste
        UserServiceImpl userService = new UserServiceImpl();  // Crie uma instância apropriada do UserService
        RegistrationService registrationService = new RegistrationServiceImpl(userService);

        // Crie uma instância do seu controlador de teste
        TestController testController = new TestController(registrationService);

        // Teste o cadastro de CPF
//        testController.testarCadastroCPF();

        // Teste o cadastro de CNPJ
        testController.testarCadastroCNPJ();
    }


}