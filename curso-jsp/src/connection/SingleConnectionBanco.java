package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp";
	private static String senha = "postgres";
	private static String user = "postgres";

	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	static {// chamar o conectar quando for feita a chamada direta da classe
		conectar();
	}
	public SingleConnectionBanco() {// quando tiver uma instancia vai conectar, 
		// TODO Auto-generated constructor stub
	conectar();
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");// carrega o driver de conexão do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//para não efetuar alterações no banco sem nosso comando
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//mostra qualquer erro no meomento de conectar
		}
	}
}
