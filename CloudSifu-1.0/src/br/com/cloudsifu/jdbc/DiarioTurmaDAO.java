package br.com.cloudsifu.jdbc;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.DiarioTurma;

public interface DiarioTurmaDAO {
		public void adicionarDiarioTurma(DiarioTurma diarioTurma) throws GlobalException;		
		public boolean editarDiarioTurma(DiarioTurma diarioTurma) throws GlobalException;
		public DiarioTurma buscarDiarioTurmaId(DiarioTurma diarioTurma) throws GlobalException;
		public DiarioTurma buscarDiarioTurmaData(DiarioTurma diarioTurma) throws GlobalException;		
		public boolean excluirDiarioTurma(DiarioTurma diarioTurma) throws GlobalException;
}

	


