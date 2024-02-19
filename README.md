# Sistema de Autenticação em Java com JDBC

Este é um projeto Java que implementa um sistema básico de autenticação usando JDBC (Java Database Connectivity) para interagir com um banco de dados MySQL. O sistema permite o cadastro de usuários, login, exclusão de usuários e consulta de todos os usuários cadastrados.

## Funcionalidades

- **Cadastro de Usuário**: Permite que novos usuários se cadastrem fornecendo nome, idade, login e senha.
- **Login de Usuário**: Usuários registrados podem fazer login no sistema fornecendo suas credenciais (login e senha).
- **Exclusão de Usuário**: Administradores podem excluir usuários existentes do sistema.
- **Consulta de Usuários**: Administradores podem visualizar uma lista de todos os usuários cadastrados no sistema.

## Tecnologias Utilizadas

- **Java**: A linguagem de programação principal do projeto.
- **JDBC (Java Database Connectivity)**: Para interagir com o banco de dados MySQL.
- **MySQL**: O banco de dados utilizado para armazenar informações de usuário.
- **Git**: Controle de versão utilizando Git para gerenciar o código-fonte.

## Como Utilizar

1. Clone o repositório para sua máquina local.
2. Configure o banco de dados MySQL e crie a tabela `Users` com os campos `id`, `name`, `age`, `login` e `password`.
3. Atualize as variáveis `URL`, `USUARIO` e `SENHA` na classe `AuthenticationMainSystem` com as configurações do seu banco de dados MySQL.
4. Compile e execute o projeto.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma Issue se encontrar algum problema ou propor uma melhoria. Pull requests também são aceitos.
