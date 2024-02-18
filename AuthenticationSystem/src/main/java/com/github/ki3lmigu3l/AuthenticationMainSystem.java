package com.github.ki3lmigu3l;

import com.github.ki3lmigu3l.model.User;

import java.util.Scanner;

public class AuthenticationMainSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String username, userLogin, userPassword, option;
        int age;

        String login = "admin";
        String password = "admin";

        System.out.println("""
                =================================================
                                Lista de operações:
                
                            1- Cadastrar um Usuário
                            2- Fazer Login
                            
                =================================================
                """);

        System.out.print("Digite o numero da operação desejada realizar: ");
        option = scanner.nextLine();

        switch (Integer.parseInt(option)) {
            case 1 -> {
                System.out.println("""
                        
                        =================================================
                                Iniciando sistema de Cadastro
                                
                        """);
                System.out.print("Digite seu nome de usuário: ");
                username = scanner.nextLine();
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

                    System.out.println(user.toString());
                }
            }
            case 2 -> {
                System.out.println("""
                        
                        =================================================
                                Iniciando Sistema de Login
                                
                        """);
                System.out.print("Digite seu nome de usuário: ");
                username = scanner.nextLine();
                System.out.print("Login: ");
                userLogin = scanner.nextLine();
                System.out.print("Password: ");
                userPassword = scanner.nextLine();
                if (userLogin.equalsIgnoreCase(login) && userPassword.equalsIgnoreCase(password)) {
                    System.out.println("Login bem-sucedido! Bem-vindo " + username + "!");
                    System.out.println("\n=================================================");
                } else {
                    System.out.println("Login ou senha incorretos. Tente novamente.");
                    System.out.println("\n=================================================");
                }
            }


        }

        scanner.close();
    }

}
