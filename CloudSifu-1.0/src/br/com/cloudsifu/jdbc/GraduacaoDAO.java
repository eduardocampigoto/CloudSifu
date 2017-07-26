package br.com.cloudsifu.jdbc;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Graduacao;


public interface GraduacaoDAO {
		public boolean adicionarGraduacao(Graduacao graduacao)throws GlobalException;
		public List<Graduacao> buscarGraduacao(String busca)throws GlobalException;
		public boolean excluirGraduacao(Graduacao graduacao)throws GlobalException;
		public boolean editarGraduacao(Graduacao graduacao)throws GlobalException;
		public Graduacao buscarGraduacaoId(Graduacao graduacao)throws GlobalException;
}

	


