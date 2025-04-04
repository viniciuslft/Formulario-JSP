package com.meuapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/meu_projeto";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    public boolean validarLogin(String email, String senha) {
        boolean valido = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            valido = rs.next(); // Se tiver resultado, o login é válido

            rs.close();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valido;
    }
}
