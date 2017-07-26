package br.com.cloudsifu.services;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.LoginDAOJDBC;
import br.com.cloudsifu.objects.Usuario;

public class LoginServico {
	
	private LoginDAOJDBC jdbcLogin;
	
	public LoginServico() {
		jdbcLogin = new LoginDAOJDBC();
	}
	
	public Usuario validarLogin(Usuario valida) throws GlobalException{
		return jdbcLogin.validarLogin(valida);
	}

}
