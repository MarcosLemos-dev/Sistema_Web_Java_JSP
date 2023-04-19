package servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin
 */
//o chamado controller são as servlets, nossa classe poderia se chamar ServletLoginController
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})// mapeamento da URL que vem da tela
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /*recebe os dados pela url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = request.getParameter("acao");
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();//invalida a sessao
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
		doPost(request, response);
		}
	}

    /*recebe os dados enviados por um formulario*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String login = request.getParameter("login");// vao ser capturados e colocado em um objeto para serem usados juntos
		String senha = request.getParameter("senha");
		// vao ser capturados e colocado em um objeto para serem usados juntos
		String url = request.getParameter("url");
		try {
			
		if (login !=null && !login.isEmpty() && senha !=null && !senha.isEmpty()) {// valida se o login e senha estão certos
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (daoLoginRepository.validarAutenticacao(modelLogin)) {//simulando o login
				
				modelLogin = daoUsuarioRepository.consultarUsuarioLogado(login);// consultar o objeto no banco para retornar ao setAtribute"isAdmin"
				request.getSession().setAttribute("usuario" , modelLogin.getLogin());
				request.getSession().setAttribute("perfil", modelLogin.getPerfil());
				
				if (url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg","informe o login e senha corretamente");
				redirecionar.forward(request, response);
			
			}
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg","informe o login e senha corretamente");
			redirecionar.forward(request, response);
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		
		}
	}

}
