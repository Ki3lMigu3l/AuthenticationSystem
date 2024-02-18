package com.github.ki3lmigu3l;

import java.util.Scanner;

public class AuthenticationMainSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String username, userLogin, userPassword;

        String login = "admin";
        String password = "admin";

        System.out.println("Bem-vindo ao sistema de login!");
        System.out.println("\nDigite seu nome de usuário: ");
        username = scanner.nextLine();

        System.out.println("\nLogin: ");
        userLogin = scanner.nextLine();

        System.out.println("Password: ");
        userPassword = scanner.nextLine();

        if (userLogin.equalsIgnoreCase(login) && userPassword.equalsIgnoreCase(password)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + username + "!");
        } else {
            System.out.println("Login ou senha incorretos. Tente novamente.");
        }

        scanner.close();
    }
}
