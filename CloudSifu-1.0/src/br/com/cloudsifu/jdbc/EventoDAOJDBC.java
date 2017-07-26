package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EventoDAO;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.objects.Evento;
import br.com.cloudsifu.services.EnderecoServico;
import br.com.cloudsifu.services.EventoServico;
import br.com.cloudsifu.test.JDBCTeste;

public class EventoDAOJDBC implements EventoDAO {

	private Connection connect;
	private AcessaDB acessaDB;
	private EnderecoServico enderecoServico;
	private JDBCTeste jdbcTeste;
	private DateConvert convert;

	public EventoDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		jdbcTeste = new JDBCTeste();
		enderecoServico = new EnderecoServico();
		convert = new DateConvert();

	}

	public Evento montarEventoNovo(Evento evento, String metodo)
			throws GlobalException, SQLException {
		
		if (enderecoServico.buscarEndereco(evento.getEndereco()) == null ){
			enderecoServico.adicionarEndereco(evento.getEndereco());
			evento.setEndereco(enderecoServico.buscarEndereco(evento.getEndereco()));
		}else{
			evento.setEndereco(enderecoServico.buscarEndereco(evento.getEndereco()));
		}
		return evento;
	}
	
	public Evento montarEventoExistente(Evento evento, String metodo) throws GlobalException{
		evento.setEndereco(enderecoServico.buscarEnderecoId(evento.getEndereco()));
		java.util.Date novaData = new java.util.Date(evento.getData().getTime());	
		evento.setData(novaData);
		return evento;
	}

	public void adicionarEvento(Evento evento) throws GlobalException, SQLException {
		
		montarEventoNovo(evento, "adicionarEvento");
		String comando = "INSERT INTO `Evento` "
				+ "(data, hora, endereco, descricao, titulo) VALUES(?,?,?,?,?)";
		PreparedStatement p;
		Date sqlDate = convert.converteParaSqlDate(evento.getData());
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "adicionarEvento");
		
		try {
			p = connect.prepareStatement(comando);
			p.setDate(1, sqlDate);
			p.setString(2, evento.getHora());
			p.setInt(3, evento.getEndereco().getId());
			p.setString(4, evento.getDescricao());
			p.setString(5, evento.getTitulo());
			p.execute();
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar evento", e);
		}

	}

	public boolean editarEvento(Evento evento) throws GlobalException, SQLException {
		enderecoServico.editarEndereco(evento.getEndereco());
		evento.setEndereco(enderecoServico.buscarEndereco(evento.getEndereco()));
		
		String comando = "UPDATE Evento SET data=?, hora=?, endereco=?, descricao=?, titulo=? WHERE id= "
				+ evento.getId();
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "editarEvento");
		DateConvert convert = new DateConvert();
		Date sqlDate = convert.converteParaSqlDate(evento.getData());
		PreparedStatement p;
	
		try {
			p = connect.prepareStatement(comando);
			p.setDate(1, sqlDate);
			p.setString(2, evento.getHora());
			p.setInt(3, evento.getEndereco().getId());
			p.setString(4, evento.getDescricao());
			p.setString(5, evento.getTitulo());
			p.execute();
			acessaDB.fechaConexao();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar evento", e);
		}
		return true;

	}


	public Evento buscarEventoId(Evento busca) throws GlobalException, SQLException {
		
		String comando = "SELECT * FROM Evento WHERE id =" + busca.getId();
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "buscarEventoId");
		Evento evento = new Evento();
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			
			while (rs.next()) {
				evento.setTitulo(rs.getString("titulo"));
				evento.setEndereco(new Endereco());
				evento.getEndereco().setId(rs.getInt("endereco"));
				evento.setData(rs.getDate("data"));
				evento.setHora(rs.getString("hora"));
				evento.setDescricao(rs.getString("descricao"));
			}
			acessaDB.fechaConexao();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar evento por ID", e);
		}

		return montarEventoExistente(evento, "buscarEventoId");
	}

	public List<Evento> buscarEvento(Evento busca) throws GlobalException, SQLException {
		
		List<Evento> eventoLista = new ArrayList<Evento>();
		String comando = "SELECT * FROM Evento" + " WHERE titulo  like '%" + busca.getTitulo()+"%'";
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "buscarEvento");

		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setTitulo(rs.getString("titulo"));
				evento.setEndereco(new Endereco());
				evento.getEndereco().setId(rs.getInt("endereco"));
				evento.setData(rs.getDate("data"));
				evento.setHora(rs.getString("hora"));
				evento.setDescricao(rs.getString("descricao"));
				montarEventoExistente(evento, "buscarEvento");
				eventoLista.add(evento);
			}
			acessaDB.fechaConexao();
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" buscar lista de eventos", e);
		}
		return eventoLista;
	}

	public List<Evento> buscarEventoData(String busca) throws GlobalException, SQLException {
		
		String comando = "SELECT * FROM Evento";
		Evento evento;
		List<Evento> listaEvento = new ArrayList<Evento>();
		
		//Date sqlDate = convert.converteParaSqlDate(busca.getData());
		if (!busca.equals(null)
				&& !busca.equals("")) {
			comando += " WHERE data = '" + busca + "'";
		}
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "buscarData");
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setEndereco(new Endereco());
				evento.setTitulo(rs.getString("titulo"));
				evento.getEndereco().setId(rs.getInt("endereco"));
				evento.setData(rs.getDate("data"));
				evento.setHora(rs.getString("hora"));
				evento.setDescricao(rs.getString("descricao"));
				montarEventoExistente(evento, "buscarData");
				listaEvento.add(evento);
			}
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar evento por data", e);
		}

		return listaEvento;

	}

	public boolean excluirEvento(Evento evento) throws GlobalException, SQLException {

		String comando = "DELETE  FROM Evento WHERE id =" + evento.getId();
		Statement p;
		jdbcTeste.mostraQuerie(comando, "EventoDAOJDBC", "excluirEvento");
		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();
			return true;

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir evento", e);
		}

	}

}
