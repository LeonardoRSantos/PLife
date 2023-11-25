package com.example.plife.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.plife.model.CnpjUser;
import com.example.plife.model.TarefaSustentavel;
import com.example.plife.model.User;
import com.example.plife.model.enums.Role;
import com.example.plife.service.TarefaService;
import com.example.plife.service.UserService;
import com.example.plife.service.util.PontuacaoTarefa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TarefaServiceImpl implements TarefaService {

    private UserService userService;
    private static TarefaServiceImpl instance;
    private List<TarefaSustentavel> tarefaSustentavelList;
    private static final String TAREFA_LIST_KEY = "tarefaSustentavelList";
    private static final String TAREFA_IMAGE_DIRECTORY = "tarefa_images";

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Context context;  // Adiciona uma referência ao contexto

    private TarefaServiceImpl(Context context) {
        this.context = context;
        this.tarefaSustentavelList = new ArrayList<>();
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        this.gson = new Gson();

        // Carrega a lista de tarefas do SharedPreferences
        loadTarefaList();
    }

    public static synchronized TarefaServiceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new TarefaServiceImpl(context);
        }
        return instance;
    }
    @Override
    public String cadastrarTarefaCnpj(CnpjUser cnpjUser, TarefaSustentavel tarefaSustentavel, InputStream imagemStream) {
        try {
            // Lógica para cadastrar a tarefa e vincular a empresa que está cadastrando a tarefa.
            tarefaSustentavel.setCnpjUser(cnpjUser);

            // Salva a imagem e obtém o caminho
            String caminhoImagem = saveImageToInternalStorage(imagemStream);

            // Associa o caminho da imagem à tarefa
            tarefaSustentavel.setProduto(caminhoImagem);

            int pontosImediatos = PontuacaoTarefa.getPontuacaoImediata(tarefaSustentavel.getTipo());

            tarefaSustentavel.setPontos(pontosImediatos);

            // Adiciona a tarefa à lista
            tarefaSustentavelList.add(tarefaSustentavel);

            // Salva a lista de tarefas no SharedPreferences
            saveTarefaList();

            return "Tarefa cadastrada com sucesso.";
        } catch (Exception e) {
            e.printStackTrace(); // Isso imprime a exceção no console. Modifique conforme necessário.
            return "Erro ao cadastrar a tarefa. Por favor, tente novamente.";
        }
    }


    private String saveImageToInternalStorage(InputStream imagemStream) {
        try {
            // Cria um diretório para armazenar as imagens se não existir
            File directory = new File(context.getFilesDir(), TAREFA_IMAGE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Gera um nome único para a imagem
            String imageName = UUID.randomUUID().toString() + ".png";

            // Cria o arquivo da imagem no diretório
            File imagePath = new File(directory, imageName);
            FileOutputStream fos = new FileOutputStream(imagePath);

            // Lê o InputStream da imagem e grava no arquivo
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imagemStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            // Fecha o FileOutputStream e o InputStream
            fos.close();
            imagemStream.close();

            return imageName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Ocorreu algum erro, não foi salvar a imagen.";
        }
    }

    @Override
    public String validarTarefa(String adminUsername, Long tarefaId) {
        // Verificar se o usuário é um admin
        if (verificarSeAdmin(adminUsername)) {
            // Encontrar a tarefa pelo ID
            TarefaSustentavel tarefa = encontrarTarefaPorId(tarefaId);

            // Validar a tarefa
            if (tarefa != null) {
                tarefa.setValidado(true);
                saveTarefaList();  // Salvar a lista atualizada no SharedPreferences
                return "Tarefa validada com sucesso."; // Tarefa encontrada e validada com sucesso
            } else {
                return "Não foi possível encontrar a tarefa do ID: " + tarefaId; // Tarefa não encontrada
            }
        }
        return "Ocorreu algum erro, não foi possível validar.";  // Falha na validação
    }

    private boolean verificarSeAdmin(String adminUsername) {
        // Percorre a lista de usuários admin
        for (User adminUser : userService.getUserAdminList()) {
            // Verifica se o nome de usuário e a função correspondem a um usuário admin
            if (adminUser.getName().equals(adminUsername) && adminUser.getRole() == Role.ROLE_ADMIN) {
                return true; // Retorna true se encontrar um usuário admin
            }
        }
        return false; // Retorna false se não encontrar um usuário admin
    }


    private TarefaSustentavel encontrarTarefaPorId(Long tarefaId) {
        // Percorre a lista de tarefas
        for (TarefaSustentavel tarefa : tarefaSustentavelList) {
            // Verifica se o ID da tarefa corresponde ao ID fornecido
            if (tarefa.getId() == tarefaId) {
                return tarefa; // Retorna a tarefa se encontrada
            }
        }
        return null; // Retorna null se a tarefa não for encontrada
    }


    private void loadTarefaList() {
        String tarefaListJson = sharedPreferences.getString(TAREFA_LIST_KEY, null);
        if (tarefaListJson != null) {
            Type tarefaListType = new TypeToken<List<TarefaSustentavel>>() {}.getType();
            tarefaSustentavelList = gson.fromJson(tarefaListJson, tarefaListType);
        }
    }

    private void saveTarefaList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String tarefaListJson = gson.toJson(tarefaSustentavelList);
        editor.putString(TAREFA_LIST_KEY, tarefaListJson);
        editor.apply();
    }

    @Override
    public List<TarefaSustentavel> getTarefasDisponiveisCpf() {
        // Retorna a lista de tarefas disponíveis para usuários do tipo CPF
        return tarefaSustentavelList;
    }



}
