package com.github.ki3lmigu3l.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/authenticationsystem";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public boolean saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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

    public User findUserByID(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
            System.out.println("Erro ao buscar usuário ou usuário não encontrado: " + e.getMessage());
        }

        return null;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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

    public void deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
