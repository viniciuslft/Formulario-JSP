package com.meuapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/meu_projeto";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "root"; // troque pela sua senha

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                response.sendRedirect("index.html?cadastro=sucesso");
            } else {
                response.sendRedirect("cadastro.html?erro=1");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cadastro.html?erro=2");
        }
    }
}
