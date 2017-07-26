package br.com.cloudsifu.jdbc;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Estilo;


public interface EstiloDAO {
		
		public boolean adicionarEstilo(Estilo estilo)throws GlobalException;
		public Estilo buscarEstiloId(Estilo estilo)throws GlobalException;
		public Estilo buscarEstilo(Estilo estilo)throws GlobalException;
		public List<Estilo> buscarEstiloNome(Estilo estilo)throws GlobalException;
		public boolean editarEstilo(Estilo estilo)throws GlobalException;
		public boolean excluirEstilo(Estilo estilo)throws GlobalException;
		

	}

	


