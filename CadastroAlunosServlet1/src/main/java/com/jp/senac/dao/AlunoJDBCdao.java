package com.jp.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jp.senac.model.Aluno;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.mysql.cj.xdevapi.Result;

public class AlunoJDBCdao {
	
	public Connection getConexao() throws ClassNotFoundException {
		
		//driver
		String driver = "com.mysql.jdbc.Driver";
		
		//banco de dados
		String url = "jdbc:mysql://localhost:3306/cadastroalunos";
		
		//usuario
		String user = "root";
				
		//senha
		String password = "root";
		
		Connection con =  null;
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Conectado ao banco de dados");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return con;
		
	}
	
	public List<Aluno> listarAlunos (){
		List<Aluno> alunos = new ArrayList<>();
		String query = "SELECT * FROM alunos";
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno(id,name,semestre,genero,matricula));
				
			}
		
		Connection con1 = getConexao();
				rs.close();
		pst.close();
		con1.close();
	}catch (ClassNotFoundException e){
		e.printStackTrace();
		
		return alunos;
	}
	}
