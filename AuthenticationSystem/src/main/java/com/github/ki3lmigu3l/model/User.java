package com.github.ki3lmigu3l.model;

public class User {

    private String name;
    private Integer age;
    private String login;
    private String password;

    public User() {
    }

    public User(String name, Integer age, String login, String password) {
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
        this.password = password;
    }

    @Override
    public String toString() {
        return "Informações do Usuário cadastrado { " +
                "name: '" + name + '\'' +
                ", age: " + age +
                ", login: '" + login + '\'' +
                ", password: '" + password + '\'' +
                " }";
    }
}
