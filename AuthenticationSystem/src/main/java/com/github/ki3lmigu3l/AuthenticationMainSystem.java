package com.github.ki3lmigu3l;

import com.github.ki3lmigu3l.model.User;
import com.github.ki3lmigu3l.model.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthenticationMainSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        String option;

        do {

            System.out.println("""
                    =================================================
                                    Lista de operações:
                                    
                                1- Cadastrar um Usuário
                                2- Fazer Login
                                3- Excluir um Usuários
                                4- Consultar Usuários
                                
                    =================================================
                    """);

            System.out.print("Digite o número da operação que deseja realizar: ");
            option = scanner.next();

            switch (Integer.parseInt(option)) {
                case 1 -> register(scanner, userDAO);
                case 2 -> login(scanner, userDAO);
                case 3 -> delete(scanner, userDAO);
                case 4 -> findAll(userDAO);
            }
        } while (!option.equals("sair"));
        scanner.close();
    }

    private static void register(Scanner scanner, UserDAO userDAO) {
        System.out.println("""
                                        
                =================================================
                        Iniciando sistema de Cadastro
                                                            
                """);
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.next();
        System.out.print("Digite a sua idade: ");
        int age = scanner.nextInt();
        System.out.print("Login: ");
        String userLogin = scanner.next();
        System.out.print("Password: ");
        String userPassowrd = scanner.next();

        User user = new User(username, age, userLogin);
        user.setPassword(userPassowrd);

        if (userDAO.saveUser(user)) {
            System.out.println("Usuário inserido no banco de dados.");

            System.out.println("""
                        
                        Cadastro realizado com sucesso!
                        
                    """);
        } else {
            System.out.println("Falha ao inserir usuário no banco de dados.");
        }
    }

    private static void login(Scanner scanner, UserDAO userDAO) {
        System.out.println("""
                                        
                =================================================
                        Iniciando Sistema de Login
                        
                """);
        System.out.print("Digite o seu numero Id: ");
        int id = scanner.nextInt();
        System.out.print("Informe seu Login correspondente: ");
        String login = scanner.next();
        System.out.print("Informe a sua senha: ");
        String password = scanner.next();


        User user = userDAO.findUserByID(id);
        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
            System.out.println("Login bem-sucedido! Bem-vindo " + user.getName() + "!");
        } else {
            System.out.println("Senha ou login incorreto, tente novamente...");
        }
    }

    private static void delete(Scanner scanner, UserDAO userDAO) {
        System.out.println("""
                                        
                =================================================
                      Iniciando Sistema para excluir Usuario
                        
                """);
        System.out.println("Digite o ID do usuário: ");
        int id = scanner.nextInt();
        userDAO.deleteUser(id);
    }

    private static void findAll(UserDAO userDAO) {
        List<User> userList = new ArrayList<>();
        userList = userDAO.findAllUsers();

        System.out.println("""
                                        
                =================================================
                    Iniciando Sistema para consultar usuários
                        
                """);

        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
