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

public class ExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		HttpSession session = request.getSession();
		
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		
		Object Aluno = null;
		for(Aluno a: listaAlunos) {
			if(a.getNome().toString().equals(id)) {
				Aluno = a;
			}
		}
		
		listaAlunos.remove(Aluno);
		
		AlunoJDBCdao dao = new AlunoJDBCdao();
		dao.excluirAluno(id);
		
		
		session.setAttribute("listaAlunos", listaAlunos);
		request.getRequestDispatcher("listarServlet").forward(request, response);
	}
}
