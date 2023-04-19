package servelts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;
import dao.DAOUsuarioRepository;

public class ServeltGenericUtil extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
			
		return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado).getId();
	}
}
