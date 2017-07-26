package br.com.cloudsifu.jdbc;
import java.sql.SQLException;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Evento;


public interface EventoDAO {
		
		public void adicionarEvento(Evento evento)throws GlobalException, SQLException;		
		public boolean editarEvento(Evento evento)throws GlobalException, SQLException;
		public Evento buscarEventoId(Evento evento)throws GlobalException, SQLException;
		public List<Evento> buscarEvento(Evento evento)throws GlobalException, SQLException;
		public List<Evento> buscarEventoData(String evento)throws GlobalException, SQLException;		
		public boolean excluirEvento(Evento evento)throws GlobalException, SQLException;
}

	


