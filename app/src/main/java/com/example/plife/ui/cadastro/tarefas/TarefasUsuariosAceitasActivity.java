package com.example.plife.ui.cadastro.tarefas;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.controller.TarefaController;
import com.example.plife.model.CpfUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.service.util.SessionManager;
import com.example.plife.ui.adapter.TarefaUsuarioAceitaAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TarefasUsuariosAceitasActivity extends AppCompatActivity implements TarefaUsuarioAceitaAdapter.OnButtonClickListener, TarefaUsuarioAceitaAdapter.OnImageClickListener {

    private RecyclerView recyclerView;
    private TarefaUsuarioAceitaAdapter tarefaUsuarioAceitaAdapter;
    private TarefaController tarefaController;
    private SessionManager sessionManager;

    private CpfUser cpfUser;
    private List<TarefaSustentavel> tarefas;
    private static final int PICK_IMAGE_REQUEST = 200;
    private Uri imagemUri; // Adicione esta variável para armazenar a Uri da imagem selecionada



    private Integer posicaoTarefaAceitaSelecionada = RecyclerView.NO_POSITION;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas_aceitas_usuarios);

        // Inicializa o controlador de tarefas
        tarefaController = new TarefaController(getApplicationContext());

        // Inicializa o RecyclerView
        recyclerView = findViewById(R.id.recyclerTarefasAceitasUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtém a lista de tarefas para o CNPJ logado
        cpfUser = obterCpfjUsuarioLogado(); // Obtém o CNPJ do usuário logado
        tarefas = tarefaController.getTarefasAceitas(cpfUser);

        // Inicializa o adaptador e define no RecyclerView
        tarefaUsuarioAceitaAdapter = new TarefaUsuarioAceitaAdapter(tarefas, this, this);
        recyclerView.setAdapter(tarefaUsuarioAceitaAdapter);
    }

    // Obtém o CNPJ do usuário logado usando a classe de sessão
    private CpfUser obterCpfjUsuarioLogado() {
        sessionManager = SessionManager.getInstance();
        return sessionManager.getCpfUser();
    }

    @Override
    public void onButtonClick(int position) {

        posicaoTarefaAceitaSelecionada = position;

        concluirTarefaSelecionada();
    }

    @Override
    public void onItemClick(int position) {
        // Implemente a lógica para lidar com o clique em um item da lista, se necessário
    }

    @Override
    public boolean isSelected(int position) {
        if (tarefaUsuarioAceitaAdapter != null) {
            Integer posicaoTarefaSelecionada = tarefaUsuarioAceitaAdapter.getPosicaoTarefaSelecionada();
            return posicaoTarefaSelecionada != null && position == posicaoTarefaSelecionada.intValue();
        }
        return false;
    }

    private void concluirTarefaSelecionada() {
        try {
            if (posicaoTarefaAceitaSelecionada != RecyclerView.NO_POSITION) {
                TarefaSustentavel tarefaSelecionada = tarefas.get(posicaoTarefaAceitaSelecionada);

                // Converta a Uri em InputStream
                InputStream imagemStream = getInputStreamFromUri(imagemUri, this);

                // Agora você pode usar o InputStream conforme necessário
                String mensagem = tarefaController.concluirTarefa(cpfUser.getCPF(), tarefaSelecionada.getId(), imagemStream);
                tarefas.remove(posicaoTarefaAceitaSelecionada);
                tarefaUsuarioAceitaAdapter.notifyItemRemoved(posicaoTarefaAceitaSelecionada);
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selecione uma tarefa para concluir", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao concluir a tarefa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private InputStream getInputStreamFromUri(Uri uri, Context context) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.openInputStream(uri);
    }

    @Override
    public void onImageClick(int position) {
        // Salve a posição da tarefa selecionada
        posicaoTarefaAceitaSelecionada = position;

        // Implemente a lógica para escolher ou capturar uma imagem aqui
        // Suponha que você está usando uma Intent para a galeria de imagens
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Salve a Uri da imagem selecionada
            imagemUri = data.getData();

            exibirImagemEscolhida();

            // Chame o método para concluir a tarefa
//            concluirTarefaSelecionada();
        }
    }

    private void exibirImagemEscolhida() {
        ImageView imageView = findViewById(R.id.btnImgTarefaConcluida);
        imageView.setImageURI(imagemUri);
    }









}


