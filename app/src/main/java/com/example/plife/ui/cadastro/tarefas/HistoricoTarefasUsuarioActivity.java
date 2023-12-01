package com.example.plife.ui.cadastro.tarefas;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.controller.TarefaController;
import com.example.plife.model.CpfUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.service.util.SessionManager;
import com.example.plife.ui.adapter.TarefaUsuarioAdapter;

import java.util.List;

public class HistoricoTarefasUsuarioActivity extends AppCompatActivity implements TarefaUsuarioAdapter.OnButtonClickListener {

    private RecyclerView recyclerView;
    private TarefaUsuarioAdapter tarefaUsuarioAdapter;
    private TarefaController tarefaController;
    private SessionManager sessionManager;
    private CpfUser cpfUser;
    private List<TarefaSustentavel> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_tarefas_usuarios);

        tarefaController = new TarefaController(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerTarefasUsuarios);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cpfUser = obterCpfjUsuarioLogado();
        tarefas = tarefaController.getTarefasDisponiveisCpf(cpfUser);

        tarefaUsuarioAdapter = new TarefaUsuarioAdapter(tarefas, this);
        recyclerView.setAdapter(tarefaUsuarioAdapter);
    }

    private CpfUser obterCpfjUsuarioLogado() {
        sessionManager = SessionManager.getInstance();
        return sessionManager.getCpfUser();
    }

    @Override
    public void onButtonClick(int position) {
        aceitarTarefaSelecionada(position);
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public boolean isSelected(int position) {
        if (tarefaUsuarioAdapter != null) {
            Integer posicaoTarefaSelecionada = tarefaUsuarioAdapter.getPosicaoTarefaSelecionada();
            return posicaoTarefaSelecionada != null && position == posicaoTarefaSelecionada.intValue();
        }
        return false;
    }


    private void aceitarTarefaSelecionada(int position) {
        try {
            if (position != RecyclerView.NO_POSITION) {
                TarefaSustentavel tarefaSelecionada = tarefas.get(position);
                String mensagem = tarefaController.aceitarTarefa(cpfUser.getCPF(), tarefaSelecionada.getId());
                tarefas.remove(position);
                tarefaUsuarioAdapter.notifyItemRemoved(position);
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selecione uma tarefa para aceitar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao aceitar tarefa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

