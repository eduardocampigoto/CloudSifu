package br.com.cloudsifu.services;

import java.util.List;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.UsuarioDAOJDBC;
import br.com.cloudsifu.objects.Usuario;

public class UsuarioServico {
	private UsuarioDAOJDBC jdbcUsuario;
	
	public UsuarioServico() {
		jdbcUsuario = new UsuarioDAOJDBC();
	}

	public void adicionarUsuario(Usuario usuario) throws GlobalException {
		jdbcUsuario.adicionarUsuario(usuario);
	}

	public List<Usuario> buscarUsuarioNome(Usuario usuario)
			throws GlobalException {
		return jdbcUsuario.buscarUsuarioNome(usuario);
	}

	public Usuario buscarUsuarioId(Usuario usuario) throws GlobalException {
		return jdbcUsuario.buscarUsuarioId(usuario);
	}

	public Usuario buscarUsuario(Usuario usuario) throws GlobalException {
		return jdbcUsuario.buscarUsuario(usuario);
	}
	
	
	public boolean editarUsuario(Usuario usuario) throws GlobalException {
		return jdbcUsuario.editarUsuario(usuario);
	}

	public boolean deletarUsuario(Usuario usuario) throws GlobalException {
		return jdbcUsuario.removerUsuario(usuario);
	}

}
