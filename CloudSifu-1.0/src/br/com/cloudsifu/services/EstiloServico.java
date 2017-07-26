package br.com.cloudsifu.services;

import java.util.List;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EstiloDAOJDBC;
import br.com.cloudsifu.objects.Estilo;

public class EstiloServico {
	private EstiloDAOJDBC jdbcEstilo;

	public EstiloServico() {
		jdbcEstilo = new EstiloDAOJDBC();
	}

	public boolean adicionarEstilo(Estilo estilo) throws GlobalException {
		return jdbcEstilo.adicionarEstilo(estilo);
	}

	public List<Estilo> buscarEstiloNome(Estilo busca) throws GlobalException {
		return jdbcEstilo.buscarEstiloNome(busca);
	}

	public Estilo buscarEstilo(Estilo busca) throws GlobalException {
		return jdbcEstilo.buscarEstilo(busca);
	}

	public Estilo buscarEstiloId(Estilo busca) throws GlobalException {
		return jdbcEstilo.buscarEstiloId(busca);
	}

	public boolean editarEstilo(Estilo estilo) throws GlobalException {
		return jdbcEstilo.editarEstilo(estilo);
	}

	public boolean excluirEstilo(Estilo estilo) throws GlobalException {
		return jdbcEstilo.excluirEstilo(estilo);
	}
}
