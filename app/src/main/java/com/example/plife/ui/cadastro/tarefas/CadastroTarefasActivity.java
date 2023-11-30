package com.example.plife.ui.cadastro.tarefas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plife.MainActivity;
import com.example.plife.R;
import com.example.plife.controller.TarefaController;
import com.example.plife.model.CnpjUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.service.impl.TarefaServiceImpl;
import com.example.plife.service.impl.UserServiceImpl;
import com.example.plife.service.util.PontuacaoTarefa;
import com.example.plife.service.util.SessionManager;
import com.example.plife.ui.cadastro.CadastroCompanyActivity;
import com.example.plife.ui.menu.MenuCompanyActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CadastroTarefasActivity extends AppCompatActivity {

    private UserServiceImpl userService;
    private TarefaController tarefaController;

    private static final int SELECAO_GALERIA = 200;

    private ImageView btnAdicionarFoto;

    private Uri uriImagem;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefas);

        userService = UserServiceImpl.getInstance(getApplicationContext());
        tarefaController = new TarefaController(this);

        Spinner spinnerTarefas = findViewById(R.id.spinnerTarefas);
        TextView textViewPontosTarefa = findViewById(R.id.pontosTarefa);
        btnAdicionarFoto = findViewById(R.id.btnImagemTaskProduto);
        Button btnCadastrarTarefa = findViewById(R.id.btnCadastrarTarefaCompany);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Tarefas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTarefas.setAdapter(adapter);

        spinnerTarefas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Atualizar a pontuação com base na tarefa selecionada
                String tarefaSelecionada = parentView.getItemAtPosition(position).toString();
                int pontos = obterPontosDaTarefa(tarefaSelecionada);
                textViewPontosTarefa.setText(String.valueOf(pontos)); // Convertendo int para String
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Implemente se necessário
            }
        });

        btnAdicionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent,SELECAO_GALERIA);
                }

            }
        });

        btnCadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chame o método para cadastrar a tarefa ao clicar no botão
                cadastrarTarefa();
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

    private int obterPontosDaTarefa(String tarefa) {
        return PontuacaoTarefa.getPontuacaoImediata(tarefa);
    }

//    private void selecionarImagemDaGaleria() {
//        // Crie um Intent para escolher uma imagem da galeria
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, REQUEST_CODE_GALERIA);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECAO_GALERIA && resultCode == RESULT_OK && data != null) {
            // A imagem foi escolhida com sucesso, obtenha o URI da imagem
            uriImagem = data.getData();  // Remova a palavra-chave Uri da frente aqui

            // Faça algo com o URI da imagem, como exibição ou armazenamento
            exibirImagemEscolhida();


        }
    }


    private void exibirImagemEscolhida() {
        ImageView imageView = findViewById(R.id.btnImagemTaskProduto);
        imageView.setImageURI(uriImagem);
    }


    private void cadastrarTarefa() {
        Spinner spinnerTarefas = findViewById(R.id.spinnerTarefas);
        EditText editTextDescricao = findViewById(R.id.descricao);
        EditText editTextPontos = findViewById(R.id.pontosTarefa);
        EditText editTextObjetivo = findViewById(R.id.objetivoTarefas);



        try {
            // Obtenha o tipo da tarefa selecionada no Spinner
            String tipoTarefa = spinnerTarefas.getSelectedItem().toString();
            // Obtenha os pontos da tarefa do EditText
            int pontos = Integer.parseInt(editTextPontos.getText().toString());
            // Obtenha o objetivo da tarefa do EditText
            String objetivo = editTextObjetivo.getText().toString();
            // Obtenha a descrição da tarefa do EditText
            String descricao = editTextDescricao.getText().toString();
            // Obtenha o usuário logado
            CnpjUser usuarioCnpj = SessionManager.getInstance().getCnpjUser();
            // Certifique-se de ajustar os parâmetros conforme necessário para o seu caso
            TarefaSustentavel tarefaSustentavel = new TarefaSustentavel(usuarioCnpj, tipoTarefa, objetivo, pontos, descricao);
            // Defina outros atributos da tarefa conforme necessário
            // Abra um InputStream a partir do URI da imagem
            // (O InputStream da imagem deve ser obtido de alguma forma, dependendo de como o usuário seleciona a imagem)
            InputStream imagemStream = getContentResolver().openInputStream(uriImagem);; // Substitua null pelo código adequado

            // Chame o método cadastrarTarefaCnpj do tarefaController
            //Tarefa cadastrada com sucesso.
            String resultadoCadastro = tarefaController.cadastrarTarefaCnpj(usuarioCnpj, tarefaSustentavel, imagemStream);
            // Exiba o resultado (pode ser útil para o usuário)
            if ("Tarefa cadastrada com sucesso".equals(resultadoCadastro)) {
                // A validação foi bem-sucedida, continue para a próxima tela
                Intent intent = new Intent(CadastroTarefasActivity.this, MenuCompanyActivity.class);
                Toast.makeText(CadastroTarefasActivity.this, resultadoCadastro, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Trate a exceção de formato de número inválido aqui, se necessário
            Toast.makeText(CadastroTarefasActivity.this, "Erro: Formato de número inválido.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            // Trate outras exceções aqui, se necessário
            Toast.makeText(CadastroTarefasActivity.this, "Erro ao cadastrar a tarefa. Por favor, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }


}
