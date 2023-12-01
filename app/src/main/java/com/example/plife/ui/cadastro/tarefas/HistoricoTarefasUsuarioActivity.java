package com.example.plife.ui.cadastro.tarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class HistoricoTarefasUsuarioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaUsuarioAdapter tarefaUsuarioAdapter;
    private TarefaController tarefaController;
    private SessionManager sessionManager;

    private Button btnAceitarTarefa;

    private CpfUser cpfUser;

    private List<TarefaSustentavel> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_tarefas_usuarios);

        btnAceitarTarefa = findViewById(R.id.btnAceitarTarefaUsuario);
        // Inicializa o controlador de tarefas
        tarefaController = new TarefaController(getApplicationContext());
        // Inicializa o RecyclerView
        recyclerView = findViewById(R.id.recyclerTarefasUsuarios);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtém a lista de tarefas para o CNPJ logado
        cpfUser = obterCpfjUsuarioLogado(); // Obtém o CNPJ do usuário logado
        tarefas = tarefaController.getTarefasDisponiveisCpf(cpfUser);

        // Inicializa o adaptador e define no RecyclerView
        tarefaUsuarioAdapter = new TarefaUsuarioAdapter(tarefas);
        recyclerView.setAdapter(tarefaUsuarioAdapter);


//        btnAceitarTarefa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                aceitarTarefaSelecionada();
//            }
//        });
    }

    // Obtém o CNPJ do usuário logado usando a classe de sessão
    private CpfUser obterCpfjUsuarioLogado() {
        // Obtemos a instância da classe de sessão
        sessionManager = SessionManager.getInstance();

        // Retorna o CNPJ do usuário logado
        return sessionManager.getCpfUser();
    }

    // Método para lidar com o clique no botão "Aceitar Tarefa"
//    private void aceitarTarefaSelecionada() {
//        // Verifica se uma tarefa está selecionada
//        int posicaoSelecionada = tarefaUsuarioAdapter.getPosicaoTarefaSelecionada();
//
//        if (posicaoSelecionada != RecyclerView.NO_POSITION) {
//            // Obtém a tarefa selecionada
//            TarefaSustentavel tarefaSelecionada = tarefas.get(posicaoSelecionada);
//
//            // Obtém o CNPJ do usuário logado
////            CpfUser cpfUser = obterCpfjUsuarioLogado();
//
//            // Chama o método para aceitar a tarefa no controlador
//            String mensagem = tarefaController.aceitarTarefa(cpfUser.getCPF(), tarefaSelecionada.getId());
//
//            // Atualiza a lista de tarefas no adaptador
//            tarefas.remove(posicaoSelecionada);
//            tarefaUsuarioAdapter.notifyItemRemoved(posicaoSelecionada);
//
//            // Exibe uma mensagem de sucesso ou erro
//            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
//        } else {
//            // Se nenhuma tarefa estiver selecionada, exiba uma mensagem
//            Toast.makeText(this, "Selecione uma tarefa para aceitar", Toast.LENGTH_SHORT).show();
//        }
//    }
}
