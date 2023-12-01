//package com.example.plife.ui.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.plife.R;
//import com.example.plife.model.TarefaSustentavel;
//
//import java.util.List;
//
//public class TarefaUsuarioAdapter extends RecyclerView.Adapter<TarefaUsuarioAdapter.TarefaViewHolder> {
//
//    private List<TarefaSustentavel> tarefas;
//
//    private int posicaoTarefaSelecionada = RecyclerView.NO_POSITION;
//
//
//    public TarefaUsuarioAdapter(List<TarefaSustentavel> tarefas, OnButtonClickListener listener) {
//        this.tarefas = tarefas;
//        this.mListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.adapter_tarefas_usuario, parent, false);
//        return new TarefaViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
//        TarefaSustentavel tarefa = tarefas.get(position);
//
//        // Atualiza as visualizações com os dados da tarefa
//        holder.textViewTarefa.setText(tarefa.getTipo());
//        holder.textViewPontos.setText(String.valueOf(tarefa.getPontos()));
//        holder.textViewObjetivo.setText(tarefa.getObjetivo());
//        // Adicione outros campos conforme necessário
//
//        // Adicione um ouvinte de clique ao item do RecyclerView
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Atualiza a posição da tarefa selecionada
//                posicaoTarefaSelecionada = holder.getAdapterPosition();
//                notifyDataSetChanged(); // Notifica o adaptador sobre a mudança
//            }
//        });
//        // Atualiza a aparência com base na seleção
//        holder.itemView.setSelected(posicaoTarefaSelecionada == position);
//    }
//
//    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewTarefa;
//        TextView textViewPontos;
//
//        TextView textViewObjetivo;
//
//        public TarefaViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewTarefa = itemView.findViewById(R.id.adapterUsuarioTarefas);
//            textViewPontos = itemView.findViewById(R.id.adapterUsuarioPontos);
//            textViewObjetivo = itemView.findViewById(R.id.adapterUsuarioObjetivo);
//            // Adicione outros campos conforme necessário
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return tarefas.size();
//    }
//
//    public Integer getPosicaoTarefaSelecionada() {
//        return posicaoTarefaSelecionada != RecyclerView.NO_POSITION ? posicaoTarefaSelecionada : null;
//    }
//}

package com.example.plife.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.model.TarefaSustentavel;

import java.util.List;

public class TarefaUsuarioAdapter extends RecyclerView.Adapter<TarefaUsuarioAdapter.TarefaViewHolder> {

    private List<TarefaSustentavel> tarefas;
    private OnButtonClickListener mListener;
    private Integer posicaoTarefaSelecionada = RecyclerView.NO_POSITION;

    public TarefaUsuarioAdapter(List<TarefaSustentavel> tarefas, OnButtonClickListener listener) {
        this.tarefas = tarefas;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tarefas_usuario, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        TarefaSustentavel tarefa = tarefas.get(position);

        holder.textViewTarefa.setText(tarefa.getTipo());
        holder.textViewPontos.setText(String.valueOf(tarefa.getPontos()));
        holder.textViewObjetivo.setText(tarefa.getObjetivo());

        holder.btnAceitarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicaoTarefaSelecionada = holder.getAdapterPosition();
                if (posicaoTarefaSelecionada != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onButtonClick(posicaoTarefaSelecionada);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicaoTarefaSelecionada = holder.getAdapterPosition();
                if (posicaoTarefaSelecionada != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onItemClick(posicaoTarefaSelecionada);
                }
            }
        });

        holder.itemView.setSelected(mListener.isSelected(position));
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTarefa;
        TextView textViewPontos;
        TextView textViewObjetivo;
        Button btnAceitarTarefa;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.adapterUsuarioTarefas);
            textViewPontos = itemView.findViewById(R.id.adapterUsuarioPontos);
            textViewObjetivo = itemView.findViewById(R.id.adapterUsuarioObjetivo);
            btnAceitarTarefa = itemView.findViewById(R.id.btnAceitarTarefaUsuario);
        }
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public Integer getPosicaoTarefaSelecionada() {
        return posicaoTarefaSelecionada != RecyclerView.NO_POSITION ? posicaoTarefaSelecionada : null;
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);

        void onItemClick(int position);

        boolean isSelected(int position);
    }
}

