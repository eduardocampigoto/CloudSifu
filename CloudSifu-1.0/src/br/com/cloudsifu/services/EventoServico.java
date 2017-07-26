package br.com.cloudsifu.services;

import java.sql.SQLException;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EventoDAOJDBC;
import br.com.cloudsifu.objects.Evento;

public class EventoServico {
	private EventoDAOJDBC jdbcEvento;

	public EventoServico() {
		jdbcEvento = new EventoDAOJDBC();
	}

	public void adicionarEvento(Evento evento) throws GlobalException, SQLException {
		jdbcEvento.adicionarEvento(evento);
	}

	public List<Evento> buscarEvento(Evento evento) throws GlobalException, SQLException {
		return jdbcEvento.buscarEvento(evento);
	}
	
	public Evento buscarEventoId(Evento evento) throws GlobalException, SQLException {
		return jdbcEvento.buscarEventoId(evento);
	}

	public List<Evento> buscarEventoData(String busca) throws GlobalException, SQLException {
		return jdbcEvento.buscarEventoData(busca);
	}

	public boolean editarEvento(Evento evento) throws GlobalException, SQLException {
		return jdbcEvento.editarEvento(evento);
	}

	public boolean excluirEvento(Evento evento) throws GlobalException, SQLException {
		return jdbcEvento.excluirEvento(evento);
	}

}
