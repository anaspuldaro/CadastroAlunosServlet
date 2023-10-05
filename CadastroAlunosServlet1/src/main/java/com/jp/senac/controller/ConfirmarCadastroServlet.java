package com.jp.senac.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperando a sessão
		HttpSession session = request.getSession();
		
		// Recuperando os valores informados
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		
		// Recuperando a lista da sessão, caso não exista, cria
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		if (listaAlunos == null) {
			listaAlunos = new ArrayList<>(); // Criando a lista
		}

		// Guardar no objeto aluno
		Aluno aluno = new Aluno();
		
		// Adicionando aluno na lista (INSERT)
		listaAlunos.add(aluno);
		
		session.setAttribute("listaAlunos", listaAlunos);
		request.setAttribute("id", id);
		
		// Encaminhar a requisição para o JSP
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
		
		private static final String Semestre = null;
		private static int proximoId = 1;
		
		public static String gerarMatricula(int anoNascimento, int mesNascimento, String Semestre, int idade) {
			int anoAtual = Calendar.getInstance().get(Calendar.YEAR)%100;
			
			String numerosAleatorios = gerarNumerosAleatorios();
			
			String matricula = String.format("%02d%02d%s%02%s", anoNascimento, mesNascimento, Semestre, idade, numerosAleatorios);
			return matricula;
		}
			private static String gerarNumerosAleatorios() {
				Random randon =new Random;
				StringBuilder numerosAleatorios = new StringBuilder();
				
				for(int i = 0; 1 < 4; i++) {
					Random random;
					numerosAleatorios .append(random.nextInt(10));
				}
				return numerosAleatorios.toString();
				
				int anoNascimento;
				int mesNacsimento;
				int idade;
				String matricula = gerarMatricula(anoNascimento, mesNacsimento, Semestre, idade);
				System.out.println ("Matricula gerada" + matricula);
		
	}

}
