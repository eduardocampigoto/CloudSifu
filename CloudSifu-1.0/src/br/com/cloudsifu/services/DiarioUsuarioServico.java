package br.com.cloudsifu.services;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.DiarioUsuarioDAOJDBC;
import br.com.cloudsifu.objects.DiarioUsuario;

public class DiarioUsuarioServico {

	private DiarioUsuarioDAOJDBC jdbcDiarioUsuario;

	public DiarioUsuarioServico() {
		jdbcDiarioUsuario = new DiarioUsuarioDAOJDBC();
	}

	public void adicionarListaDiarioUsuario(List<DiarioUsuario> listaAdicionar) throws GlobalException {
		jdbcDiarioUsuario.adicionarListaDiarioUsuario(listaAdicionar);
	}
	
	public DiarioUsuario buscarDiarioUsuario(DiarioUsuario busca) throws GlobalException {
		return jdbcDiarioUsuario.buscarDiarioUsuarioId(busca);
	}
	
	public List<DiarioUsuario> buscarListaDiarioUsuario(DiarioUsuario busca) throws GlobalException {
				return jdbcDiarioUsuario.buscarListaDiarioUsuario(busca);
	}

	public boolean editarListaDiarioUsuario(List<DiarioUsuario> listaEditar) throws GlobalException {
		return jdbcDiarioUsuario.editarListaDiarioUsuario(listaEditar);
	}

	public boolean excluirDiarioUsuario(DiarioUsuario diarioUsuario) throws GlobalException {
		return jdbcDiarioUsuario.excluirDiarioUsuario(diarioUsuario);
	}

}