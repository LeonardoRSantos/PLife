    package com.example.plife.service.impl;


    import com.example.plife.model.CnpjUser;
    import com.example.plife.model.CpfUser;
    import com.example.plife.model.User;
    import com.example.plife.model.enums.Role;
    import com.example.plife.service.UserService;

    import java.util.ArrayList;
    import java.util.List;

    public class UserServiceImpl implements UserService {

        private List<User> userList;

        public UserServiceImpl() {
            this.userList = new ArrayList<>();
        }

        @Override
        public String cadastrarUsuario(User user) {
            // Verifica se o usuário já está cadastrado
            if (userList.contains(user)) {
                return "Usuário já cadastrado.";
            }

            // Adiciona o usuário à lista
            userList.add(user);
            System.out.println("Cadastro bem-sucedido");
            return "Cadastro bem-sucedido";

        }

        @Override
        public String existeUsuario(CpfUser cpfUser, CnpjUser cnpjUser) {
            // Verifique se o usuário existe com base no CPF ou CNPJ
            if (cpfUser != null) {
                return userList.stream().anyMatch(user -> user instanceof CpfUser && ((CpfUser) user).getCPF().equals(cpfUser.getCPF()))
                        ? "Usuário CPF existe"
                        : "Usuário CPF não existe";
            } else if (cnpjUser != null) {
                return userList.stream().anyMatch(user -> user instanceof CnpjUser && ((CnpjUser) user).getCNPJ().equals(cnpjUser.getCNPJ()))
                        ? "Usuário CNPJ existe"
                        : "Usuário CNPJ não existe";
            }
            return "Parâmetros inválidos";
        }




        public List<User> getUserList() {
            return userList;
        }


    }