package com.example.plife.ui.cadastro.tarefas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.controller.TarefaController;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.ui.adapter.TarefaAdapter;
import com.example.plife.service.util.SessionManager;

import java.util.List;

public class HistoricoTarefasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaController tarefaController;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_tarefas);

        // Inicializa o controlador de tarefas
        tarefaController = new TarefaController(getApplicationContext());

        // Inicializa o RecyclerView
        recyclerView = findViewById(R.id.recyclerTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtém a lista de tarefas para o CNPJ logado
        CnpjUser cnpjUser = obterCnpjUsuarioLogado(); // Obtém o CNPJ do usuário logado
        List<TarefaSustentavel> tarefas = tarefaController.getTarefasPorCnpj(cnpjUser);

        // Inicializa o adaptador e define no RecyclerView
        tarefaAdapter = new TarefaAdapter(tarefas);
        recyclerView.setAdapter(tarefaAdapter);
    }

    // Obtém o CNPJ do usuário logado usando a classe de sessão
    private CnpjUser obterCnpjUsuarioLogado() {
        // Obtemos a instância da classe de sessão
        sessionManager = SessionManager.getInstance();

        // Retorna o CNPJ do usuário logado
        return sessionManager.getCnpjUser();
    }
}
