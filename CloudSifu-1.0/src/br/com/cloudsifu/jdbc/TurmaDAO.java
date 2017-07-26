package br.com.cloudsifu.jdbc;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Turma;


public interface TurmaDAO {
	
	public void adicionarTurma(Turma turma)throws GlobalException;	
	public Turma buscarTurmaId(Turma turma)throws GlobalException;
	public List<Turma> buscarTurma(Turma turma)throws GlobalException;
	public List<Turma> buscarTurmaHorario(Turma turma)throws GlobalException;
	public List<Turma> buscarTurmaDiaSemana(Turma turma)throws GlobalException;
	public boolean editarTurma(Turma turma)throws GlobalException;
	public boolean excluirTurma(Turma turma)throws GlobalException;

}
