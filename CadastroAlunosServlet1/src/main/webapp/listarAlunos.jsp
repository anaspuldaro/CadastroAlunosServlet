<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.jp.senac.model.Aluno" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Alunos</title>
</head>
<body>
<% String usuario = (String) session.getAttribute("usuario");
	out.print("Bem vindo, "+usuario+"<br>");
%>

Clique <a href="cadastrarAluno.jsp">aqui</a> para cadastrar um novo aluno

<!--  mudar para request-->
<%List<Aluno> listaAlunos = (List<Aluno>) request.getAttribute("listaAlunos");  %>

<% if (listaAlunos == null) { %>
		<h3>Nenhum aluno cadastrado</h3>
<% } else { %>
		<h2>Alunos cadastrados</h2>
		<table border="1">
			<tr>
				<th>Detalhar</th>
				<th>Matricula</th>
				<th>Nome</th>
				<th>Idade</th>
				<th>Sexo</th>
				<th>Semestre</th>
				<th>Excluir</th>
			</tr>
			<% for (Aluno aluno : listaAlunos) { %>
			<tr>
				<td><a href="DetalharServlet?nome=<%=aluno.getId()%>">Detalhar</a></td>
				<td><%=aluno.getMatricula()%> </td>
				<td><%=aluno.getNome()%> </td>
				<td><%=aluno.getIdade()%> </td>
				<td><%=aluno.getGenero()%> </td>
				<td><%=aluno.getSemestre()%> </td>
				<td><a href="ExcluirServlet?nome=<%=aluno.getId()%>">Excluir</a></td>
			</tr>
			<% } %>
		</table>
<% } %>

<a href = "LogoutServlet">Lougout</a>
</body>
</html>