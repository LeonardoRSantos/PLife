package com.example.plife.ui.cadastro.tarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plife.R;
import com.example.plife.service.impl.UserServiceImpl;
import com.example.plife.service.util.PontuacaoTarefa;

public class CadastroTarefasActivity extends AppCompatActivity {

    private UserServiceImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefas);

        Spinner spinnerTarefas = findViewById(R.id.spinnerTarefas);
        TextView textViewPontosTarefa = findViewById(R.id.pontosTarefa);

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

    }

    private int obterPontosDaTarefa(String tarefa) {
        return PontuacaoTarefa.getPontuacaoImediata(tarefa);
    }

}
