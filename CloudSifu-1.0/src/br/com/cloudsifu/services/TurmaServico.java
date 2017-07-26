package br.com.cloudsifu.services;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.TurmaDAOJDBC;
import br.com.cloudsifu.objects.Turma;

public class TurmaServico {
	private TurmaDAOJDBC jdbcTurma;

	public TurmaServico() {
		jdbcTurma = new TurmaDAOJDBC();
	}

	public void adicionarTurma(Turma turma) throws GlobalException {
		jdbcTurma.adicionarTurma(turma);
		
	}

	public List<Turma> buscarTurmaNome(Turma busca) throws GlobalException {
		return jdbcTurma.buscarTurma(busca);
	}

	public List<Turma> buscarTurmaHorario(Turma busca) throws GlobalException {
		return jdbcTurma.buscarTurmaHorario(busca);
	}
	
	public List<Turma> buscarTurmaDiaSemana(Turma busca) throws GlobalException {
		return jdbcTurma.buscarTurmaDiaSemana(busca);
	}

	public Turma buscarTurmaId(Turma busca) throws GlobalException {
		Turma turma = jdbcTurma.buscarTurmaId(busca);
		return turma;
	}

	public boolean editarTurma(Turma turma) throws GlobalException {
		boolean resposta = jdbcTurma.editarTurma(turma);
		return resposta;
	}
	
	public boolean excluirTurma(Turma turma) throws GlobalException {
		boolean resposta = jdbcTurma.excluirTurma(turma);
		return resposta;
	}

}
