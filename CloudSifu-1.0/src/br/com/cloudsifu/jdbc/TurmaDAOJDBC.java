package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Turma;
import br.com.cloudsifu.test.JDBCTeste;

public class TurmaDAOJDBC implements TurmaDAO {

	private Connection connect;
	private AcessaDB acessaDB;
	private JDBCTeste jdbcTeste;
	
	public TurmaDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		jdbcTeste = new JDBCTeste();
	}

	public void adicionarTurma(Turma turma) throws GlobalException {
		String comando = "INSERT INTO `Turma` "
				+ "(nome, horario, diaSemana, descricao) VALUES(?,?,?,?)";
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "adicionarTurma");
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setString(1,  turma.getNome());			
			p.setString(2, turma.getHorario());
			p.setString(3, turma.getDiaSemana());
			p.setString(4, turma.getDescricao());
			p.execute();
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar turma", e);
		}
		acessaDB.fechaConexao();
		
		
	}

	public boolean excluirTurma(Turma turma) throws GlobalException {
		String comando = "DELETE  FROM Turma WHERE id =" + turma.getId();
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "excluirTurma");
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir turma", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public boolean editarTurma(Turma turma) throws GlobalException {
		String comando = "UPDATE Turma SET  nome=?, horario=?, diaSemana=?, descricao=? WHERE id="
				+ turma.getId();
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "editarTurma");
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setString(1, turma.getNome());
			p.setString(2, turma.getHorario());
			p.setString(3, turma.getDiaSemana());
			p.setString(4, turma.getDescricao());
			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar turma", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public Turma buscarTurmaId(Turma busca) throws GlobalException {
		String comando = "SELECT * FROM Turma WHERE id =" + busca.getId();
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "buscarTurmaId");
		Turma turma = new Turma();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setHorario(rs.getString("horario"));
				turma.setDiaSemana(rs.getString("diaSemana"));
				turma.setDescricao(rs.getString("descricao"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turma por ID", e);
		}
		acessaDB.fechaConexao();
		return turma;
	}

	public List<Turma> buscarTurma(Turma busca) throws GlobalException {
		Turma turma = null;
		List<Turma> listaTurma = new ArrayList<Turma>(); 
		String comando = "SELECT * FROM Turma";
		if (!busca.equals("null") && !busca.equals("") && !busca.getNome().equals("todas")){
			comando += " WHERE nome LIKE '%" + busca.getNome() + "%'";
		}
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "buscarTurma");
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turma = new Turma();
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setHorario(rs.getString("horario"));
				turma.setDiaSemana(rs.getString("diaSemana"));
				turma.setDescricao(rs.getString("descricao"));
				listaTurma.add(turma);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turma", e);
		}
		acessaDB.fechaConexao();
		return listaTurma;
	}

	
	
	
	public List<Turma> buscarTurmaHorario(Turma busca) throws GlobalException {
		Turma turma = null;
		List<Turma> listaTurma = new ArrayList<Turma>(); 
		String comando = "SELECT * FROM Turma";
		comando += " WHERE horario LIKE '" + busca.getHorario() + "%'";
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "buscarTurmaHorario");
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turma = new Turma();
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setHorario(rs.getString("horario"));
				turma.setDiaSemana(rs.getString("diaSemana"));
				turma.setDescricao(rs.getString("descricao"));
				listaTurma.add(turma);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turma", e);
		}
		acessaDB.fechaConexao();
		return listaTurma;
	}
	
	public List<Turma> buscarTurmaDiaSemana(Turma busca) throws GlobalException {
		Turma turma = null;
		List<Turma> listaTurma = new ArrayList<Turma>(); 
		String comando = "SELECT * FROM Turma";
		comando += " WHERE diaSemana LIKE '" + busca.getDiaSemana() + "%'";
		jdbcTeste.mostraQuerie(comando, "TurmaDAOJDBC", "buscarTurmaDiaSemana");
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turma = new Turma();
				turma.setId(rs.getInt("id"));
				turma.setNome(rs.getString("nome"));
				turma.setHorario(rs.getString("horario"));
				turma.setDiaSemana(rs.getString("diaSemana"));
				turma.setDescricao(rs.getString("descricao"));
				listaTurma.add(turma);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turma", e);
		}
		acessaDB.fechaConexao();
		return listaTurma;
	}
	
	
}
