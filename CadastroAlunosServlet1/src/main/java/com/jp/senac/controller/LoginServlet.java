package com.jp.senac.controller;

import java.io.IOException;
import java.util.List;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		if(usuario.equals("admin")  && senha.equals("admin")) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(150);
			
			
			//banco de dados
			AlunoJDBCdao dao = new AlunoJDBCdao();
			List<Aluno> listaAlunos = dao.listarAlunos();
			//mudar aqui
			request.setAttribute("listaAlunos", listaAlunos);
			
			session.setAttribute("usuario", usuario);
			request.getRequestDispatcher("listarAlunos.jsp").forward(request, response);
		} else {
			request.setAttribute("mensagem", "Usuario e/ou senha inválida");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}


	}

}
