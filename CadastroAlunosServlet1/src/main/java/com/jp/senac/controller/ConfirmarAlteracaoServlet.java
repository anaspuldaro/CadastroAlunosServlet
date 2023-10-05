package com.jp.senac.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.jp.senac.model.Aluno;

public class ConfirmarAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade;");
		String genero = request.getParameter("genero;");
		String semestre = request.getParameter("semestre");
		String nomeAntigo = request.getParameter("nomeAntigo");
		
		HttpSession session = request.getSession();
		List<Aluno>listaAlunos = (List<Aluno>)session.getAttribute("listaAlunos");
		
		for(Aluno aluno : listaAlunos) {
			if(aluno.getNome().toString().equals(nomeAntigo)) {
				aluno.setNome(nome);
				aluno.setIdade(idade);
				aluno.setGenero(genero);
				aluno.setSemestre(semestre);
			}
		}
		
		session.setAttribute("listaAlunos", listaAlunos);
		request.getRequestDispatcher("listaAlunos.jsp").forward(request, response);
	}

}
