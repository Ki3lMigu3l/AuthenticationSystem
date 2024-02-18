package com.github.ki3lmigu3l;

import com.github.ki3lmigu3l.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthenticationMainSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();
        String username, userLogin, userPassword, option;
        int age;

        String login = "admin";
        String password = "admin";

        users.add(new User("Ezequiel", 20, "admin", "admin"));
        users.add(new User("Miguel", 18, "user", "123"));
        users.add(new User("Isac", 25, "user", "user123"));

        System.out.println("""
                =================================================
                                Lista de operações:
                
                            1- Cadastrar um Usuário
                            2- Fazer Login
                            3- Consultar Usuários
                            4- Sair
                            
                =================================================
                """);

        System.out.print("Digite o numero da operação que deseja realizar: ");
        option = scanner.nextLine();

        switch (Integer.parseInt(option)) {
            case 1 -> {
                System.out.println("""
                        
                        =================================================
                                Iniciando sistema de Cadastro
                                
                        """);

                while (!option.equalsIgnoreCase("sair")) {
                    System.out.print("Digite seu nome de usuário: ");
                    username = scanner.next();
                    System.out.print("Digite a sua idade: ");
                    age = scanner.nextInt();
                    System.out.print("Login: ");
                    userLogin = scanner.next();
                    System.out.print("Password: ");
                    userPassword = scanner.next();
                    if (age < 18) {
                        System.out.println("Não foi possível realizar o cadastro, você não é maior de idade!");
                        System.out.println("\n=================================================");
                    } else {
                        System.out.println("""
                                        
                                        Cadastro realizado com sucesso!
                                        
                                =================================================
                                    """);
                        User user = new User(username, age, login, password);
                        users.add(user);

                        System.out.println("Digite qualquer coisa para continuar ou 'Sair' para sair");
                        option = scanner.next();
                    }

                }
                    for(User userOptional: users) {
                        System.out.println(userOptional.toString());
                    }
            }

            case 2 -> {
                System.out.println("""
                                                
                        =================================================
                                Iniciando Sistema de Login
                                
                        """);

                while (!option.equalsIgnoreCase("sair")) {
                    System.out.println("Digite seu username: ");
                    username = scanner.next();

                    System.out.print("Login: ");
                    userLogin = scanner.next();

                    System.out.print("Password: ");
                    userPassword = scanner.next();

                    User user = findByName(users, username);

                    if (user == null) {
                        System.out.println("Usuário não encontrado!");
                        System.out.println("\n=================================================");

                    } else if (user.getLogin().equals(userLogin) && user.getPassword().equals(userPassword)) {
                        System.out.println("Login bem-sucedido! Bem-vindo " + user.getName() + "!");
                        System.out.println("\n=================================================");
                    } else {
                        System.out.println("Senha ou Login incorreto, tente novamente!");
                        System.out.println("\n=================================================");
                    }

                    System.out.println("Digite qualquer coisa para continuar ou 'Sair' para sair");
                    option = scanner.next();
                }
            }
            case 3 -> {
                for (User user: users) {
                    System.out.println(user.toString());
                }
            }

            case 4 -> {
                System.out.println("Encerrando o programa...");
            }
        }

        scanner.close();
    }

    public static User findByName (List<User> users, String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }

        return null;
    }

}
