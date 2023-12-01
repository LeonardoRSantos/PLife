package com.example.plife.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.model.TarefaSustentavel;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<TarefaSustentavel> tarefas;

    public TarefaAdapter(List<TarefaSustentavel> tarefas) {
        this.tarefas = tarefas;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tarefas, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        TarefaSustentavel tarefa = tarefas.get(position);

        // Atualiza as visualizações com os dados da tarefa
        holder.textViewTarefa.setText(tarefa.getTipo());
        holder.textViewPontos.setText(String.valueOf(tarefa.getPontos()));
        holder.textViewObjetivo.setText(tarefa.getObjetivo());
        // Adicione outros campos conforme necessário
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTarefa;
        TextView textViewPontos;

        TextView textViewObjetivo;


//= findViewById(R.id.spinnerTarefas);
// String tipoTarefa = spinnerTarefas.getSelectedItem().toString();
        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.adapterTarefas);
            textViewPontos = itemView.findViewById(R.id.adapterPontos);
            textViewObjetivo = itemView.findViewById(R.id.adapterObjetivo);
            // Adicione outros campos conforme necessário
        }
    }
}
