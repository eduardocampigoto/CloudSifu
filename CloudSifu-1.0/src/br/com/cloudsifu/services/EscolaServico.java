package br.com.cloudsifu.services;

import java.util.List;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EscolaDAOJDBC;
import br.com.cloudsifu.objects.Escola;

public class EscolaServico {
	private EscolaDAOJDBC jdbcEscola;

	public EscolaServico() {
		jdbcEscola = new EscolaDAOJDBC();
	}

	public void adicionarEscola(Escola escola) throws GlobalException {
		jdbcEscola.adicionarEscola(escola);
	}

	public List<Escola> buscarEscolaLista(Escola busca) throws GlobalException {
		return jdbcEscola.buscarEscolaLista(busca);
	}

	public Escola buscarEscola(Escola escola) throws GlobalException {
		return jdbcEscola.buscarEscola(escola);
	}

	public Escola buscarEscolaId(Escola escola) throws GlobalException {
		return jdbcEscola.buscarEscolaId(escola);
	}

	public boolean editarEscola(Escola escola) throws GlobalException {
		return jdbcEscola.editarEscola(escola);
	}
	
	public boolean excluirEscola(Escola escola) throws GlobalException {
		return jdbcEscola.excluirEscola(escola);
	}

}
