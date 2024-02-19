package com.github.ki3lmigu3l.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String login;
    private String password;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, Integer age, String login) {
        this.name = name;
        this.age = age;
        this.login = login;
    }

    public User(String name, Integer age, String login, String password) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String name, Integer age, String login, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashedPassword;
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {

        if (login == null && password == null && age == null) {
            return "Informações do Usuário cadastrado { " +
                    "id: " + id + " " +
                    "name: '" + name + '\'' +
                    " }";
        } else {
            return "Informações do Usuário cadastrado { " +
                    "id: " + id + " " +
                    "name: '" + name + '\'' +
                    ", age: " + age +
                    ", login: '" + login + '\'' +
                    ", password: '" + password + '\'' +
                    " }";
        }

    }
}
