package br.com.cloudsifu.services;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.DiarioTurmaDAO;
import br.com.cloudsifu.jdbc.DiarioTurmaDAOJDBC;
import br.com.cloudsifu.objects.DiarioTurma;

public class DiarioTurmaServico {
	private DiarioTurmaDAO diarioTurmaJDBC;

	public DiarioTurmaServico() {
		diarioTurmaJDBC = new DiarioTurmaDAOJDBC();
	}

	public void adicionarDiarioTurma(DiarioTurma diarioTurma) throws GlobalException {
		diarioTurmaJDBC.adicionarDiarioTurma(diarioTurma);
	}

	public DiarioTurma buscarDiarioTurmaId(DiarioTurma diarioTurma)throws GlobalException {
		return diarioTurmaJDBC.buscarDiarioTurmaId(diarioTurma);
	}

	public DiarioTurma buscarDiarioTurmaData(DiarioTurma diarioTurma) throws GlobalException {
		return diarioTurmaJDBC.buscarDiarioTurmaData(diarioTurma);
	}

	public boolean editarDiarioTurma(DiarioTurma diarioTurma) throws GlobalException {
		return diarioTurmaJDBC.editarDiarioTurma(diarioTurma);
	}

	public boolean excluirDiarioTurma(DiarioTurma diarioTurma) throws GlobalException {
		return diarioTurmaJDBC.excluirDiarioTurma(diarioTurma);
	}

}
