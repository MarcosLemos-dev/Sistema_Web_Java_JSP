package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() { // construtor
		// TODO Auto-generated constructor stub
		connection = SingleConnectionBanco.getConnection();
	}
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userlogado) throws SQLException {
		
		
			
			if (objeto.isNovo()) {
				
			
		String sql = "INSERT INTO public.model_login( login, senha, nome, email, usuario_id, perfil)	VALUES (?, ?, ?, ?, ?, ?); " ;
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		
		preparedSQL.setString(1, objeto.getLogin()); // captura o objeto
		preparedSQL.setString(2, objeto.getSenha());
		preparedSQL.setString(3, objeto.getNome());
		preparedSQL.setString(4, objeto.getEmail());
		preparedSQL.setLong(5, userlogado);
		preparedSQL.setString(6, objeto.getPerfil());
		
		preparedSQL.execute();
		connection.commit();
			}else {
				String sql = "UPDATE public.model_login SET login=?, senha=?, nome=?, email=?, perfil=?	WHERE id = "+objeto.getId()+" ; " ;
				PreparedStatement prepareSQL = connection.prepareStatement(sql);
				
				prepareSQL.setString(1, objeto.getLogin()); // captura o objeto
				prepareSQL.setString(2, objeto.getSenha());
				prepareSQL.setString(3, objeto.getNome());
				prepareSQL.setString(4, objeto.getEmail());
				prepareSQL.setString(5, objeto.getPerfil());
				
				prepareSQL.executeUpdate();
				connection.commit();
			}
		
		
		return this.consultarUsuario(objeto.getLogin(), userlogado);//ja chama metodo consultarusuario. caso não queira isso é so retirar essa linha e colocar void antes do nome do metodo
		}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useadmin is false and usuario_id = ? " ;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {/*percorrer as linhas de resultado do SQL*/
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));// ESSAs são as colunas do BD
			modelLogin.setId(resultado.getLong("id"));// ESSAs são as colunas do BD
			modelLogin.setLogin(resultado.getString("login"));// ESSAs são as colunas do BD
			modelLogin.setNome(resultado.getString("nome"));// ESSAs são as colunas do BD
			//modelLogin.setEmail(resultado.getString("email"));// ESSAs são as colunas do BD
			
			retorno.add(modelLogin);
		}
		return retorno;
		
		
	}
	
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useadmin is false and usuario_id = " + userLogado ;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {/*percorrer as linhas de resultado do SQL*/
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));// ESSAs são as colunas do BD
			modelLogin.setId(resultado.getLong("id"));// ESSAs são as colunas do BD
			modelLogin.setLogin(resultado.getString("login"));// ESSAs são as colunas do BD
			modelLogin.setNome(resultado.getString("nome"));// ESSAs são as colunas do BD
			modelLogin.setUseadmin(resultado.getBoolean("useadmin"));// ESSAs são as colunas do BD
			
			retorno.add(modelLogin);
		}
		return retorno;
		
		
	}
	
public ModelLogin consultarUsuarioLogado(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"') " ;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado =  statement.executeQuery();
		while (resultado.next()) {// se tem resultado retorna o valor
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUseadmin(resultado.getBoolean("useadmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
		} 
		
		return modelLogin;
	}
	
public ModelLogin consultarUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useadmin is false " ;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado =  statement.executeQuery();
		while (resultado.next()) {// se tem resultado retorna o valor
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			
		} 
		
		return modelLogin;
	}
	public ModelLogin consultarUsuario(String login, Long userlogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useadmin is false and usuario_id = " + userlogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado =  statement.executeQuery();
		while (resultado.next()) {// se tem resultado retorna o valor
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			
		} 
		
		return modelLogin;
	}
	
public ModelLogin consultarUsuarioID(String id, Long userLogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ? and useadmin is false and usuario_id = ?" ;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		ResultSet resultado =  statement.executeQuery();
		while (resultado.next()) {// se tem resultado retorna o valor
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			
		} 
		
		return modelLogin;
	}
	
	public boolean validarLogin(String login) throws SQLException {
	String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
	PreparedStatement statement = connection.prepareStatement(sql);
	
	ResultSet resultado =  statement.executeQuery();
	resultado.next();// pra ele entrar nos resultados do sql , avançar como se fosse um ponteiro
	return resultado.getBoolean("existe");
	
	}
	public void deletarUser(String idUser) throws Exception   {
		
		String sql = "delete from model_login where id = ?and useadmin is false ;";
		PreparedStatement prepareSQL = connection.prepareStatement(sql);
		prepareSQL.setLong(1, Long.parseLong(idUser));
		prepareSQL.executeUpdate();
		connection.commit();
	}
}
