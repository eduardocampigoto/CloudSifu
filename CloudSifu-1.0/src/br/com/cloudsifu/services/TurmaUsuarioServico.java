package br.com.cloudsifu.services;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.TurmaUsuarioDAOJDBC;
import br.com.cloudsifu.objects.TurmaUsuario;
import br.com.cloudsifu.objects.Usuario;

public class TurmaUsuarioServico {
	private TurmaUsuarioDAOJDBC jdbcTurmaUsuario;

	public TurmaUsuarioServico() {
		jdbcTurmaUsuario = new TurmaUsuarioDAOJDBC();
	}

	public boolean adicionarTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {
		Boolean resposta = jdbcTurmaUsuario.adicionarTurmaUsuario(turmaUsuario);
		return resposta;
	}

	public boolean excluirTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {
		boolean resposta = jdbcTurmaUsuario.excluirTurmaUsuario(turmaUsuario);
		return resposta;
	}

	public List<TurmaUsuario> buscarTurmaUsuarios(TurmaUsuario busca)
			throws GlobalException {
		return jdbcTurmaUsuario.buscarTurmaUsuarios(busca);
		
	}

	
	public List<Usuario> buscarListaUsuarios(TurmaUsuario busca)
			throws GlobalException {
		return jdbcTurmaUsuario.buscarListaUsuarios(busca);
		
	}
	
	public TurmaUsuario buscarTurma(TurmaUsuario busca) throws GlobalException {
		TurmaUsuario turmaUsuario = jdbcTurmaUsuario.buscarTurma(busca);
		return turmaUsuario;
	}

	public TurmaUsuario buscarUsuario(TurmaUsuario busca)
			throws GlobalException {
		TurmaUsuario turmaUsuario = jdbcTurmaUsuario.buscarUsuario(busca);
		return turmaUsuario;
	}

	public boolean editarTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {
		boolean resposta = jdbcTurmaUsuario.editarTurmaUsuario(turmaUsuario);
		return resposta;
	}

}
