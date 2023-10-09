package com.jp.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.jp.senac.model.Aluno;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.mysql.cj.xdevapi.Result;

public class AlunoJDBCdao {

	public Connection getConexao() throws ClassNotFoundException {
		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		// Banco de dados
		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";
		// Usuario
		String user = "root";
		// Senha
		String password = "root";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado ao banco de dados");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// listar
	public List<Aluno> listarAlunos() {
		List<Aluno> alunos = new ArrayList<>();
		String query = "SELECT * FROM alunos";

		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno(id, name, idade, semestre, genero, matricula));

			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return alunos;
	}

	// excluir
	public void excluirAluno(String id) {

		try {
			Connection con = getConexao();
			String comandoSQL = "DELETE FROM alunos WHERE id ";
			Statement stmt = null;
			int excluirAluno = stmt.executeUpdate(comandoSQL);

			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//    //cadastar
//    public Aluno cadastrarAluno (Aluno aluno) {
//	 String query = "Insert into alunos (nome, idade, semestre, genero, matricula) values (?,?,?,?,?)";
//	 
//	 try {
//		 Connection con = getConexao();
//		 PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//		 pst.setString(1,aluno.getNome());
//		 pst.setString(2,aluno.getIdade());
//		 pst.setString(3,aluno.getSemestre());
//		 pst.setString(4,aluno.getGenero());
//		 pst.setString(5,aluno.getMatricula());
//		 
//		 ResultSet rs = pst.getGeneratedKeys();
//		 int id = rs.getInt(1);
//		 aluno.setId(id);
//		 
//    } catch (ClassNotFoundExcption | SQLException e) {
//    	e.printStackTrace();
//    }
//    	
// 
//}

}
