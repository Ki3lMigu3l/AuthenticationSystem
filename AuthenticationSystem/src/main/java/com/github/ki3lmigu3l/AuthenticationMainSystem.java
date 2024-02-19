package com.github.ki3lmigu3l;

import com.github.ki3lmigu3l.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthenticationMainSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/authenticationsystem";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();
        User user = new User();
        String username, userLogin, userPassword, option;
        int age, id = 0;

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

            System.out.print("Digite o numero da operação que deseja realizar: ");
            option = scanner.next();

            switch (Integer.parseInt(option)) {
                case 1 -> {
                    System.out.println("""
                                                    
                            =================================================
                                    Iniciando sistema de Cadastro
                                    
                            """);

                    System.out.print("Digite seu nome de usuário: ");
                    username = scanner.next();
                    System.out.print("Digite a sua idade: ");
                    age = scanner.nextInt();
                    System.out.print("Login: ");
                    userLogin = scanner.next();
                    System.out.print("Password: ");
                    userPassword = scanner.next();

                    if (saveUser(new User(username, age, userLogin, userPassword))) {
                        System.out.println("Usuário inserido no banco de dados com sucesso.");
                        System.out.println("""
                                        
                                        Cadastro realizado com sucesso!
                                        
                                =================================================
                                    """);


                    } else {
                        System.out.println("Falha ao inserir usuário no banco de dados.");
                        break;
                    }

                    System.out.println("Digite qualquer coisa para continuar ou 'Sair' para sair");
                    option = scanner.next();

                }

                case 2 -> {
                    System.out.println("""
                                                    
                            =================================================
                                    Iniciando Sistema de Login
                                    
                            """);

                    while (!option.equalsIgnoreCase("sair")) {
                        System.out.println("Digite seu id: ");
                        id = scanner.nextInt();

                        System.out.print("Login: ");
                        userLogin = scanner.next();

                        System.out.print("Password: ");
                        userPassword = scanner.next();


                        user = findUserByID(id);

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

                        System.out.println("Para logar em outra conta digite 1 para sair Digite 'Sair'");
                        option = scanner.next();
                    }
                }

                case 3 -> {
                    System.out.println("Digite qualquer coisa para continuar ou 'Sair' para sair");
                    option = scanner.next();
                }

                case 4 -> {

                    users = findAllUsers();
                    for (User user1 : users) {
                        System.out.println(user1.toString());
                    }

                    System.out.println("Digite qualquer coisa para continuar ou 'Sair' para sair");
                    option = scanner.next();
                }
            }
        } while (!option.equals("sair"));
        scanner.close();
    }

    private static boolean saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "INSERT INTO Users (name, age, login, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setString(3, user.getLogin());
                preparedStatement.setString(4, user.getPassword());
                int linhasAfetadas = preparedStatement.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar conectar ao banco de dados: " + e.getMessage());
            return false;
        }
    }

    private static User findUserByID(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM Users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return new User(result.getInt("id"),
                            result.getString("name"),
                            result.getInt("age"),
                            result.getString("login"),
                            result.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }

    private static List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM Users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todos os usuários: " + e.getMessage());
        }

        return users;
    }

    private static void deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "DELETE FROM Users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Usuário deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
