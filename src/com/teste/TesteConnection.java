package com.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.conexaoHSQLDB;

public class TesteConnection {

	public static void main(String[] args) {
		conexaoHSQLDB conn =  new conexaoHSQLDB();
		Connection connection =  conn.conectar();
		System.out.println(connection);
		
	    final String SQL_INSERT_PESSOA = "INSERT INTO PRODUTO(NOME,TIPO,PRECO) VALUES(?,?,?)";
	    PreparedStatement pst;
		try {
			pst = connection.prepareStatement(SQL_INSERT_PESSOA);
			pst.setString(1,"aa");
			pst.setString(2,"teste.com");
			pst.setDouble(3, 1);
			int qtde = pst.executeUpdate();
			System.out.println("Qtde inserido: "+ qtde);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		final String SQL_SELECT_PESSOA = "SELECT * FROM PRODUTO";
		try {
			pst = connection.prepareStatement(SQL_SELECT_PESSOA);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String nome = rs.getString("NOME");
				String email = rs.getString("TIPO");
				Double preco  = rs.getDouble("PRECO");

				System.out.println(id +" "+ nome +" "+email+ "" + preco );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
