package br.com.cloudsifu.services;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.GraduacaoDAOJDBC;
import br.com.cloudsifu.objects.Graduacao;

public class GraduacaoServico {
	private GraduacaoDAOJDBC jdbcGraduacao;

	public GraduacaoServico() {
		jdbcGraduacao = new GraduacaoDAOJDBC();
	}

	public boolean adicionarGraduacao(Graduacao graduacao)
			throws GlobalException {
		return jdbcGraduacao.adicionarGraduacao(graduacao);
	}

	public List<Graduacao> buscarGraduacao(String busca)
			throws GlobalException {
		return jdbcGraduacao.buscarGraduacao(busca);
	}
	
	public List<Graduacao> buscarGraduacaoNome(Graduacao busca)
			throws GlobalException {
		return jdbcGraduacao.buscarGraduacaoNome(busca);
	}
	
	public Graduacao buscarGraduacaoId(Graduacao busca) throws GlobalException {
		return jdbcGraduacao.buscarGraduacaoId(busca);
	}


	public boolean excluirGraduacao(Graduacao graduacao) throws GlobalException {
		return jdbcGraduacao.excluirGraduacao(graduacao);
	}

	
	public boolean editarGraduacao(Graduacao graduacao) throws GlobalException {
		return jdbcGraduacao.editarGraduacao(graduacao);
	}

}
