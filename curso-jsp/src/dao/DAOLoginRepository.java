package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	public DAOLoginRepository() {
		// TODO Auto-generated constructor stub
	connection = SingleConnectionBanco.getConnection();//temos um construtor que semore pega a conexão
	
	}
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception{
		
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true; // usuario autenticado
		}
		
		return false;// usuario não autenticado
		
	
	}
	
	
}
