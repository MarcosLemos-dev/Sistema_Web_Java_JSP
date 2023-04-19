package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private boolean useadmin;
	private String perfil;
	
	
	
	


	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean getUseadmin() {
		return useadmin;
	}

	public void setUseadmin(boolean useadmin) {
		this.useadmin = useadmin;
	}

	public boolean isNovo() {//usando para auxiliar em um novo registro no BD
		
		if(this.id == null) {// é um novo registro
		return true;// inserir um novo, lembrando que o id é automatico gerado no BD
		}else if(this.id !=null && this.id > 0) {// não é um novo registro , trata-se de uma atualização
			return false;//atualiza o que ja existe
		}
			return id == null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
