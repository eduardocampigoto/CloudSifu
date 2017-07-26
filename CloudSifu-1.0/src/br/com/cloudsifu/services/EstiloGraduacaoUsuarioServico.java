package br.com.cloudsifu.services;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EstiloGraduacaoUsuarioDAOJDBC;
import br.com.cloudsifu.objects.EstiloGraduacaoUsuario;

public class EstiloGraduacaoUsuarioServico {
	private EstiloGraduacaoUsuarioDAOJDBC jdbcEstiloGraduacao;
	
	public EstiloGraduacaoUsuarioServico(){
		jdbcEstiloGraduacao = new EstiloGraduacaoUsuarioDAOJDBC();
	}
	
	public boolean adicionarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
		return jdbcEstiloGraduacao.adicionarEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
	}

	public EstiloGraduacaoUsuario buscarEstiloGraduacaoUsuarioNome(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
			return jdbcEstiloGraduacao.buscarEstiloGraduacaoUsuarioNome(estiloGraduacaoUsuario);
	}	
	
	public List<EstiloGraduacaoUsuario> buscarListaEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
		return jdbcEstiloGraduacao.buscarListaEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
	}	
	
	public List<EstiloGraduacaoUsuario> buscarEstiloGraduacaoUsuarioId(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
		return jdbcEstiloGraduacao.buscarEstiloGraduacaoUsuarioId(estiloGraduacaoUsuario);
	}	

	public boolean excluirEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
		return jdbcEstiloGraduacao.excluirEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
	}

	public boolean editarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario) throws GlobalException{
		return jdbcEstiloGraduacao.editarEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
	}

}
