package com.example.plife.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plife.R;
import com.example.plife.model.TarefaSustentavel;

import java.util.List;

public class TarefaUsuarioAceitaAdapter extends RecyclerView.Adapter<TarefaUsuarioAceitaAdapter.TarefaViewHolder> {

    private List<TarefaSustentavel> tarefas;
    private OnButtonClickListener mListener;
    private OnImageClickListener mImageListener;
    private Integer posicaoTarefaAceitaSelecionada = RecyclerView.NO_POSITION;

    public TarefaUsuarioAceitaAdapter(List<TarefaSustentavel> tarefas, OnButtonClickListener listener, OnImageClickListener imageListener) {
        this.tarefas = tarefas;
        this.mListener = listener;
        this.mImageListener = imageListener;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tarefas_aceitas, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        TarefaSustentavel tarefa = tarefas.get(position);

        holder.textViewTarefa.setText(tarefa.getTipo());
        holder.textViewPontos.setText(String.valueOf(tarefa.getPontos()));

        // Verifica se textViewObjetivo não é nulo antes de definir o texto
        if (holder.textViewObjetivo != null) {
            holder.textViewObjetivo.setText(tarefa.getObjetivo());
        }


//        if (imagemUri != null) {
//            holder.imgTarefaConcluida.setImageURI(imagemUri);
//        }


        holder.btnCompletarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                posicaoTarefaAceitaSelecionada = adapterPosition;
                if (adapterPosition != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onButtonClick(adapterPosition);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                posicaoTarefaAceitaSelecionada = adapterPosition;
                if (adapterPosition != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onItemClick(adapterPosition);
                }
            }
        });

        // Verifica se mListener não é nulo para evitar NullPointerException
        if (mListener != null) {
            holder.itemView.setSelected(mListener.isSelected(position));
        }

        holder.imgTarefaConcluida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (mImageListener != null && adapterPosition != RecyclerView.NO_POSITION) {
                    mImageListener.onImageClick(adapterPosition);
//                    Uri imagemUri = tarefa.getImagemUri();
//                    holder.imgTarefaConcluida.setImageURI(imagemUri);
                }
            }
        });
    }

//    private void exibirImagemEscolhida() {
//        ImageView imageView = findViewById(R.id.btnImgTarefaConcluida);
//        imageView.setImageURI(uriImagem);
//    }


    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public Uri getImagemUri(int position) {
        TarefaSustentavel tarefa = tarefas.get(position);
        return tarefa.getImagemUri();
    }

    public Integer getPosicaoTarefaSelecionada() {
        return posicaoTarefaAceitaSelecionada != RecyclerView.NO_POSITION ? posicaoTarefaAceitaSelecionada : null;
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);

        void onItemClick(int position);

        boolean isSelected(int position);

        void onImageClick(int position);
    }


    public interface OnImageClickListener {
        void onImageClick(int position);
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTarefa;
        TextView textViewPontos;
        TextView textViewObjetivo;
        Button btnCompletarTarefa, btnRemoverTarefa;

        ImageView imgTarefaConcluida;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.adapterTarefasAceitas);
            textViewPontos = itemView.findViewById(R.id.adapterPontosAceitos);
            textViewObjetivo = itemView.findViewById(R.id.adapterObjetivoAceitos);
            btnCompletarTarefa = itemView.findViewById(R.id.btnCompletarTarefaAceita);
            imgTarefaConcluida = itemView.findViewById(R.id.btnImgTarefaConcluida);
        }
    }

}

