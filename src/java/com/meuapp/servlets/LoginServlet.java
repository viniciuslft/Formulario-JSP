package com.meuapp.servlets;

import com.meuapp.dao.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean loginValido = usuarioDAO.validarLogin(email, senha);

        if (loginValido) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("index.html?erro=1");
        }
    }
}
