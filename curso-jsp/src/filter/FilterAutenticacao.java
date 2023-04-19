package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;

/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns = {"/principal/*"})// intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	private static Connection connection;
    /**
     * Default constructor. 
     */
    public FilterAutenticacao() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    // o destroy encerra o processo quando o servidor é parado
    // mataria os processos de conexão com o banco
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//dofilter intecpta as requisições e as respostas no sistema
	/*alguns exemplos tudo que fiz no sistema passa por aqui,
	 * validações de autenticação
	 * dar commit e rolback,
	 * validar e fazer redirecionamento de paginas
	 * 
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;//conversão do parametro request para o HTTPSERVELTREQUEST
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath();// url que esta sendo acessada
		
		/* validar se esta logado senão redireciona para a tela de login
		 * 
		 */
		if (usuarioLogado == null  && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {//não esta logado
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "por favor realizar o login!");
			redireciona.forward(request, response);
			return;// para a execução e redireciona para o login
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		
		}
		connection.commit();//deu tudo certo , então comita as alterações no banco de dados
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	// init inicia os processo ou recursos quando o servidor sobre o projeto
	//iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		connection = SingleConnectionBanco.getConnection();
	}

}
